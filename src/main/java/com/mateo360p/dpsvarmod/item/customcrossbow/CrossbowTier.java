package com.mateo360p.dpsvarmod.item.customcrossbow;

import net.minecraft.world.item.crafting.Ingredient;

public interface CrossbowTier {
    int getUses();
    float getAttackDamageBonus();
    float getSpeedBonus();
    int getChargeTime();
    int getEnchantmentValue();
    Ingredient getIngredient();
}
