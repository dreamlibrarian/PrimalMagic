package com.verdantartifice.primalmagic.common.worldgen.features;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

public abstract class AbstractPhasingTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    public AbstractPhasingTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> config, boolean doBlockNotify) {
        super(config, doBlockNotify);
    }
    
    protected abstract IPlantable getSapling();
    
    protected abstract BlockState getLogState(IWorldGenerationReader world);

    protected abstract BlockState getLeafState(IWorldGenerationReader world);

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader world, Random rand, BlockPos position, MutableBoundingBox box) {
        int i = rand.nextInt(3) + 5;
        
        BlockState logState = this.getLogState(world);
        BlockState leafState = this.getLeafState(world);
        
        boolean flag = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= world.getMaxHeight()) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;
                if (j == position.getY()) {
                    k = 0;
                }
                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }
                
                BlockPos.MutableBlockPos mbp = new BlockPos.MutableBlockPos();
                
                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < world.getMaxHeight()) {
                            if (!func_214587_a(world, mbp.setPos(l, j, i1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }
            
            if (!flag) {
                return false;
            } else if (isSoil(world, position.down(), getSapling()) && position.getY() < world.getMaxHeight() - i - 1) {
                this.setDirtAt(world, position.down(), position);

                for (int l1 = position.getY() - 3 + i; l1 <= position.getY() + i; ++l1) {
                    int j2 = l1 - (position.getY() + i);
                    int k2 = 1 - j2 / 2;

                    for (int l2 = position.getX() - k2; l2 <= position.getX() + k2; ++l2) {
                        int i3 = l2 - position.getX();

                        for (int j1 = position.getZ() - k2; j1 <= position.getZ() + k2; ++j1) {
                            int k1 = j1 - position.getZ();
                            if (Math.abs(i3) != k2 || Math.abs(k1) != k2 || rand.nextInt(2) != 0 && j2 != 0) {
                                BlockPos blockpos = new BlockPos(l2, l1, j1);
                                if (isAirOrLeaves(world, blockpos)) {
                                    this.setLogState(changedBlocks, world, blockpos, leafState, box);
                                }
                            }
                        }
                    }
                }
                
                for (int i2 = 0; i2 < i; ++i2) {
                    if (isAirOrLeaves(world, position.up(i2))) {
                        this.setLogState(changedBlocks, world, position.up(i2), logState, box);
                    }
                }
                
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
