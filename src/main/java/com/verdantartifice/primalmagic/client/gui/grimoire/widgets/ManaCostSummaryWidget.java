package com.verdantartifice.primalmagic.client.gui.grimoire.widgets;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.client.util.GuiUtils;
import com.verdantartifice.primalmagic.common.sources.Source;
import com.verdantartifice.primalmagic.common.sources.SourceList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Display widget for summarizing recipe mana costs.  Used on arcane recipe pages.
 * 
 * @author Daedalus4096
 */
@OnlyIn(Dist.CLIENT)
public class ManaCostSummaryWidget extends Widget {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(PrimalMagic.MODID, "textures/gui/mana_cost_summary_widget.png");

    protected SourceList manaCosts;
    
    public ManaCostSummaryWidget(SourceList manaCosts, int x, int y) {
        super(x, y, 16, 16, "");
        this.manaCosts = manaCosts;
    }
    
    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
        // Render the base widget
        Minecraft mc = Minecraft.getInstance();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.pushMatrix();
        mc.getTextureManager().bindTexture(TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.translatef(this.x, this.y, 0.0F);
        RenderSystem.scaled(0.0625D, 0.0625D, 0.0625D);
        this.blit(0, 0, 0, 0, 255, 255);
        RenderSystem.popMatrix();

        // Render tooltip if hovered over
        if (this.isHovered()) {
            List<ITextComponent> tooltip = new ArrayList<>();
            if (this.manaCosts.isEmpty()) {
                tooltip.add(new TranslationTextComponent("primalmagic.crafting.no_mana"));
            } else {
                for (Source source : this.manaCosts.getSourcesSorted()) {
                    boolean discovered = source.isDiscovered(mc.player);
                    ITextComponent sourceText = discovered ? 
                            new TranslationTextComponent(source.getNameTranslationKey()).applyTextStyle(source.getChatColor()) :
                            new TranslationTextComponent(Source.getUnknownTranslationKey());
                    tooltip.add(new TranslationTextComponent("primalmagic.crafting.mana_tooltip", this.manaCosts.getAmount(source), sourceText));
                }
            }
            GuiUtils.renderCustomTooltip(tooltip, this.x, this.y);
        }
    }
    
    @Override
    public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
        // Disable click behavior
        return false;
    }
}
