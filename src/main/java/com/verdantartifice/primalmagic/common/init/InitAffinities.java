package com.verdantartifice.primalmagic.common.init;

import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.common.blocks.BlocksPM;
import com.verdantartifice.primalmagic.common.items.ItemsPM;
import com.verdantartifice.primalmagic.common.items.essence.EssenceItem;
import com.verdantartifice.primalmagic.common.sources.AffinityManager;
import com.verdantartifice.primalmagic.common.sources.Source;
import com.verdantartifice.primalmagic.common.sources.SourceList;
import com.verdantartifice.primalmagic.common.tags.ItemTagsPM;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

/**
 * Point of registration for primal affinities on blocks and items.
 * 
 * @author Daedalus4096
 */
public class InitAffinities {
    public static void initAffinities(MinecraftServer server) {
        initItemAffinities(server);
        initPotionBonusAffinities();
        initEnchantmentBonusAffinities();
    }
    
    protected static void initItemAffinities(MinecraftServer server) {
        // Register Vanilla item tag affinities
        AffinityManager.registerItemTagAffinities(new ResourceLocation("beds"), new SourceList().add(Source.EARTH, 4).add(Source.SUN, 4).add(Source.BLOOD, 11));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("coals"), new SourceList().add(Source.EARTH, 5).add(Source.INFERNAL, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("fishes"), new SourceList().add(Source.SEA, 5).add(Source.BLOOD, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("leaves"), new SourceList().add(Source.EARTH, 5).add(Source.SKY, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("logs"), new SourceList().add(Source.EARTH, 10).add(Source.SUN, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("planks"), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("sand"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("saplings"), new SourceList().add(Source.EARTH, 10).add(Source.SUN, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("small_flowers"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("stone_bricks"), new SourceList().add(Source.EARTH, 3));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("wooden_slabs"), new SourceList().add(Source.EARTH, 1).add(Source.SUN, 1));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("wool"), new SourceList().add(Source.BLOOD, 5));
        
        // Register Vanilla block tag affinities
        AffinityManager.registerBlockTagAffinities(new ResourceLocation("anvil"), new SourceList().add(Source.EARTH, 180));
        AffinityManager.registerBlockTagAffinities(new ResourceLocation("coral_blocks"), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 10).add(Source.SUN, 5));
        AffinityManager.registerBlockTagAffinities(new ResourceLocation("corals"), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 5).add(Source.SUN, 5));
        AffinityManager.registerBlockTagAffinities(new ResourceLocation("flower_pots"), new SourceList().add(Source.EARTH, 10).add(Source.SUN, 5));
        AffinityManager.registerBlockTagAffinities(new ResourceLocation("ice"), new SourceList().add(Source.SEA, 5));
        
        // Register Forge item tag affinities
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "cobblestone"), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "dyes"), new SourceList().add(Source.EARTH, 1).add(Source.SUN, 1));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "eggs"), new SourceList().add(Source.SKY, 2).add(Source.BLOOD, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "end_stones"), new SourceList().add(Source.EARTH, 5).add(Source.VOID, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ender_pearls"), new SourceList().add(Source.VOID, 20));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "feathers"), new SourceList().add(Source.SKY, 10).add(Source.BLOOD, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "gravel"), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "gunpowder"), new SourceList().add(Source.EARTH, 5).add(Source.INFERNAL, 15));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "heads"), new SourceList().add(Source.BLOOD, 20));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "leather"), new SourceList().add(Source.BLOOD, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "mushrooms"), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "nether_stars"), new SourceList().add(Source.EARTH, 10).add(Source.SEA, 10).add(Source.SKY, 10).add(Source.SUN, 10).add(Source.MOON, 10).add(Source.BLOOD, 10).add(Source.INFERNAL, 10).add(Source.VOID, 10).add(Source.HALLOWED, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "netherrack"), new SourceList().add(Source.EARTH, 5).add(Source.INFERNAL, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "obsidian"), new SourceList().add(Source.EARTH, 5).add(Source.INFERNAL, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "sandstone"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "slimeballs"), new SourceList().add(Source.SEA, 5).add(Source.BLOOD, 2));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "stone"), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "string"), new SourceList().add(Source.SKY, 5).add(Source.BLOOD, 2));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "crops/beetroot"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "crops/carrot"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "crops/nether_wart"), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5).add(Source.INFERNAL, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "crops/potato"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "crops/wheat"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "dusts/glowstone"), new SourceList().add(Source.SUN, 10).add(Source.INFERNAL, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "dusts/prismarine"), new SourceList().add(Source.EARTH, 2).add(Source.SEA, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "dusts/redstone"), new SourceList().add(Source.EARTH, 5));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "gems/diamond"), new SourceList().add(Source.EARTH, 20));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "gems/emerald"), new SourceList().add(Source.EARTH, 20));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "gems/lapis"), new SourceList().add(Source.EARTH, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "gems/prismarine"), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "gems/quartz"), new SourceList().add(Source.EARTH, 10).add(Source.INFERNAL, 5));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ingots/brick"), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ingots/gold"), new SourceList().add(Source.EARTH, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ingots/iron"), new SourceList().add(Source.EARTH, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ingots/nether_brick"), new SourceList().add(Source.EARTH, 5).add(Source.INFERNAL, 5));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/coal"), new SourceList().add(Source.EARTH, 10).add(Source.INFERNAL, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/diamond"), new SourceList().add(Source.EARTH, 25));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/emerald"), new SourceList().add(Source.EARTH, 25));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/gold"), new SourceList().add(Source.EARTH, 15));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/iron"), new SourceList().add(Source.EARTH, 15));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/lapis"), new SourceList().add(Source.EARTH, 15));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/quartz"), new SourceList().add(Source.EARTH, 15).add(Source.INFERNAL, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "ores/redstone"), new SourceList().add(Source.EARTH, 10));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "rods/blaze"), new SourceList().add(Source.INFERNAL, 20));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "rods/wooden"), new SourceList().add(Source.EARTH, 1).add(Source.SUN, 1));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "sand/colorless"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "sand/red"), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "seeds/beetroot"), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "seeds/melon"), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "seeds/pumpkin"), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerItemTagAffinities(new ResourceLocation("forge", "seeds/wheat"), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        
        // Register Forge block tag affinities
        AffinityManager.registerBlockTagAffinities(new ResourceLocation("forge", "dirt"), new SourceList().add(Source.EARTH, 5));
        
        // Register Vanilla block affinities
        AffinityManager.registerAffinities(new ItemStack(Blocks.GRASS_BLOCK), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BEDROCK), new SourceList().add(Source.EARTH, 20).add(Source.VOID, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.WATER), new SourceList().add(Source.SEA, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.LAVA), new SourceList().add(Source.INFERNAL, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SPONGE), new SourceList().add(Source.SEA, 10).add(Source.VOID, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.WET_SPONGE), new SourceList().add(Source.SEA, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.COBWEB), new SourceList().add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.GRASS), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.FERN), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.DEAD_BUSH), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SEAGRASS), new SourceList().add(Source.EARTH, 2).add(Source.SEA, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.TALL_SEAGRASS), new SourceList().add(Source.EARTH, 2).add(Source.SEA, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.WITHER_ROSE), new SourceList().add(Source.EARTH, 5).add(Source.INFERNAL, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.MOSSY_COBBLESTONE), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.TORCH), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.WALL_TORCH), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.FIRE), new SourceList().add(Source.INFERNAL, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SPAWNER), new SourceList().add(Source.BLOOD, 20).add(Source.INFERNAL, 10).add(Source.VOID, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.REDSTONE_WIRE), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.WHEAT), AffinityManager.getAffinities(new ItemStack(Items.WHEAT), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.FARMLAND), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 2).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SNOW), new SourceList().add(Source.SEA, 2).add(Source.SKY, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.CACTUS), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SUGAR_CANE), new SourceList().add(Source.EARTH, 5).add(Source.SKY, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.PUMPKIN), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SOUL_SAND), new SourceList().add(Source.EARTH, 5).add(Source.INFERNAL, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.GLOWSTONE), new SourceList().add(Source.SUN, 20).add(Source.INFERNAL, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.NETHER_PORTAL), new SourceList().add(Source.INFERNAL, 20).add(Source.VOID, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.CARVED_PUMPKIN), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.MOSSY_STONE_BRICKS), new SourceList().add(Source.EARTH, 3).add(Source.MOON, 3));
        AffinityManager.registerAffinities(new ItemStack(Blocks.INFESTED_STONE), AffinityManager.getAffinities(new ItemStack(Blocks.STONE), server).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.INFESTED_COBBLESTONE), AffinityManager.getAffinities(new ItemStack(Blocks.COBBLESTONE), server).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.INFESTED_STONE_BRICKS), AffinityManager.getAffinities(new ItemStack(Blocks.STONE_BRICKS), server).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.INFESTED_MOSSY_STONE_BRICKS), AffinityManager.getAffinities(new ItemStack(Blocks.MOSSY_STONE_BRICKS), server).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.INFESTED_CRACKED_STONE_BRICKS), AffinityManager.getAffinities(new ItemStack(Blocks.CRACKED_STONE_BRICKS), server).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.INFESTED_CHISELED_STONE_BRICKS), AffinityManager.getAffinities(new ItemStack(Blocks.CHISELED_STONE_BRICKS), server).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.RED_MUSHROOM_BLOCK), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.MUSHROOM_STEM), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.MELON), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.ATTACHED_PUMPKIN_STEM), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.ATTACHED_MELON_STEM), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.PUMPKIN_STEM), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.MELON_STEM), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.VINE), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.MYCELIUM), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.LILY_PAD), new SourceList().add(Source.EARTH, 2).add(Source.SEA, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.END_PORTAL), new SourceList().add(Source.VOID, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.END_PORTAL_FRAME), new SourceList().add(Source.EARTH, 5).add(Source.VOID, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.DRAGON_EGG), new SourceList().add(Source.BLOOD, 20).add(Source.VOID, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.COCOA), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.FLOWER_POT), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_OAK_SAPLING), AffinityManager.getAffinities(new ItemStack(Items.OAK_SAPLING), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_SPRUCE_SAPLING), AffinityManager.getAffinities(new ItemStack(Items.SPRUCE_SAPLING), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_BIRCH_SAPLING), AffinityManager.getAffinities(new ItemStack(Items.BIRCH_SAPLING), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_JUNGLE_SAPLING), AffinityManager.getAffinities(new ItemStack(Items.JUNGLE_SAPLING), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_ACACIA_SAPLING), AffinityManager.getAffinities(new ItemStack(Items.ACACIA_SAPLING), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_DARK_OAK_SAPLING), AffinityManager.getAffinities(new ItemStack(Items.DARK_OAK_SAPLING), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_FERN), AffinityManager.getAffinities(new ItemStack(Blocks.FERN), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_WITHER_ROSE), AffinityManager.getAffinities(new ItemStack(Blocks.WITHER_ROSE), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_RED_MUSHROOM), AffinityManager.getAffinities(new ItemStack(Blocks.RED_MUSHROOM), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_BROWN_MUSHROOM), AffinityManager.getAffinities(new ItemStack(Blocks.BROWN_MUSHROOM), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_DEAD_BUSH), AffinityManager.getAffinities(new ItemStack(Blocks.DEAD_BUSH), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.CARROTS), AffinityManager.getAffinities(new ItemStack(Items.CARROT), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTATOES), AffinityManager.getAffinities(new ItemStack(Items.POTATO), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SKELETON_SKULL), new SourceList().add(Source.MOON, 5).add(Source.BLOOD, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SKELETON_WALL_SKULL), AffinityManager.getAffinities(new ItemStack(Blocks.SKELETON_SKULL), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.WITHER_SKELETON_SKULL), new SourceList().add(Source.BLOOD, 20).add(Source.INFERNAL, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.WITHER_SKELETON_WALL_SKULL), AffinityManager.getAffinities(new ItemStack(Blocks.WITHER_SKELETON_SKULL), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.ZOMBIE_HEAD), new SourceList().add(Source.MOON, 5).add(Source.BLOOD, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.ZOMBIE_WALL_HEAD), AffinityManager.getAffinities(new ItemStack(Blocks.ZOMBIE_HEAD), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.PLAYER_WALL_HEAD), AffinityManager.getAffinities(new ItemStack(Blocks.PLAYER_HEAD), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.CREEPER_WALL_HEAD), AffinityManager.getAffinities(new ItemStack(Blocks.CREEPER_HEAD), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.DRAGON_HEAD), new SourceList().add(Source.BLOOD, 20).add(Source.VOID, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.DRAGON_WALL_HEAD), AffinityManager.getAffinities(new ItemStack(Blocks.DRAGON_HEAD), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.PACKED_ICE), new SourceList().add(Source.SEA, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SUNFLOWER), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.LILAC), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.ROSE_BUSH), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.PEONY), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.TALL_GRASS), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.LARGE_FERN), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.CHORUS_PLANT), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5).add(Source.VOID, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.CHORUS_FLOWER), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5).add(Source.VOID, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.PURPUR_BLOCK), new SourceList().add(Source.EARTH, 5).add(Source.VOID, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BEETROOTS), AffinityManager.getAffinities(new ItemStack(Items.BEETROOT), server));
        AffinityManager.registerAffinities(new ItemStack(Blocks.GRASS_PATH), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Blocks.END_GATEWAY), new SourceList().add(Source.VOID, 20));
        AffinityManager.registerAffinities(new ItemStack(Blocks.MAGMA_BLOCK), new SourceList().add(Source.INFERNAL, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BONE_BLOCK), new SourceList().add(Source.MOON, 5).add(Source.BLOOD, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.KELP), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.KELP_PLANT), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.TURTLE_EGG), new SourceList().add(Source.SEA, 5).add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SEA_PICKLE), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 5).add(Source.SUN, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BAMBOO_SAPLING), new SourceList().add(Source.EARTH, 10).add(Source.SKY, 10).add(Source.SUN, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BAMBOO), new SourceList().add(Source.EARTH, 5).add(Source.SKY, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.POTTED_BAMBOO), AffinityManager.getAffinities(new ItemStack(Blocks.BAMBOO), server).add(AffinityManager.getAffinities(new ItemStack(Blocks.FLOWER_POT), server)));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BELL), new SourceList().add(Source.EARTH, 10));
        AffinityManager.registerAffinities(new ItemStack(Blocks.SWEET_BERRY_BUSH), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Blocks.BEE_NEST), new SourceList().add(Source.EARTH, 9).add(Source.SEA, 11).add(Source.SUN, 9).add(Source.BLOOD, 4));  // copy the beehive sources
        
        // Register Vanilla item affinities
        AffinityManager.registerAffinities(new ItemStack(Items.SCUTE), new SourceList().add(Source.SEA, 2).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.APPLE), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.CHAINMAIL_HELMET), AffinityManager.getAffinities(new ItemStack(Items.IRON_HELMET), server));
        AffinityManager.registerAffinities(new ItemStack(Items.CHAINMAIL_CHESTPLATE), AffinityManager.getAffinities(new ItemStack(Items.IRON_CHESTPLATE), server));
        AffinityManager.registerAffinities(new ItemStack(Items.CHAINMAIL_LEGGINGS), AffinityManager.getAffinities(new ItemStack(Items.IRON_LEGGINGS), server));
        AffinityManager.registerAffinities(new ItemStack(Items.CHAINMAIL_BOOTS), AffinityManager.getAffinities(new ItemStack(Items.IRON_BOOTS), server));
        AffinityManager.registerAffinities(new ItemStack(Items.FLINT), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.PORKCHOP), new SourceList().add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), AffinityManager.getAffinities(new ItemStack(Items.GOLDEN_APPLE), server).add(Source.HALLOWED, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.WATER_BUCKET), AffinityManager.getAffinities(new ItemStack(Items.BUCKET), server).add(new SourceList().add(Source.SEA, 20)));
        AffinityManager.registerAffinities(new ItemStack(Items.LAVA_BUCKET), AffinityManager.getAffinities(new ItemStack(Items.BUCKET), server).add(new SourceList().add(Source.INFERNAL, 20)));
        AffinityManager.registerAffinities(new ItemStack(Items.SADDLE), new SourceList().add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.SNOWBALL), new SourceList().add(Source.SEA, 2).add(Source.SKY, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.MILK_BUCKET), AffinityManager.getAffinities(new ItemStack(Items.BUCKET), server).add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.PUFFERFISH_BUCKET), AffinityManager.getAffinities(new ItemStack(Items.BUCKET), server).add(AffinityManager.getAffinities(new ItemStack(Items.PUFFERFISH), server)));
        AffinityManager.registerAffinities(new ItemStack(Items.SALMON_BUCKET), AffinityManager.getAffinities(new ItemStack(Items.BUCKET), server).add(AffinityManager.getAffinities(new ItemStack(Items.SALMON), server)));
        AffinityManager.registerAffinities(new ItemStack(Items.COD_BUCKET), AffinityManager.getAffinities(new ItemStack(Items.BUCKET), server).add(AffinityManager.getAffinities(new ItemStack(Items.COD), server)));
        AffinityManager.registerAffinities(new ItemStack(Items.TROPICAL_FISH_BUCKET), AffinityManager.getAffinities(new ItemStack(Items.BUCKET), server).add(AffinityManager.getAffinities(new ItemStack(Items.TROPICAL_FISH), server)));
        AffinityManager.registerAffinities(new ItemStack(Items.CLAY_BALL), new SourceList().add(Source.EARTH, 2).add(Source.SEA, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.INK_SAC), new SourceList().add(Source.SEA, 5).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.COCOA_BEANS), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.BONE_MEAL), new SourceList().add(Source.MOON, 1).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.BONE), new SourceList().add(Source.MOON, 5).add(Source.BLOOD, 10));
        AffinityManager.registerAffinities(new ItemStack(Items.FILLED_MAP), AffinityManager.getAffinities(new ItemStack(Items.MAP), server));
        AffinityManager.registerAffinities(new ItemStack(Items.MELON_SLICE), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.BEEF), new SourceList().add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.CHICKEN), new SourceList().add(Source.SKY, 2).add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.ROTTEN_FLESH), new SourceList().add(Source.MOON, 5).add(Source.BLOOD, 10));
        AffinityManager.registerAffinities(new ItemStack(Items.GHAST_TEAR), new SourceList().add(Source.SKY, 5).add(Source.BLOOD, 5).add(Source.INFERNAL, 10));
        AffinityManager.registerAffinities(new ItemStack(Items.POTION), new SourceList().add(Source.SEA, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.SPIDER_EYE), new SourceList().add(Source.MOON, 5).add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.EXPERIENCE_BOTTLE), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 5).add(Source.SKY, 5).add(Source.SUN, 5).add(Source.MOON, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.WRITTEN_BOOK), AffinityManager.getAffinities(new ItemStack(Items.WRITABLE_BOOK), server));
        AffinityManager.registerAffinities(new ItemStack(Items.POISONOUS_POTATO), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5).add(Source.VOID, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.FIREWORK_ROCKET), new SourceList().add(Source.EARTH, 5).add(Source.SKY, 5).add(Source.SUN, 15).add(Source.INFERNAL, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.FIREWORK_STAR), AffinityManager.getAffinities(new ItemStack(Items.GUNPOWDER), server));
        AffinityManager.registerAffinities(new ItemStack(Items.ENCHANTED_BOOK), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 5).add(Source.SKY, 5).add(Source.SUN, 5).add(Source.MOON, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.RABBIT), new SourceList().add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.RABBIT_FOOT), new SourceList().add(Source.MOON, 20).add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.RABBIT_HIDE), new SourceList().add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.IRON_HORSE_ARMOR), new SourceList().add(Source.EARTH, 52));
        AffinityManager.registerAffinities(new ItemStack(Items.GOLDEN_HORSE_ARMOR), new SourceList().add(Source.EARTH, 52));
        AffinityManager.registerAffinities(new ItemStack(Items.DIAMOND_HORSE_ARMOR), new SourceList().add(Source.EARTH, 105));
        AffinityManager.registerAffinities(new ItemStack(Items.MUTTON), new SourceList().add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.CHORUS_FRUIT), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5).add(Source.VOID, 10));
        AffinityManager.registerAffinities(new ItemStack(Items.DRAGON_BREATH), new SourceList().add(Source.SKY, 10).add(Source.VOID, 10));
        AffinityManager.registerAffinities(new ItemStack(Items.SPLASH_POTION), AffinityManager.getAffinities(new ItemStack(Items.POTION), server));
        AffinityManager.registerAffinities(new ItemStack(Items.TIPPED_ARROW), AffinityManager.getAffinities(new ItemStack(Items.ARROW), server));
        AffinityManager.registerAffinities(new ItemStack(Items.LINGERING_POTION), AffinityManager.getAffinities(new ItemStack(Items.POTION), server));
        AffinityManager.registerAffinities(new ItemStack(Items.ELYTRA), new SourceList().add(Source.SKY, 20).add(Source.VOID, 10));
        AffinityManager.registerAffinities(new ItemStack(Items.TOTEM_OF_UNDYING), new SourceList().add(Source.HALLOWED, 10));
        AffinityManager.registerAffinities(new ItemStack(Items.SHULKER_SHELL), new SourceList().add(Source.BLOOD, 5).add(Source.VOID, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.TRIDENT), new SourceList().add(Source.EARTH, 15).add(Source.SEA, 15));
        AffinityManager.registerAffinities(new ItemStack(Items.PHANTOM_MEMBRANE), new SourceList().add(Source.SKY, 10).add(Source.BLOOD, 5));
        AffinityManager.registerAffinities(new ItemStack(Items.NAUTILUS_SHELL), new SourceList().add(Source.SEA, 10).add(Source.BLOOD, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.HEART_OF_THE_SEA), new SourceList().add(Source.SEA, 20));
        AffinityManager.registerAffinities(new ItemStack(Items.GLOBE_BANNER_PATTERN), AffinityManager.getAffinities(new ItemStack(Items.PAPER), server));
        AffinityManager.registerAffinities(new ItemStack(Items.SWEET_BERRIES), new SourceList().add(Source.EARTH, 2).add(Source.SUN, 2));
        AffinityManager.registerAffinities(new ItemStack(Items.HONEY_BOTTLE), AffinityManager.getAffinities(new ItemStack(Items.GLASS_BOTTLE), server).add(Source.SEA, 5).add(Source.BLOOD, 1));
        AffinityManager.registerAffinities(new ItemStack(Items.HONEYCOMB), new SourceList().add(Source.SEA, 5).add(Source.BLOOD, 2));
        
        // Append affinities to Vanilla blocks
        AffinityManager.appendAffinities(new ItemStack(Blocks.FURNACE), new SourceList().add(Source.INFERNAL, 5), server);
        AffinityManager.appendAffinities(new ItemStack(Blocks.SEA_LANTERN), new SourceList().add(Source.SUN, 15), server);
        AffinityManager.appendAffinities(new ItemStack(Blocks.CONDUIT), new SourceList().add(Source.SUN, 15), server);
        AffinityManager.appendAffinities(new ItemStack(Blocks.LANTERN), new SourceList().add(Source.SUN, 15), server);
        
        // Register Primal Magic tag affinities
        AffinityManager.registerItemTagAffinities(new ResourceLocation(PrimalMagic.MODID, "colored_shulker_boxes"), AffinityManager.getAffinities(new ItemStack(Items.SHULKER_BOX), server));
        AffinityManager.registerItemTagAffinities(new ResourceLocation(PrimalMagic.MODID, "concrete"), AffinityManager.getAffinities(new ItemStack(Items.WHITE_CONCRETE_POWDER), server).add(Source.SEA, 1));
        AffinityManager.registerItemTagAffinities(new ResourceLocation(PrimalMagic.MODID, "dead_coral_blocks"), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 10));
        AffinityManager.registerItemTagAffinities(new ResourceLocation(PrimalMagic.MODID, "dead_corals"), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 5));
        AffinityManager.registerItemTagAffinities(new ResourceLocation(PrimalMagic.MODID, "moonwood_logs"), new SourceList().add(Source.EARTH, 10).add(Source.MOON, 20));
        AffinityManager.registerItemTagAffinities(new ResourceLocation(PrimalMagic.MODID, "sunwood_logs"), new SourceList().add(Source.EARTH, 10).add(Source.SUN, 20));
        
        // Register Primal Magic block affinities
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.MARBLE_RAW.get()), new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED.get()), new SourceList().add(Source.EARTH, 6).add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.MARBLE_SMOKED.get()), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 5));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.MOONWOOD_LEAVES.get()), new SourceList().add(Source.EARTH, 5).add(Source.SKY, 5).add(Source.MOON, 10));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.MOONWOOD_SAPLING.get()), new SourceList().add(Source.EARTH, 10).add(Source.MOON, 20));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.MOONWOOD_PLANKS.get()), new SourceList().add(Source.EARTH, 2).add(Source.MOON, 4));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.MOONWOOD_SLAB.get()), new SourceList().add(Source.EARTH, 1).add(Source.MOON, 2));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.INFUSED_STONE_EARTH.get()), new SourceList().add(Source.EARTH, 6));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.INFUSED_STONE_SEA.get()), new SourceList().add(Source.EARTH, 5).add(Source.SEA, 1));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.INFUSED_STONE_SKY.get()), new SourceList().add(Source.EARTH, 5).add(Source.SKY, 1));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.INFUSED_STONE_SUN.get()), new SourceList().add(Source.EARTH, 5).add(Source.SUN, 1));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.INFUSED_STONE_MOON.get()), new SourceList().add(Source.EARTH, 5).add(Source.MOON, 1));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.ARCANE_WORKBENCH.get()), AffinityManager.getAffinities(new ItemStack(Blocks.CRAFTING_TABLE), server));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.ANCIENT_FONT_EARTH.get()), new SourceList().add(Source.EARTH, 20));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.ANCIENT_FONT_SEA.get()), new SourceList().add(Source.SEA, 20));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.ANCIENT_FONT_SKY.get()), new SourceList().add(Source.SKY, 20));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.ANCIENT_FONT_SUN.get()), new SourceList().add(Source.SUN, 20));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.ANCIENT_FONT_MOON.get()), new SourceList().add(Source.MOON, 20));
        AffinityManager.registerAffinities(new ItemStack(BlocksPM.CALCINATOR.get()), AffinityManager.getAffinities(new ItemStack(Blocks.FURNACE), server));
        
        // Register Primal Magic item affinities
        AffinityManager.registerAffinities(new ItemStack(ItemsPM.GRIMOIRE.get()), AffinityManager.getAffinities(new ItemStack(Items.ENCHANTED_BOOK), server));
        AffinityManager.registerAffinities(new ItemStack(ItemsPM.MUNDANE_WAND.get()), new SourceList());  // Don't want mundane wands to have any affinities
        AffinityManager.registerAffinities(new ItemStack(ItemsPM.BLOODY_FLESH.get()), new SourceList().add(Source.BLOOD, 20));
        AffinityManager.registerAffinities(new ItemStack(ItemsPM.HALLOWED_ORB.get()), new SourceList().add(Source.HALLOWED, 20));

        // Register Primal Magic essence affinities
        for (Item tagItem : ItemTagsPM.ESSENCES.getAllElements()) {
            if (tagItem instanceof EssenceItem) {
                EssenceItem item = (EssenceItem)tagItem;
                AffinityManager.registerAffinities(new ItemStack(item), new SourceList().add(item.getSource(), item.getEssenceType().getAffinity()));
            }
        }
        
        // Append affinities to Primal Magic blocks
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_SLAB.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_STAIRS.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_WALL.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_BRICKS.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_BRICK_SLAB.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_BRICK_STAIRS.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_BRICK_WALL.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_PILLAR.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_CHISELED.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.MARBLE_ENCHANTED_RUNED.get()), new SourceList().add(Source.SEA, 1).add(Source.SKY, 1).add(Source.SUN, 1).add(Source.MOON, 1), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.SUNWOOD_LEAVES.get()), new SourceList().add(Source.SUN, 10), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.SUNWOOD_SAPLING.get()), new SourceList().add(Source.SUN, 20), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.SUNWOOD_PLANKS.get()), new SourceList().add(Source.SUN, 4), server);
        AffinityManager.appendAffinities(new ItemStack(BlocksPM.SUNWOOD_SLAB.get()), new SourceList().add(Source.SUN, 2), server);
    }
    
    protected static void initPotionBonusAffinities() {
        // Register bonus affinities to be applied to potions based on the type of potion
        AffinityManager.registerPotionBonusAffinity(Potions.NIGHT_VISION, new SourceList().add(Source.SUN, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_NIGHT_VISION, new SourceList().add(Source.SUN, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.INVISIBILITY, new SourceList().add(Source.MOON, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_INVISIBILITY, new SourceList().add(Source.MOON, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.LEAPING, new SourceList().add(Source.SKY, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_LEAPING, new SourceList().add(Source.SKY, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_LEAPING, new SourceList().add(Source.SKY, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.FIRE_RESISTANCE, new SourceList().add(Source.INFERNAL, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_FIRE_RESISTANCE, new SourceList().add(Source.INFERNAL, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.SWIFTNESS, new SourceList().add(Source.SKY, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_SWIFTNESS, new SourceList().add(Source.SKY, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_SWIFTNESS, new SourceList().add(Source.SKY, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.SLOWNESS, new SourceList().add(Source.SKY, 1).add(Source.VOID, 1));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_SLOWNESS, new SourceList().add(Source.SKY, 2).add(Source.VOID, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_SLOWNESS, new SourceList().add(Source.SKY, 2).add(Source.VOID, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.TURTLE_MASTER, new SourceList().add(Source.SEA, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_TURTLE_MASTER, new SourceList().add(Source.SEA, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_TURTLE_MASTER, new SourceList().add(Source.SEA, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.WATER_BREATHING, new SourceList().add(Source.SEA, 1).add(Source.SKY, 1));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_WATER_BREATHING, new SourceList().add(Source.SEA, 2).add(Source.SKY, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.HEALING, new SourceList().add(Source.SUN, 1).add(Source.BLOOD, 1));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_HEALING, new SourceList().add(Source.SUN, 2).add(Source.BLOOD, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.HARMING, new SourceList().add(Source.BLOOD, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_HARMING, new SourceList().add(Source.BLOOD, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.POISON, new SourceList().add(Source.EARTH, 1).add(Source.BLOOD, 1));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_POISON, new SourceList().add(Source.EARTH, 2).add(Source.BLOOD, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_POISON, new SourceList().add(Source.EARTH, 2).add(Source.BLOOD, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.REGENERATION, new SourceList().add(Source.SUN, 1).add(Source.BLOOD, 1));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_REGENERATION, new SourceList().add(Source.SUN, 2).add(Source.BLOOD, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_REGENERATION, new SourceList().add(Source.SUN, 2).add(Source.BLOOD, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.STRENGTH, new SourceList().add(Source.EARTH, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_STRENGTH, new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.STRONG_STRENGTH, new SourceList().add(Source.EARTH, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.WEAKNESS, new SourceList().add(Source.EARTH, 1).add(Source.VOID, 1));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_WEAKNESS, new SourceList().add(Source.EARTH, 2).add(Source.VOID, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LUCK, new SourceList().add(Source.MOON, 5));
        AffinityManager.registerPotionBonusAffinity(Potions.SLOW_FALLING, new SourceList().add(Source.SKY, 2));
        AffinityManager.registerPotionBonusAffinity(Potions.LONG_SLOW_FALLING, new SourceList().add(Source.SKY, 5));
    }
    
    protected static void initEnchantmentBonusAffinities() {
        // Register bonus affinities to be applied to books and enchanted items based on enchantment type.  The amount
        // of the bonus is based on the enchantment level.
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.PROTECTION, Source.EARTH);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.FIRE_PROTECTION, Source.INFERNAL);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.FEATHER_FALLING, Source.SKY);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.BLAST_PROTECTION, Source.EARTH);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.PROJECTILE_PROTECTION, Source.SKY);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.RESPIRATION, Source.SEA);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.AQUA_AFFINITY, Source.SEA);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.THORNS, Source.BLOOD);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.DEPTH_STRIDER, Source.SEA);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.FROST_WALKER, Source.SEA);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.BINDING_CURSE, Source.VOID);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.SHARPNESS, Source.BLOOD);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.SMITE, Source.HALLOWED);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.BANE_OF_ARTHROPODS, Source.BLOOD);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.KNOCKBACK, Source.EARTH);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.FIRE_ASPECT, Source.INFERNAL);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.LOOTING, Source.MOON);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.SWEEPING, Source.SKY);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.EFFICIENCY, Source.EARTH);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.SILK_TOUCH, Source.MOON);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.UNBREAKING, Source.EARTH);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.FORTUNE, Source.MOON);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.POWER, Source.BLOOD);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.PUNCH, Source.BLOOD);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.FLAME, Source.INFERNAL);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.INFINITY, Source.VOID);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.LUCK_OF_THE_SEA, Source.MOON);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.LURE, Source.SEA);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.LOYALTY, Source.SKY);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.IMPALING, Source.BLOOD);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.RIPTIDE, Source.SEA);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.CHANNELING, Source.SKY);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.MULTISHOT, Source.SKY);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.QUICK_CHARGE, Source.SKY);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.PIERCING, Source.BLOOD);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.MENDING, Source.SUN);
        AffinityManager.registerEnchantmentBonusAffinity(Enchantments.VANISHING_CURSE, Source.VOID);
    }
}
