package com.verdantartifice.primalmagic.common.items.wands;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Nonnull;

import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.common.crafting.IWandTransform;
import com.verdantartifice.primalmagic.common.crafting.WandTransforms;
import com.verdantartifice.primalmagic.common.sources.Source;
import com.verdantartifice.primalmagic.common.sources.SourceList;
import com.verdantartifice.primalmagic.common.spells.SpellManager;
import com.verdantartifice.primalmagic.common.spells.SpellPackage;
import com.verdantartifice.primalmagic.common.stats.StatsManager;
import com.verdantartifice.primalmagic.common.wands.IInteractWithWand;
import com.verdantartifice.primalmagic.common.wands.IWand;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Base item definition for a wand.  Wands store mana for use in crafting and, optionally, casting spells.
 * They are replenished by drawing from mana fonts or being charged in a wand charger.  The wand's mana is
 * stored internally as centimana (hundredths of mana points), though most mana manipulation methods deal
 * in "real" mana, not centimana.
 * 
 * @author Daedalus4096
 */
public abstract class AbstractWandItem extends Item implements IWand {
    protected static final DecimalFormat MANA_FORMATTER = new DecimalFormat("#######.##");
    
    public AbstractWandItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getMana(ItemStack stack, Source source) {
        if (this.getMaxMana(stack) == -1) {
            // If the given wand stack has infinite mana, return that
            return -1;
        } else {
            // Otherwise get the current centimana for that source from the stack's NBT tag
            int retVal = 0;
            if (stack != null && source != null && stack.hasTag() && stack.getTag().contains(source.getTag())) {
                retVal = stack.getTag().getInt(source.getTag());
            }
            return retVal;
        }
    }
    
    protected ITextComponent getManaText(ItemStack stack, Source source) {
        int mana = this.getMana(stack, source);
        if (mana == -1) {
            // If the given wand stack has infinte mana, show the infinity symbol
            return new StringTextComponent(Character.toString('\u221E'));
        } else {
            // Otherwise show the current real mana for that source from the stack's NBT tag
            return new StringTextComponent(MANA_FORMATTER.format(mana / 100.0D));
        }
    }

    @Override
    public SourceList getAllMana(ItemStack stack) {
        SourceList retVal = new SourceList();
        boolean isInfinite = this.getMaxMana(stack) == -1;
        for (Source source : Source.SORTED_SOURCES) {
            if (isInfinite) {
                // If the stack has infinite mana, set that into the returned source list (not merge; it would keep the default zero)
                retVal.set(source, -1);
            } else if (stack.hasTag() && stack.getTag().contains(source.getTag())) {
                // Otherwise, merge the current centimana into the returned source list
                retVal.merge(source, stack.getTag().getInt(source.getTag()));
            } else {
                retVal.merge(source, 0);
            }
        }
        return retVal;
    }

    protected ITextComponent getMaxManaText(ItemStack stack) {
        int mana = this.getMaxMana(stack);
        if (mana == -1) {
            // If the given wand stack has infinte mana, show the infinity symbol
            return new StringTextComponent(Character.toString('\u221E'));
        } else {
            // Otherwise show the max centimana for that source from the stack's NBT tag
            return new StringTextComponent(MANA_FORMATTER.format(mana / 100.0D));
        }
    }
    
    protected void setMana(@Nonnull ItemStack stack, @Nonnull Source source, int amount) {
        // Save the given amount of centimana for the given source into the stack's NBT tag
        stack.setTagInfo(source.getTag(), IntNBT.valueOf(amount));
    }

    @Override
    public int addRealMana(ItemStack stack, Source source, int amount) {
        // If the parameters are invalid or the given wand stack has infinite mana, do nothing
        if (stack == null || source == null || this.getMaxMana(stack) == -1) {
            return 0;
        }
        
        // Otherwise, increment and set the new real mana total for the source into the wand's data, returning
        // any leftover real mana that wouldn't fit
        int toStore = this.getMana(stack, source) + (amount * 100);
        int leftover = Math.max(toStore - this.getMaxMana(stack), 0);
        this.setMana(stack, source, Math.min(toStore, this.getMaxMana(stack)));
        return leftover;
    }

