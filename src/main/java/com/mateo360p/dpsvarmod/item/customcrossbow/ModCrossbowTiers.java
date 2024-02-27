package com.mateo360p.dpsvarmod.item.customcrossbow;

import com.mateo360p.dpsvarmod.config.Config;
import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModCrossbowTiers implements CrossbowTier {
    DIAMOND(824, Config.DIAMOND_CROSSBOW_DAMAGE_BONUS, 2, 2, 3, () -> {return Ingredient.of(Items.DIAMOND); }),
    NETHERITE(1530, Config.NETHERITE_CROSSBOW_DAMAGE_BONUS, 3,3,4,    () -> { return Ingredient.of(Items.NETHERITE_INGOT); }),
    DENDERITE(2456, Config.DENDERITE_CROSSBOW_DAMAGE_BONUS, 5,5,6,  () -> { return Ingredient.of(ModItems.DENDERITE_INGOT.get()); });

    private final int uses;
    private Supplier<Double> damageBonus;
    private final float speedBonus;
    private final int chargeTime;
    private final int enchantmentValue;
    private final Supplier<Ingredient> ingredient;


    private ModCrossbowTiers(int durability, Supplier damageBonus, float speedBonus, int chargeTime, int enchantmentValue, Supplier<Ingredient> ingredient) {
        this.uses = durability;
        this.damageBonus = damageBonus;
        this.speedBonus = speedBonus;
        this.chargeTime = chargeTime;
        this.enchantmentValue = enchantmentValue;
        this.ingredient = ingredient;
    }

    public int getUses() {
        return this.uses;
    }

    public float getAttackDamageBonus() {
        return ((Double)this.damageBonus.get()).floatValue();
    }

    public float getSpeedBonus() {
        return this.speedBonus;
    }
    public int getChargeTime() {
        return this.chargeTime;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }
    public Ingredient getIngredient() {
        return this.ingredient.get();
    }

}
