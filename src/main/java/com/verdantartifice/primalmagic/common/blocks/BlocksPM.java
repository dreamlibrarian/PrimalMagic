package com.verdantartifice.primalmagic.common.blocks;

import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.common.blocks.base.SaplingBlockPM;
import com.verdantartifice.primalmagic.common.blocks.crafting.ArcaneWorkbenchBlock;
import com.verdantartifice.primalmagic.common.blocks.crafting.CalcinatorBlock;
import com.verdantartifice.primalmagic.common.blocks.crafting.SpellcraftingAltarBlock;
import com.verdantartifice.primalmagic.common.blocks.crafting.WandAssemblyTableBlock;
import com.verdantartifice.primalmagic.common.blocks.crafting.WandInscriptionTableBlock;
import com.verdantartifice.primalmagic.common.blocks.mana.AncientManaFontBlock;
import com.verdantartifice.primalmagic.common.blocks.mana.WandChargerBlock;
import com.verdantartifice.primalmagic.common.blocks.misc.AnalysisTableBlock;
import com.verdantartifice.primalmagic.common.blocks.misc.ConsecrationFieldBlock;
import com.verdantartifice.primalmagic.common.blocks.misc.PillarBlock;
import com.verdantartifice.primalmagic.common.blocks.misc.WoodTableBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.MoonwoodLeavesBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.MoonwoodLogBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.MoonwoodPillarBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.MoonwoodPlanksBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.MoonwoodSlabBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.MoonwoodStairsBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.MoonwoodTree;
import com.verdantartifice.primalmagic.common.blocks.trees.SunwoodLeavesBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.SunwoodLogBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.SunwoodPillarBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.SunwoodPlanksBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.SunwoodSlabBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.SunwoodStairsBlock;
import com.verdantartifice.primalmagic.common.blocks.trees.SunwoodTree;
import com.verdantartifice.primalmagic.common.sources.Source;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Deferred registry for mod blocks.
 * 
 * @author Daedalus4096
 */
