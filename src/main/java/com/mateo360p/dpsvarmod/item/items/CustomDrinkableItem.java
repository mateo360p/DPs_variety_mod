package com.mateo360p.dpsvarmod.item.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class CustomDrinkableItem extends Item {
    private final Item remainItem;
    private final int duration;
    private final boolean craftRemainable;

    public CustomDrinkableItem(Item remainItem, int duration, boolean craftRemainable, Item.Properties properties) {
        super(properties);
        this.remainItem = remainItem;
        this.duration = duration;
        this.craftRemainable = craftRemainable;
    }
    public ItemStack finishUsingItem(ItemStack mStack, Level mLevel, LivingEntity mEntity) {
        super.finishUsingItem(mStack, mLevel, mEntity);
        if (mEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, mStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }
        if (mStack.isEmpty()) {
            return new ItemStack(remainItem);
        } else {
            if (mEntity instanceof Player && !((Player)mEntity).getAbilities().instabuild) {
                ItemStack itemstack = new ItemStack(remainItem);
                Player player = (Player)mEntity;
                if (!player.getInventory().add(itemstack)) {
                    player.drop(itemstack, false);
                }
            }
            return mStack;
        }
    }
    @Override
    public boolean hasCraftingRemainingItem() {
        return craftRemainable;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
        return new ItemStack(remainItem);
    }
    @Override
    public int getUseDuration(ItemStack mStack) {
        return duration;
    }

    public UseAnim getUseAnimation(ItemStack mStack) {
        return UseAnim.DRINK;
    }
    public SoundEvent getEatingSound() {
        return this.getDrinkingSound();
    }

    public InteractionResultHolder<ItemStack> use(Level mLevel, Player mPlayer, InteractionHand mHand) {
        return ItemUtils.startUsingInstantly(mLevel, mPlayer, mHand);
    }
}
