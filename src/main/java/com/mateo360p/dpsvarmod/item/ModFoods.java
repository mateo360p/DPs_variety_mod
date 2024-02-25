package com.mateo360p.dpsvarmod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BOILED_EGG = new FoodProperties.Builder()
            .meat().nutrition(2).saturationMod(0.65f).build();
    public static final FoodProperties FRIED_EGGS = new FoodProperties.Builder()
            .meat().nutrition(4).saturationMod(1.25f).build();
}