public class BlocksPM {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, PrimalMagic.MODID);
    
    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    
    // Register raw marble blocks
    public static final RegistryObject<Block> MARBLE_RAW = BLOCKS.register("marble_raw", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<SlabBlock> MARBLE_SLAB = BLOCKS.register("marble_slab", () -> new SlabBlock(Block.Properties.from(MARBLE_RAW.get())));
    public static final RegistryObject<StairsBlock> MARBLE_STAIRS = BLOCKS.register("marble_stairs", () -> new StairsBlock(MARBLE_RAW.get()::getDefaultState, Block.Properties.from(MARBLE_RAW.get())));
    public static final RegistryObject<WallBlock> MARBLE_WALL = BLOCKS.register("marble_wall", () -> new WallBlock(Block.Properties.from(MARBLE_RAW.get())));
    public static final RegistryObject<Block> MARBLE_BRICKS = BLOCKS.register("marble_bricks", () -> new Block(Block.Properties.from(MARBLE_RAW.get())));
    public static final RegistryObject<SlabBlock> MARBLE_BRICK_SLAB = BLOCKS.register("marble_brick_slab", () -> new SlabBlock(Block.Properties.from(MARBLE_BRICKS.get())));
    public static final RegistryObject<StairsBlock> MARBLE_BRICK_STAIRS = BLOCKS.register("marble_brick_stairs", () -> new StairsBlock(MARBLE_BRICKS.get()::getDefaultState, Block.Properties.from(MARBLE_BRICKS.get())));
    public static final RegistryObject<WallBlock> MARBLE_BRICK_WALL = BLOCKS.register("marble_brick_wall", () -> new WallBlock(Block.Properties.from(MARBLE_BRICKS.get())));
    public static final RegistryObject<PillarBlock> MARBLE_PILLAR = BLOCKS.register("marble_pillar", () -> new PillarBlock(Block.Properties.from(MARBLE_RAW.get())));
    public static final RegistryObject<Block> MARBLE_CHISELED = BLOCKS.register("marble_chiseled", () -> new Block(Block.Properties.from(MARBLE_RAW.get())));
    public static final RegistryObject<Block> MARBLE_RUNED = BLOCKS.register("marble_runed", () -> new Block(Block.Properties.from(MARBLE_RAW.get())));
    
    // Register enchanted marble blocks
    public static final RegistryObject<Block> MARBLE_ENCHANTED = BLOCKS.register("marble_enchanted", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(3.0F, 12.0F).sound(SoundType.STONE)));
    public static final RegistryObject<SlabBlock> MARBLE_ENCHANTED_SLAB = BLOCKS.register("marble_enchanted_slab", () -> new SlabBlock(Block.Properties.from(MARBLE_ENCHANTED.get())));
    public static final RegistryObject<StairsBlock> MARBLE_ENCHANTED_STAIRS = BLOCKS.register("marble_enchanted_stairs", () -> new StairsBlock(MARBLE_ENCHANTED.get()::getDefaultState, Block.Properties.from(MARBLE_ENCHANTED.get())));
    public static final RegistryObject<WallBlock> MARBLE_ENCHANTED_WALL = BLOCKS.register("marble_enchanted_wall", () -> new WallBlock(Block.Properties.from(MARBLE_ENCHANTED.get())));
    public static final RegistryObject<Block> MARBLE_ENCHANTED_BRICKS = BLOCKS.register("marble_enchanted_bricks", () -> new Block(Block.Properties.from(MARBLE_ENCHANTED.get())));
    public static final RegistryObject<SlabBlock> MARBLE_ENCHANTED_BRICK_SLAB = BLOCKS.register("marble_enchanted_brick_slab", () -> new SlabBlock(Block.Properties.from(MARBLE_ENCHANTED_BRICKS.get())));
    public static final RegistryObject<StairsBlock> MARBLE_ENCHANTED_BRICK_STAIRS = BLOCKS.register("marble_enchanted_brick_stairs", () -> new StairsBlock(MARBLE_ENCHANTED_BRICKS.get()::getDefaultState, Block.Properties.from(MARBLE_ENCHANTED_BRICKS.get())));
    public static final RegistryObject<WallBlock> MARBLE_ENCHANTED_BRICK_WALL = BLOCKS.register("marble_enchanted_brick_wall", () -> new WallBlock(Block.Properties.from(MARBLE_ENCHANTED_BRICKS.get())));
    public static final RegistryObject<PillarBlock> MARBLE_ENCHANTED_PILLAR = BLOCKS.register("marble_enchanted_pillar", () -> new PillarBlock(Block.Properties.from(MARBLE_ENCHANTED.get())));
    public static final RegistryObject<Block> MARBLE_ENCHANTED_CHISELED = BLOCKS.register("marble_enchanted_chiseled", () -> new Block(Block.Properties.from(MARBLE_ENCHANTED.get())));
    public static final RegistryObject<Block> MARBLE_ENCHANTED_RUNED = BLOCKS.register("marble_enchanted_runed", () -> new Block(Block.Properties.from(MARBLE_ENCHANTED.get())));
    
    // Register smoked marble blocks
    public static final RegistryObject<Block> MARBLE_SMOKED = BLOCKS.register("marble_smoked", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<SlabBlock> MARBLE_SMOKED_SLAB = BLOCKS.register("marble_smoked_slab", () -> new SlabBlock(Block.Properties.from(MARBLE_SMOKED.get())));
    public static final RegistryObject<StairsBlock> MARBLE_SMOKED_STAIRS = BLOCKS.register("marble_smoked_stairs", () -> new StairsBlock(MARBLE_SMOKED.get()::getDefaultState, Block.Properties.from(MARBLE_SMOKED.get())));
    public static final RegistryObject<WallBlock> MARBLE_SMOKED_WALL = BLOCKS.register("marble_smoked_wall", () -> new WallBlock(Block.Properties.from(MARBLE_SMOKED.get())));
    public static final RegistryObject<Block> MARBLE_SMOKED_BRICKS = BLOCKS.register("marble_smoked_bricks", () -> new Block(Block.Properties.from(MARBLE_SMOKED.get())));
    public static final RegistryObject<SlabBlock> MARBLE_SMOKED_BRICK_SLAB = BLOCKS.register("marble_smoked_brick_slab", () -> new SlabBlock(Block.Properties.from(MARBLE_SMOKED_BRICKS.get())));
    public static final RegistryObject<StairsBlock> MARBLE_SMOKED_BRICK_STAIRS = BLOCKS.register("marble_smoked_brick_stairs", () -> new StairsBlock(MARBLE_SMOKED_BRICKS.get()::getDefaultState, Block.Properties.from(MARBLE_SMOKED_BRICKS.get())));
    public static final RegistryObject<WallBlock> MARBLE_SMOKED_BRICK_WALL = BLOCKS.register("marble_smoked_brick_wall", () -> new WallBlock(Block.Properties.from(MARBLE_SMOKED_BRICKS.get())));
    public static final RegistryObject<PillarBlock> MARBLE_SMOKED_PILLAR = BLOCKS.register("marble_smoked_pillar", () -> new PillarBlock(Block.Properties.from(MARBLE_SMOKED.get())));
    public static final RegistryObject<Block> MARBLE_SMOKED_CHISELED = BLOCKS.register("marble_smoked_chiseled", () -> new Block(Block.Properties.from(MARBLE_SMOKED.get())));
    public static final RegistryObject<Block> MARBLE_SMOKED_RUNED = BLOCKS.register("marble_smoked_runed", () -> new Block(Block.Properties.from(MARBLE_SMOKED.get())));
    
    // Register sunwood blocks
    public static final RegistryObject<SunwoodLogBlock> STRIPPED_SUNWOOD_LOG = BLOCKS.register("stripped_sunwood_log", () -> new SunwoodLogBlock(null));
    public static final RegistryObject<SunwoodLogBlock> SUNWOOD_LOG = BLOCKS.register("sunwood_log", () -> new SunwoodLogBlock(STRIPPED_SUNWOOD_LOG.get()));
    public static final RegistryObject<SunwoodLogBlock> STRIPPED_SUNWOOD_WOOD = BLOCKS.register("stripped_sunwood_wood", () -> new SunwoodLogBlock(null));
    public static final RegistryObject<SunwoodLogBlock> SUNWOOD_WOOD = BLOCKS.register("sunwood_wood", () -> new SunwoodLogBlock(STRIPPED_SUNWOOD_WOOD.get()));
    public static final RegistryObject<SunwoodLeavesBlock> SUNWOOD_LEAVES = BLOCKS.register("sunwood_leaves", SunwoodLeavesBlock::new);
    public static final RegistryObject<SaplingBlockPM> SUNWOOD_SAPLING = BLOCKS.register("sunwood_sapling", () -> new SaplingBlockPM(new SunwoodTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
    public static final RegistryObject<SunwoodPlanksBlock> SUNWOOD_PLANKS = BLOCKS.register("sunwood_planks", SunwoodPlanksBlock::new);
    public static final RegistryObject<SunwoodSlabBlock> SUNWOOD_SLAB = BLOCKS.register("sunwood_slab", () -> new SunwoodSlabBlock(Block.Properties.from(SUNWOOD_PLANKS.get())));
    public static final RegistryObject<SunwoodStairsBlock> SUNWOOD_STAIRS = BLOCKS.register("sunwood_stairs", () -> new SunwoodStairsBlock(SUNWOOD_PLANKS.get()::getDefaultState, Block.Properties.from(SUNWOOD_PLANKS.get())));
    public static final RegistryObject<SunwoodPillarBlock> SUNWOOD_PILLAR = BLOCKS.register("sunwood_pillar", SunwoodPillarBlock::new);
    
    // Register moonwood blocks
    public static final RegistryObject<MoonwoodLogBlock> STRIPPED_MOONWOOD_LOG = BLOCKS.register("stripped_moonwood_log", () -> new MoonwoodLogBlock(null));
    public static final RegistryObject<MoonwoodLogBlock> MOONWOOD_LOG = BLOCKS.register("moonwood_log", () -> new MoonwoodLogBlock(STRIPPED_MOONWOOD_LOG.get()));
    public static final RegistryObject<MoonwoodLogBlock> STRIPPED_MOONWOOD_WOOD = BLOCKS.register("stripped_moonwood_wood", () -> new MoonwoodLogBlock(null));
    public static final RegistryObject<MoonwoodLogBlock> MOONWOOD_WOOD = BLOCKS.register("moonwood_wood", () -> new MoonwoodLogBlock(STRIPPED_MOONWOOD_WOOD.get()));
    public static final RegistryObject<MoonwoodLeavesBlock> MOONWOOD_LEAVES = BLOCKS.register("moonwood_leaves", MoonwoodLeavesBlock::new);
    public static final RegistryObject<SaplingBlockPM> MOONWOOD_SAPLING = BLOCKS.register("moonwood_sapling", () -> new SaplingBlockPM(new MoonwoodTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)));
    public static final RegistryObject<MoonwoodPlanksBlock> MOONWOOD_PLANKS = BLOCKS.register("moonwood_planks", MoonwoodPlanksBlock::new);
    public static final RegistryObject<MoonwoodSlabBlock> MOONWOOD_SLAB = BLOCKS.register("moonwood_slab", () -> new MoonwoodSlabBlock(Block.Properties.from(MOONWOOD_PLANKS.get())));
    public static final RegistryObject<MoonwoodStairsBlock> MOONWOOD_STAIRS = BLOCKS.register("moonwood_stairs", () -> new MoonwoodStairsBlock(MOONWOOD_PLANKS.get()::getDefaultState, Block.Properties.from(MOONWOOD_PLANKS.get())));
    public static final RegistryObject<MoonwoodPillarBlock> MOONWOOD_PILLAR = BLOCKS.register("moonwood_pillar", MoonwoodPillarBlock::new);
    
    // Register infused stone
    public static final RegistryObject<Block> INFUSED_STONE_EARTH = BLOCKS.register("infused_stone_earth", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> INFUSED_STONE_SEA = BLOCKS.register("infused_stone_sea", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> INFUSED_STONE_SKY = BLOCKS.register("infused_stone_sky", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> INFUSED_STONE_SUN = BLOCKS.register("infused_stone_sun", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> INFUSED_STONE_MOON = BLOCKS.register("infused_stone_moon", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)));
    
    // Register mana fonts
    public static final RegistryObject<AncientManaFontBlock> ANCIENT_FONT_EARTH = BLOCKS.register("ancient_font_earth", () -> new AncientManaFontBlock(Source.EARTH));
    public static final RegistryObject<AncientManaFontBlock> ANCIENT_FONT_SEA = BLOCKS.register("ancient_font_sea", () -> new AncientManaFontBlock(Source.SEA));
    public static final RegistryObject<AncientManaFontBlock> ANCIENT_FONT_SKY = BLOCKS.register("ancient_font_sky", () -> new AncientManaFontBlock(Source.SKY));
    public static final RegistryObject<AncientManaFontBlock> ANCIENT_FONT_SUN = BLOCKS.register("ancient_font_sun", () -> new AncientManaFontBlock(Source.SUN));
    public static final RegistryObject<AncientManaFontBlock> ANCIENT_FONT_MOON = BLOCKS.register("ancient_font_moon", () -> new AncientManaFontBlock(Source.MOON));

    // Register devices
    public static final RegistryObject<ArcaneWorkbenchBlock> ARCANE_WORKBENCH = BLOCKS.register("arcane_workbench", ArcaneWorkbenchBlock::new);
    public static final RegistryObject<WandAssemblyTableBlock> WAND_ASSEMBLY_TABLE = BLOCKS.register("wand_assembly_table", WandAssemblyTableBlock::new);
    public static final RegistryObject<WoodTableBlock> WOOD_TABLE = BLOCKS.register("wood_table", WoodTableBlock::new);
    public static final RegistryObject<AnalysisTableBlock> ANALYSIS_TABLE = BLOCKS.register("analysis_table", AnalysisTableBlock::new);
    public static final RegistryObject<CalcinatorBlock> CALCINATOR = BLOCKS.register("calcinator", CalcinatorBlock::new);
    public static final RegistryObject<WandInscriptionTableBlock> WAND_INSCRIPTION_TABLE = BLOCKS.register("wand_inscription_table", WandInscriptionTableBlock::new);
    public static final RegistryObject<SpellcraftingAltarBlock> SPELLCRAFTING_ALTAR = BLOCKS.register("spellcrafting_altar", SpellcraftingAltarBlock::new);
    public static final RegistryObject<WandChargerBlock> WAND_CHARGER = BLOCKS.register("wand_charger", WandChargerBlock::new);
    
    // Register misc blocks
    public static final RegistryObject<ConsecrationFieldBlock> CONSECRATION_FIELD = BLOCKS.register("consecration_field", ConsecrationFieldBlock::new);
}
