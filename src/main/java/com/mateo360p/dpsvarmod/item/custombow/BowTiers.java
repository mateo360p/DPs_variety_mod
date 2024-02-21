package com.mateo360p.dpsvarmod.item.custombow;

import com.mateo360p.dpsvarmod.config.Config;
import java.util.function.Supplier;

public enum BowTiers implements BowTier {
    DIAMOND(620, Config.DIAMOND_BOW_DAMAGE_BONUS, 2),
    NETHERITE(1256, Config.NETHERITE_BOW_DAMAGE_BONUS, 3),
    DENDERITE(2178, Config.DENDERITE_BOW_DAMAGE_BONUS, 5);
    private final int uses;
    private Supplier<Double> damageBonus;
    private final int enchantmentValue;
    private BowTiers(int durability, Supplier damageBonus, int enchantmentValue) {
        this.uses = durability;
        this.damageBonus = damageBonus;
        this.enchantmentValue = enchantmentValue;
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

}
