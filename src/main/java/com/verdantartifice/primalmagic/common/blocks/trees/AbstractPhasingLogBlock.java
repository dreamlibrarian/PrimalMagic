package com.verdantartifice.primalmagic.common.blocks.trees;

import java.util.Random;

import com.verdantartifice.primalmagic.common.blockstates.properties.TimePhase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Constants;

/**
 * Base definition for log blocks that phase in and out over time.
 * 
 * @author Daedalus4096
 */
public abstract class AbstractPhasingLogBlock extends LogBlock {
    public static final EnumProperty<TimePhase> PHASE = EnumProperty.create("phase", TimePhase.class);
    
    protected final Block strippedVersion;
    
    public AbstractPhasingLogBlock(Block stripped, MaterialColor color, Block.Properties properties) {
        super(color, properties);
        this.setDefaultState(this.getDefaultState().with(PHASE, TimePhase.FULL));
        this.strippedVersion = stripped;
    }
    
    /**
     * Get the current phase of the block based on the current game time.
     * 
     * @param world the game world
     * @return the block's current phase
     */
    public abstract TimePhase getCurrentPhase(IWorld world);
    
    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(PHASE);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        // Set the block's phase upon placement
        TimePhase phase = this.getCurrentPhase(context.getWorld());
        return super.getStateForPlacement(context).with(PHASE, phase);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        // Periodically check to see if the block's phase needs to be updated
        super.randomTick(state, worldIn, pos, random);
        TimePhase newPhase = this.getCurrentPhase(worldIn);
        if (newPhase != state.get(PHASE)) {
            worldIn.setBlockState(pos, state.with(PHASE, newPhase), Constants.BlockFlags.DEFAULT);
        }
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        // Immediately check to see if the block's phase needs to be updated when one of its neighbors changes
        BlockState state = super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        TimePhase newPhase = this.getCurrentPhase(worldIn);
        if (newPhase != state.get(PHASE)) {
            state = state.with(PHASE, newPhase);
        }
        return state;
    }
    
    @Override
    public float getBlockHardness(BlockState blockState, IBlockReader worldIn, BlockPos pos) {
        if (blockState.get(PHASE) == TimePhase.FULL) {
            // If the block is fully phased in, use its default hardness as those aren't all the same
            return this.blockHardness;
        } else {
            return blockState.get(PHASE).getHardness();
        }
    }
    
    @Override
    public float getExplosionResistance(BlockState state, IWorldReader world, BlockPos pos, Entity exploder, Explosion explosion) {
        if (state.get(PHASE) == TimePhase.FULL) {
            // If the block is fully phased in, use its default resistance as those aren't all the same
            return this.blockResistance;
        } else {
            return state.get(PHASE).getResistance();
        }
    }
    
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (this.strippedVersion != null && player != null && player.getHeldItem(handIn).getItem() instanceof AxeItem) {
            // If the player right-clicks on the log with an axe, replace this block with its stripped version
            worldIn.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!worldIn.isRemote) {
                worldIn.setBlockState(pos, this.strippedVersion.getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS)).with(PHASE, state.get(PHASE)), Constants.BlockFlags.DEFAULT_AND_RERENDER);
                player.getHeldItem(handIn).damageItem(1, player, (p) -> {
                    p.sendBreakAnimation(handIn);
                });
            }
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }
}
