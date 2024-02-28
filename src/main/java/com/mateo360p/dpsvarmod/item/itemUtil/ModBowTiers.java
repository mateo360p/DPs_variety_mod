package com.mateo360p.dpsvarmod.item.itemUtil;

import com.mateo360p.dpsvarmod.config.Config;
import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModBowTiers implements BowTier {
    DIAMOND(620, Config.DIAMOND_BOW_DAMAGE_BONUS, 3, () -> {return Ingredient.of(Items.DIAMOND); }),
    NETHERITE(1256, Config.NETHERITE_BOW_DAMAGE_BONUS, 4,    () -> { return Ingredient.of(Items.NETHERITE_INGOT); }),
    DENDERITE(2178, Config.DENDERITE_BOW_DAMAGE_BONUS, 6,  () -> { return Ingredient.of(ModItems.DENDERITE_INGOT.get()); });

    private final int uses;
    private Supplier<Double> damageBonus;
    private final int enchantmentValue;
    private final Supplier<Ingredient> ingredient;


    private ModBowTiers(int durability, Supplier damageBonus, int enchantmentValue, Supplier<Ingredient> ingredient) {
        this.uses = durability;
        this.damageBonus = damageBonus;
        this.enchantmentValue = enchantmentValue;
        this.ingredient = ingredient;
    }

    public int getUses() {
        return this.uses;
    }

    public float getAttackDamageBonus() {
        return ((Double)this.damageBonus.get()).floatValue();
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getIngredient() {
        return this.ingredient.get();
    }

}
