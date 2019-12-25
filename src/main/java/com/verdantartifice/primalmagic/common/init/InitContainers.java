package com.verdantartifice.primalmagic.common.init;

import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.common.containers.AnalysisTableContainer;
import com.verdantartifice.primalmagic.common.containers.ArcaneWorkbenchContainer;
import com.verdantartifice.primalmagic.common.containers.CalcinatorContainer;
import com.verdantartifice.primalmagic.common.containers.GrimoireContainer;
import com.verdantartifice.primalmagic.common.containers.SpellcraftingAltarContainer;
import com.verdantartifice.primalmagic.common.containers.WandAssemblyTableContainer;
import com.verdantartifice.primalmagic.common.containers.WandChargerContainer;
import com.verdantartifice.primalmagic.common.containers.WandInscriptionTableContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.registries.IForgeRegistry;

public class InitContainers {
    public static void initContainers(IForgeRegistry<ContainerType<?>> registry) {
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new GrimoireContainer(windowId);
        }).setRegistryName(PrimalMagic.MODID, "grimoire"));
        
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new ArcaneWorkbenchContainer(windowId, inv);
        }).setRegistryName(PrimalMagic.MODID, "arcane_workbench"));
        
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new WandAssemblyTableContainer(windowId, inv);
        }).setRegistryName(PrimalMagic.MODID, "wand_assembly_table"));
        
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new AnalysisTableContainer(windowId, inv);
        }).setRegistryName(PrimalMagic.MODID, "analysis_table"));
        
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new CalcinatorContainer(windowId, inv);
        }).setRegistryName(PrimalMagic.MODID, "calcinator"));
        
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new WandInscriptionTableContainer(windowId, inv);
        }).setRegistryName(PrimalMagic.MODID, "wand_inscription_table"));
        
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new SpellcraftingAltarContainer(windowId, inv);
        }).setRegistryName(PrimalMagic.MODID, "spellcrafting_altar"));
        
        registry.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new WandChargerContainer(windowId, inv);
        }).setRegistryName(PrimalMagic.MODID, "wand_charger"));
    }
}
