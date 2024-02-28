package com.mateo360p.dpsvarmod.item.itemUtil;

import net.minecraft.world.item.crafting.Ingredient;

public interface BowTier {
    int getUses();
    float getAttackDamageBonus();
    int getEnchantmentValue();
    Ingredient getIngredient();
}
