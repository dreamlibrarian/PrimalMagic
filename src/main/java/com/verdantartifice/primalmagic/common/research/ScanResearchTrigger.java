package com.verdantartifice.primalmagic.common.research;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.IItemProvider;

/**
 * Definition of a trigger that grants a specified research entry upon scanning a given block/item.
 * 
 * @author Daedalus4096
 */
public class ScanResearchTrigger implements IScanTrigger {
    protected final IItemProvider target;
    protected final SimpleResearchKey toUnlock;
    
    public ScanResearchTrigger(IItemProvider target, SimpleResearchKey toUnlock) {
        this.target = target;
        this.toUnlock = toUnlock;
    }

    @Override
    public boolean matches(ServerPlayerEntity player, IItemProvider itemProvider) {
        return target.asItem().equals(itemProvider.asItem());
    }

    @Override
    public void onMatch(ServerPlayerEntity player, IItemProvider itemProvider) {
        ResearchManager.progressResearch(player, this.toUnlock);
    }
}
