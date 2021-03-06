package com.verdantartifice.primalmagic.common.containers;

import com.verdantartifice.primalmagic.PrimalMagic;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Deferred registry for mod containers.
 * 
 * @author Daedalus4096
 */
public class ContainersPM {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, PrimalMagic.MODID);
    
    public static void init() {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    
    public static final RegistryObject<ContainerType<GrimoireContainer>> GRIMOIRE = CONTAINERS.register("grimoire", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new GrimoireContainer(windowId);
    }));
    public static final RegistryObject<ContainerType<ArcaneWorkbenchContainer>> ARCANE_WORKBENCH = CONTAINERS.register("arcane_workbench", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new ArcaneWorkbenchContainer(windowId, inv);
    }));
    public static final RegistryObject<ContainerType<WandAssemblyTableContainer>> WAND_ASSEMBLY_TABLE = CONTAINERS.register("wand_assembly_table", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new WandAssemblyTableContainer(windowId, inv);
    }));
    public static final RegistryObject<ContainerType<AnalysisTableContainer>> ANALYSIS_TABLE = CONTAINERS.register("analysis_table", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new AnalysisTableContainer(windowId, inv);
    }));
    public static final RegistryObject<ContainerType<CalcinatorContainer>> CALCINATOR = CONTAINERS.register("calcinator", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new CalcinatorContainer(windowId, inv);
    }));
    public static final RegistryObject<ContainerType<WandInscriptionTableContainer>> WAND_INSCRIPTION_TABLE = CONTAINERS.register("wand_inscription_table", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new WandInscriptionTableContainer(windowId, inv);
    }));
    public static final RegistryObject<ContainerType<SpellcraftingAltarContainer>> SPELLCRAFTING_ALTAR = CONTAINERS.register("spellcrafting_altar", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new SpellcraftingAltarContainer(windowId, inv);
    }));
    public static final RegistryObject<ContainerType<WandChargerContainer>> WAND_CHARGER = CONTAINERS.register("wand_charger", () -> IForgeContainerType.create((windowId, inv, data) -> {
        return new WandChargerContainer(windowId, inv);
    }));
}
