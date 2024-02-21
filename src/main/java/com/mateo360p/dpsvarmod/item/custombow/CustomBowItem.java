package com.mateo360p.dpsvarmod.item.custombow;

import java.util.List;

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

    public AbstractArrow customArrow(AbstractArrow arrow) {
        arrow.setBaseDamage(arrow.getBaseDamage() + (double)this.tier.getAttackDamageBonus());
        return arrow;
    }

    public int m_6473_() {
        return this.tier.getEnchantmentValue();
    }

    public void m_7373_(ItemStack p_41421_, Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.literal("+" + Float.toString(this.tier.getAttackDamageBonus()) + " ").append(Component.translatable("item.dpsvarmod.damage_tooltip")).withStyle(ChatFormatting.DARK_GREEN));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }
}
