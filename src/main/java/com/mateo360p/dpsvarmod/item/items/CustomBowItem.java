package com.mateo360p.dpsvarmod.item.items;

import java.util.List;

import com.mateo360p.dpsvarmod.item.itemUtil.BowTier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class CustomBowItem extends BowItem {
    private final BowTier tier;

    public CustomBowItem(BowTier tier, Item.Properties properties) {
        super(properties.durability(tier.getUses()));
        this.tier = tier;
    }

    public BowTier getTier() {
        return this.tier;
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow) {
        arrow.setBaseDamage(arrow.getBaseDamage() + (double)this.tier.getAttackDamageBonus());
        return arrow;
    }

    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }

    public boolean isValidRepairItem(ItemStack p_43311_, ItemStack p_43312_) {
        return this.tier.getIngredient().test(p_43312_) || super.isValidRepairItem(p_43311_, p_43312_);
    }
    public void appendHoverText(ItemStack pStack, Level plevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("archery.dpsvarmod.customacheryitem").withStyle(ChatFormatting.DARK_GREEN).append(Component.literal("+" + Float.toString(this.tier.getAttackDamageBonus())).withStyle(ChatFormatting.DARK_GREEN)));
        super.appendHoverText(pStack, plevel, pTooltipComponents, pIsAdvanced);
    }
}