    @Override
    public boolean consumeRealMana(ItemStack stack, PlayerEntity player, Source source, int amount) {
        if (stack == null || player == null || source == null) {
            return false;
        }
        if (this.getMaxMana(stack) == -1) {
            // If the wand stack has infinite mana, return success without consuming anything
            StatsManager.incrementValue(player, source.getManaSpentStat(), amount);
            return true;
        } else if (this.containsRealMana(stack, player, source, amount)) {
            // If the wand stack does not have infinite mana but does have enough, consume that amount of mana and return success
            this.setMana(stack, source, this.getMana(stack, source) - (int)(this.getTotalCostModifier(stack, player, source) * (amount * 100)));
            StatsManager.incrementValue(player, source.getManaSpentStat(), amount);
            return true;
        } else {
            // Otherwise return failure
            return false;
        }
    }
    
    @Override
    public boolean consumeRealMana(ItemStack stack, PlayerEntity player, SourceList sources) {
        if (stack == null || player == null || sources == null) {
            return false;
        }
        if (this.getMaxMana(stack) == -1) {
            // If the wand stack has infinite mana, return success without consuming anything
            for (Source source : sources.getSources()) {
                StatsManager.incrementValue(player, source.getManaSpentStat(), sources.getAmount(source));
            }
            return true;
        } else if (this.containsRealMana(stack, player, sources)) {
            // If the wand stack does not have infinite mana but does have enough, consume that amount of mana and return success
            for (Source source : sources.getSources()) {
                int amount = sources.getAmount(source);
                this.setMana(stack, source, this.getMana(stack, source) - (int)(this.getTotalCostModifier(stack, player, source) * (amount * 100)));
                StatsManager.incrementValue(player, source.getManaSpentStat(), amount);
            }
            return true;
        } else {
            // Otherwise return failure
            return false;
        }
    }
    
    @Override
    public boolean containsRealMana(ItemStack stack, PlayerEntity player, Source source, int amount) {
        // A wand stack with infinite mana always contains the requested amount of mana
        return this.getMaxMana(stack) == -1 || this.getMana(stack, source) >= (this.getTotalCostModifier(stack, player, source) * (amount * 100));
    }
    
