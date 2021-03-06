package com.verdantartifice.primalmagic.datagen;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.verdantartifice.primalmagic.PrimalMagic;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ConstantRange;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.conditions.TableBonus;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import net.minecraft.world.storage.loot.functions.SetCount;

/**
 * Base class for the block loot table provider for the mod.  Handles the infrastructure so that the
 * subclass can just list registrations.
 * 
 * @author Daedalus4096
 */
public abstract class BlockLootTableProvider extends LootTableProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    
    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    
    private final DataGenerator generator;

    public BlockLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    /**
     * Add the mod's block loot tables to this provider's map for writing.
     */
    protected abstract void addTables();
    
    protected void registerBasicTable(Block block) {
        LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block))
                .acceptCondition(SurvivesExplosion.builder());
        LootTable.Builder tableBuilder = LootTable.builder().addLootPool(poolBuilder);
        this.lootTables.put(block, tableBuilder);
    }
    
    protected void registerSlabTable(Block block) {
        LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block)
                .acceptFunction(SetCount.builder(ConstantRange.of(2))
                    .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withProp(SlabBlock.TYPE, SlabType.DOUBLE)))
                ).acceptFunction(ExplosionDecay.builder()));
        LootTable.Builder tableBuilder = LootTable.builder().addLootPool(poolBuilder);
        this.lootTables.put(block, tableBuilder);
    }
    
    protected void registerLeavesTable(Block leavesBlock, Block saplingBlock) {
        float[] saplingFortuneChances = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };
        float[] stickFortuneChances = new float[] { 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F };
        ILootCondition.IBuilder shearsOrSilkTouch = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))
                .alternative(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))));
        LootEntry.Builder<?> saplingEntryBuilder = ItemLootEntry.builder(saplingBlock).acceptCondition(SurvivesExplosion.builder()).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, saplingFortuneChances));
        LootEntry.Builder<?> leavesEntryBuilder = ItemLootEntry.builder(leavesBlock).acceptCondition(shearsOrSilkTouch);
        LootPool.Builder saplingAndLeavesPool = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(leavesEntryBuilder.alternatively(saplingEntryBuilder));
        LootEntry.Builder<?> stickEntryBuilder = ItemLootEntry.builder(Items.STICK).acceptFunction(SetCount.builder(RandomValueRange.of(1.0F, 2.0F))).acceptFunction(ExplosionDecay.builder()).acceptCondition(TableBonus.builder(Enchantments.FORTUNE, stickFortuneChances));
        LootPool.Builder stickPool = LootPool.builder().rolls(ConstantRange.of(1)).acceptCondition(shearsOrSilkTouch.inverted()).addEntry(stickEntryBuilder);
        LootTable.Builder tableBuilder = LootTable.builder().addLootPool(saplingAndLeavesPool).addLootPool(stickPool);
        this.lootTables.put(leavesBlock, tableBuilder);
    }
    
    protected void registerInfusedStoneTable(Block stoneBlock, Item dustItem) {
        LootPool.Builder stonePoolBuilder = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(Blocks.COBBLESTONE))
                .acceptCondition(SurvivesExplosion.builder());
        LootPool.Builder dustPoolBuilder = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(dustItem))
                .acceptCondition(SurvivesExplosion.builder());
        LootTable.Builder tableBuilder = LootTable.builder().addLootPool(stonePoolBuilder).addLootPool(dustPoolBuilder);
        this.lootTables.put(stoneBlock, tableBuilder);
    }
    
    @Override
    public void act(DirectoryCache cache) {
        // Register all the loot tables with this provider
        this.addTables();
        
        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : this.lootTables.entrySet()) {
            // For each entry in the map, build the loot table and associate it with the block's loot table location
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        
        // Write out the loot table files to disk
        this.writeTables(cache, tables);
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                PrimalMagic.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }
}
