package com.mateo360p.dpsvarmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.ConfigValue<Double> DIAMOND_BOW_DAMAGE_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_BOW_DAMAGE_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Double> DENDERITE_BOW_DAMAGE_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Double> DIAMOND_CROSSBOW_DAMAGE_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Double> NETHERITE_CROSSBOW_DAMAGE_BONUS;
    public static final ForgeConfigSpec.ConfigValue<Double> DENDERITE_CROSSBOW_DAMAGE_BONUS;
    public static final ForgeConfigSpec SPEC;

    public Config() {
    }

    static {
        DIAMOND_BOW_DAMAGE_BONUS = BUILDER.defineInRange("Diamond Bow damage bonus", 2.5, 0.0, 3.4028234663852886E38);
        NETHERITE_BOW_DAMAGE_BONUS = BUILDER.defineInRange("Netherite Bow damage bonus", 5.0, 0.0, 3.4028234663852886E38);
        DENDERITE_BOW_DAMAGE_BONUS = BUILDER.defineInRange("Denderite Bow damage bonus", 8.0, 0.0, 3.4028234663852886E38);
        DIAMOND_CROSSBOW_DAMAGE_BONUS = BUILDER.defineInRange("Diamond Crossbow damage bonus", 1.75, 0.0, 3.4028234663852886E38);
        NETHERITE_CROSSBOW_DAMAGE_BONUS = BUILDER.defineInRange("Netherite Crossbow damage bonus", 2.5, 0.0, 3.4028234663852886E38);
        DENDERITE_CROSSBOW_DAMAGE_BONUS = BUILDER.defineInRange("Denderite Crossbow damage bonus", 4, 0.0, 3.4028234663852886E38);
        SPEC = BUILDER.build();
    }
}