    @Override
    public boolean containsRealMana(ItemStack stack, PlayerEntity player, SourceList sources) {
        for (Source source : sources.getSources()) {
            if (!this.containsRealMana(stack, player, source, sources.getAmount(source))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public float getTotalCostModifier(ItemStack stack, PlayerEntity player, Source source) {
        // Start with the base modifier, as determined by wand cap
        float modifier = this.getBaseCostModifier(stack);
        
        // TODO Subtract discounts from equipped player gear
        // TODO Subtract discounts from attuned sources
        // TODO Subtract discounts from wand enchantments
        // TODO Add penalties from temporary conditions
        
        return modifier;
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        PlayerEntity player = Minecraft.getInstance().player;
        
        if (PrimalMagic.proxy.isShiftDown()) {
            // Add detailed mana information
            for (Source source : Source.SORTED_SOURCES) {
                // Only include a mana source in the listing if it's been discovered
                if (source.isDiscovered(player)) {
                    ITextComponent nameComp = new TranslationTextComponent(source.getNameTranslationKey()).applyTextStyle(source.getChatColor());
                    ITextComponent line = new TranslationTextComponent("primalmagic.source.mana_tooltip", nameComp.getFormattedText(), this.getManaText(stack, source), this.getMaxManaText(stack));
                    tooltip.add(line);
                }
            }
            
            // Add inscribed spell listing
            List<SpellPackage> spells = this.getSpells(stack);
            int activeIndex = this.getActiveSpellIndex(stack);
            if (!spells.isEmpty()) {
                tooltip.add(new TranslationTextComponent("primalmagic.spells.wand_header"));
                for (int index = 0; index < spells.size(); index++) {
                    SpellPackage spell = spells.get(index);
                    StringBuilder sb = new StringBuilder("  ");
                    if (index == activeIndex) {
                        // Prefix the active spell name with an asterisk to distinguish it
                        sb.append("*");
                    }
                    sb.append(spell.getName().getFormattedText());
                    tooltip.add(new StringTextComponent(sb.toString()));
                }
            }
        } else {
            // Add mana summary
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (Source source : Source.SORTED_SOURCES) {
                // Only include a mana source in the summary if it's been discovered
                if (source.isDiscovered(player)) {
                    if (!first) {
                        sb.append("/");
                    }
                    ITextComponent manaStr = this.getManaText(stack, source).applyTextStyle(source.getChatColor());
                    sb.append(manaStr.getFormattedText());
                    first = false;
                }
            }
            tooltip.add(new StringTextComponent(sb.toString()));
            
            // Add active spell
            SpellPackage activeSpell = this.getActiveSpell(stack);
            if (activeSpell != null) {
                tooltip.add(new TranslationTextComponent("primalmagic.spells.short_wand_header", activeSpell.getName()));
            }
        }
    }
    
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
    
    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }
    
    @Override
    public IInteractWithWand getTileInUse(ItemStack wandStack, World world) {
        // Look up the world coordinates of the wand-interactable tile entity currently in use from NBT, then query the world for it
        if (wandStack.hasTag() && wandStack.getTag().contains("UsingX") && wandStack.getTag().contains("UsingY") && wandStack.getTag().contains("UsingZ")) {
            BlockPos pos = new BlockPos(wandStack.getTag().getInt("UsingX"), wandStack.getTag().getInt("UsingY"), wandStack.getTag().getInt("UsingZ"));
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof IInteractWithWand) {
                return (IInteractWithWand)tile;
            }
        }
        return null;
    }
    
    @Override
    public <T extends TileEntity & IInteractWithWand> void setTileInUse(ItemStack wandStack, T tile) {
        // Save the position of the wand-interactable tile entity so it can be looked up later
        wandStack.setTagInfo("UsingX", IntNBT.valueOf(tile.getPos().getX()));
        wandStack.setTagInfo("UsingY", IntNBT.valueOf(tile.getPos().getY()));
        wandStack.setTagInfo("UsingZ", IntNBT.valueOf(tile.getPos().getZ()));
    }
    
    @Override
    public void clearTileInUse(ItemStack wandStack) {
        if (wandStack.hasTag()) {
            wandStack.getTag().remove("UsingX");
            wandStack.getTag().remove("UsingY");
            wandStack.getTag().remove("UsingZ");
        }
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        SpellPackage activeSpell = this.getActiveSpell(stack);
        if (activeSpell != null && !SpellManager.isOnCooldown(playerIn)) {
            // If the wand has an active spell and spells are off the player's cooldown, attempt to cast the spell on right-click
            SpellManager.setCooldown(playerIn, activeSpell.getCooldownTicks());
            if (worldIn.isRemote) {
                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            } else if (this.consumeRealMana(stack, playerIn, activeSpell.getManaCost())) {
                // If the wand contains enough mana, consume it and cast the spell
                activeSpell.cast(worldIn, playerIn);
                playerIn.swingArm(handIn);
                return new ActionResult<>(ActionResultType.SUCCESS, stack);
            } else {
                return new ActionResult<>(ActionResultType.FAIL, stack);
            }
        } else {
            return new ActionResult<>(ActionResultType.PASS, stack);
        }
    }
    
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        // Bypass wand functionality if the player is sneaking
        if (context.getPlayer().isShiftKeyDown()) {
            return ActionResultType.PASS;
        }
        
        context.getPlayer().setActiveHand(context.getHand());
        
        // If the mouseover target is a wand-sensitive block, trigger that initial interaction
        BlockState bs = context.getWorld().getBlockState(context.getPos());
        if (bs.getBlock() instanceof IInteractWithWand) {
            return ((IInteractWithWand)bs.getBlock()).onWandRightClick(context.getItem(), context.getWorld(), context.getPlayer(), context.getPos(), context.getFace());
        }
        
        // If the mouseover target is a wand-sensitive tile entity, trigger that initial interaction
        TileEntity tile = context.getWorld().getTileEntity(context.getPos());
        if (tile != null && tile instanceof IInteractWithWand) {
            return ((IInteractWithWand)tile).onWandRightClick(context.getItem(), context.getWorld(), context.getPlayer(), context.getPos(), context.getFace());
        }
        
        // Otherwise, see if the mouseover target is a valid target for wand transformation
        for (IWandTransform transform : WandTransforms.getAll()) {
            if (transform.isValid(context.getWorld(), context.getPlayer(), context.getPos())) {
                if (!context.getPlayer().canPlayerEdit(context.getPos(), context.getFace(), context.getItem())) {
                    return ActionResultType.FAIL;
                } else {
                    context.getPlayer().swingArm(context.getHand());
                    transform.execute(context.getWorld(), context.getPlayer(), context.getPos());
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }
    
    @Override
    public void onUsingTick(ItemStack stack, LivingEntity living, int count) {
        // If the player continues to hold the interact button, continue the interaction with the last wand-sensitive block/tile interacted with
        if (living instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)living;
            IInteractWithWand wandable = this.getTileInUse(stack, player.world);
            if (wandable != null) {
                wandable.onWandUseTick(stack, player, count);
            }
        }
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        // Once interaction ceases, clear the last-interacted coordinates
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
        this.clearTileInUse(stack);
    }
    
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
    
    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
        // Don't break wand interaction just because the stack NBT changes
        return true;
    }
}
