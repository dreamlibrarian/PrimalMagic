package com.verdantartifice.primalmagic.client.renderers.tile;

import com.mojang.blaze3d.platform.GlStateManager;
import com.verdantartifice.primalmagic.common.tiles.mana.WandChargerTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class WandChargerTER extends TileEntityRenderer<WandChargerTileEntity> {
    @Override
    public void render(WandChargerTileEntity tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        super.render(tileEntityIn, x, y, z, partialTicks, destroyStage);
        ItemStack wandStack = tileEntityIn.getSyncedStackInSlot(1).copy();
        if (!wandStack.isEmpty()) {
            wandStack.setCount(1);
            int rot = (int)(this.getWorld().getWorldInfo().getGameTime() % 360);
            GlStateManager.pushMatrix();
            GlStateManager.translated(x + 0.5D, y + 0.5D, z + 0.5D);
            GlStateManager.rotated(rot, 0.0D, 1.0D, 0.0D);
            GlStateManager.scaled(0.5D, 0.5D, 0.5D);
            Minecraft.getInstance().getItemRenderer().renderItem(wandStack, ItemCameraTransforms.TransformType.GUI);
            GlStateManager.popMatrix();
        }
    }
}