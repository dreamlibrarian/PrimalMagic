package com.verdantartifice.primalmagic.datagen;

import com.verdantartifice.primalmagic.common.blocks.BlocksPM;
import com.verdantartifice.primalmagic.common.items.ItemsPM;

import net.minecraft.data.DataGenerator;

/**
 * Data provider for all of the mod's block loot tables.
 * 
 * @author Daedalus4096
 */
public class BlockLootTables extends BlockLootTableProvider {
    public BlockLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        this.registerMarbleLootTables();
        this.registerEnchantedMarbleLootTables();
        this.registerSmokedMarbleLootTables();
        this.registerSunwoodLootTables();
        this.registerMoonwoodLootTables();
        this.registerInfusedStoneLootTables();
        
        this.registerBasicTable(BlocksPM.ANALYSIS_TABLE.get());
        this.registerBasicTable(BlocksPM.ARCANE_WORKBENCH.get());
        this.registerBasicTable(BlocksPM.WAND_ASSEMBLY_TABLE.get());
        this.registerBasicTable(BlocksPM.WOOD_TABLE.get());
        this.registerBasicTable(BlocksPM.CALCINATOR.get());
    }
    
    private void registerMarbleLootTables() {
        this.registerBasicTable(BlocksPM.MARBLE_RAW.get());
        this.registerSlabTable(BlocksPM.MARBLE_BRICK_SLAB.get());
        this.registerBasicTable(BlocksPM.MARBLE_BRICK_STAIRS.get());
        this.registerBasicTable(BlocksPM.MARBLE_BRICK_WALL.get());
        this.registerBasicTable(BlocksPM.MARBLE_BRICKS.get());
        this.registerBasicTable(BlocksPM.MARBLE_CHISELED.get());
        this.registerBasicTable(BlocksPM.MARBLE_PILLAR.get());
        this.registerBasicTable(BlocksPM.MARBLE_RUNED.get());
        this.registerSlabTable(BlocksPM.MARBLE_SLAB.get());
        this.registerBasicTable(BlocksPM.MARBLE_STAIRS.get());
        this.registerBasicTable(BlocksPM.MARBLE_WALL.get());
    }
    
    private void registerEnchantedMarbleLootTables() {
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED.get());
        this.registerSlabTable(BlocksPM.MARBLE_ENCHANTED_BRICK_SLAB.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_BRICK_STAIRS.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_BRICK_WALL.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_BRICKS.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_CHISELED.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_PILLAR.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_RUNED.get());
        this.registerSlabTable(BlocksPM.MARBLE_ENCHANTED_SLAB.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_STAIRS.get());
        this.registerBasicTable(BlocksPM.MARBLE_ENCHANTED_WALL.get());
    }
    
    private void registerSmokedMarbleLootTables() {
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED.get());
        this.registerSlabTable(BlocksPM.MARBLE_SMOKED_BRICK_SLAB.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_BRICK_STAIRS.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_BRICK_WALL.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_BRICKS.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_CHISELED.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_PILLAR.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_RUNED.get());
        this.registerSlabTable(BlocksPM.MARBLE_SMOKED_SLAB.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_STAIRS.get());
        this.registerBasicTable(BlocksPM.MARBLE_SMOKED_WALL.get());
    }
    
    private void registerSunwoodLootTables() {
        this.registerBasicTable(BlocksPM.SUNWOOD_LOG.get());
        this.registerBasicTable(BlocksPM.STRIPPED_SUNWOOD_LOG.get());
        this.registerBasicTable(BlocksPM.SUNWOOD_WOOD.get());
        this.registerBasicTable(BlocksPM.STRIPPED_SUNWOOD_WOOD.get());
        this.registerLeavesTable(BlocksPM.SUNWOOD_LEAVES.get(), BlocksPM.SUNWOOD_SAPLING.get());
        this.registerBasicTable(BlocksPM.SUNWOOD_SAPLING.get());
        this.registerBasicTable(BlocksPM.SUNWOOD_PLANKS.get());
        this.registerSlabTable(BlocksPM.SUNWOOD_SLAB.get());
        this.registerBasicTable(BlocksPM.SUNWOOD_STAIRS.get());
        this.registerBasicTable(BlocksPM.SUNWOOD_PILLAR.get());
    }
    
    private void registerMoonwoodLootTables() {
        this.registerBasicTable(BlocksPM.MOONWOOD_LOG.get());
        this.registerBasicTable(BlocksPM.STRIPPED_MOONWOOD_LOG.get());
        this.registerBasicTable(BlocksPM.MOONWOOD_WOOD.get());
        this.registerBasicTable(BlocksPM.STRIPPED_MOONWOOD_WOOD.get());
        this.registerLeavesTable(BlocksPM.MOONWOOD_LEAVES.get(), BlocksPM.MOONWOOD_SAPLING.get());
        this.registerBasicTable(BlocksPM.MOONWOOD_SAPLING.get());
        this.registerBasicTable(BlocksPM.MOONWOOD_PLANKS.get());
        this.registerSlabTable(BlocksPM.MOONWOOD_SLAB.get());
        this.registerBasicTable(BlocksPM.MOONWOOD_STAIRS.get());
        this.registerBasicTable(BlocksPM.MOONWOOD_PILLAR.get());
    }
    
    private void registerInfusedStoneLootTables() {
        this.registerInfusedStoneTable(BlocksPM.INFUSED_STONE_EARTH.get(), ItemsPM.ESSENCE_DUST_EARTH.get());
        this.registerInfusedStoneTable(BlocksPM.INFUSED_STONE_SEA.get(), ItemsPM.ESSENCE_DUST_SEA.get());
        this.registerInfusedStoneTable(BlocksPM.INFUSED_STONE_SKY.get(), ItemsPM.ESSENCE_DUST_SKY.get());
        this.registerInfusedStoneTable(BlocksPM.INFUSED_STONE_SUN.get(), ItemsPM.ESSENCE_DUST_SUN.get());
        this.registerInfusedStoneTable(BlocksPM.INFUSED_STONE_MOON.get(), ItemsPM.ESSENCE_DUST_MOON.get());
    }

    @Override
    public String getName() {
        return "Primal Magic Block Loot Tables";
    }
}
