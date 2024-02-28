package com.mateo360p.dpsvarmod.item.itemUtil;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BOILED_EGG = new FoodProperties.Builder()
            .meat().nutrition(2).saturationMod(0.65f).build();
    public static final FoodProperties FRIED_EGGS = new FoodProperties.Builder()
            .meat().nutrition(3).saturationMod(1.25f).build();
    public static final FoodProperties CHEESE = new FoodProperties.Builder()
            .meat().nutrition(4).saturationMod(1.3f).build();
    public static final FoodProperties LEMON_BOTTLE = new FoodProperties.Builder()
            .nutrition(1).saturationMod(0.15f).build();
}
