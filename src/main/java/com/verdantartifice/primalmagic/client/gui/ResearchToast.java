package com.verdantartifice.primalmagic.client.gui;

import java.awt.Color;

import com.mojang.blaze3d.platform.GlStateManager;
import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.common.research.ResearchEntry;

import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ResearchToast implements IToast {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(PrimalMagic.MODID, "textures/gui/hud.png");
    
    protected ResearchEntry entry;
    
    public ResearchToast(ResearchEntry entry) {
        this.entry = entry;
    }
    
    @Override
    public Visibility draw(ToastGui toastGui, long delta) {
        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        toastGui.blit(0, 0, 0, 224, 160, 32);
        
        ITextComponent titleText = new TranslationTextComponent("primalmagic.toast.title");
        toastGui.getMinecraft().fontRenderer.drawString(titleText.getFormattedText(), 6, 7, 0x551A8B);
        
        ITextComponent descText = new TranslationTextComponent(this.entry.getNameTranslationKey());
        String descStr = descText.getFormattedText();
        float width = toastGui.getMinecraft().fontRenderer.getStringWidth(descStr);
        if (width > 148.0F) {
            float scale = (148.0F / width);
            GlStateManager.pushMatrix();
            GlStateManager.translatef(6.0F, 18.0F, 0.0F);
            GlStateManager.scalef(scale, scale, scale);
            toastGui.getMinecraft().fontRenderer.drawString(descStr, 0, 0, Color.BLACK.getRGB());
            GlStateManager.popMatrix();
        } else {
            toastGui.getMinecraft().fontRenderer.drawString(descStr, 6, 18, Color.BLACK.getRGB());
        }
        
        return (delta >= 5000L) ? Visibility.HIDE : Visibility.SHOW;
    }

}