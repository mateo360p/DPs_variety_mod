package com.mateo360p.dpsvarmod.item.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CustomContainerItem extends Item {
    private final Item remainItem;

    public CustomContainerItem(Item remainItem, Properties properties) {
        super(properties);
        this.remainItem = remainItem;
    }
    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        return new ItemStack(remainItem);
    }
}
