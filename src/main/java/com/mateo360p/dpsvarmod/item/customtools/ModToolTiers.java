package com.mateo360p.dpsvarmod.item.customtools;

import com.mateo360p.dpsvarmod.dpsvarmod;
import com.mateo360p.dpsvarmod.item.ModItems;
import com.mateo360p.dpsvarmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier DENDERITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 3190, 10.0F, 5.0F, 20, ModTags.Blocks.NEEDS_DENDERITE_TOOL,
                () -> Ingredient.of(ModItems.DENDERITE_INGOT.get())),
            new ResourceLocation(dpsvarmod.MODID, "denderite"), List.of(Tiers.NETHERITE), List.of());
}
