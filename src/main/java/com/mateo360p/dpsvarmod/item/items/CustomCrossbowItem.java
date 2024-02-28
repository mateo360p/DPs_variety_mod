package com.mateo360p.dpsvarmod.item.items;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;

import com.mateo360p.dpsvarmod.item.itemUtil.CrossbowTier;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class CustomCrossbowItem extends CrossbowItem {
    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;
    private final CrossbowTier tier;
    public CustomCrossbowItem(CrossbowTier tier, Item.Properties properties) {
        super(properties.durability(tier.getUses()));
        this.tier = tier;
    }

    public CrossbowTier getTier() {
        return this.tier;
    }
    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }
    public boolean isValidRepairItem(ItemStack p_43311_, ItemStack p_43312_) {
        return this.tier.getIngredient().test(p_43312_) || super.isValidRepairItem(p_43311_, p_43312_);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (isCharged(itemstack)) {
            this.CustomPerformShooting(level, player, hand, itemstack, this.getShootingPower(itemstack), 1.0F);
            setCharged(itemstack, false);
            return InteractionResultHolder.consume(itemstack);
        } else if (!player.getProjectile(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
                player.startUsingItem(hand);
            }
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }


    private float getShootingPower(ItemStack stack) {
        return containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.6F + this.tier.getSpeedBonus() : 3.15F + this.tier.getSpeedBonus();
    }
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int int1) {
        int i = this.getUseDuration(stack) - int1;
        float f = this.getPowerForTime(i, stack);
        if (f >= 1.0F && !isCharged(stack) && tryLoadProjectiles(entity, stack)) {
            setCharged(stack, true);
            SoundSource soundsource = entity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundsource, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }
    }


    private static boolean tryLoadProjectiles(LivingEntity entity, ItemStack stack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MULTISHOT, stack);
        int j = i == 0 ? 1 : 3;
        boolean flag = entity instanceof Player && ((Player)entity).getAbilities().instabuild;
        ItemStack itemstack = entity.getProjectile(stack);
        ItemStack itemstack1 = itemstack.copy();

        for(int k = 0; k < j; ++k) {
            if (k > 0) {
                itemstack = itemstack1.copy();
            }

            if (itemstack.isEmpty() && flag) {
                itemstack = new ItemStack(Items.ARROW);
                itemstack1 = itemstack.copy();
            }

            if (!loadProjectile(entity, stack, itemstack, k > 0, flag)) {
                return false;
            }
        }
        return true;
    }


    private static boolean loadProjectile(LivingEntity entity, ItemStack itemstack1, ItemStack stack, boolean b1, boolean b2) {
        if (stack.isEmpty()) {
            return false;
        } else {
            boolean flag = b2 && stack.getItem() instanceof ArrowItem;
            ItemStack itemstack2;
            if (!flag && !b2 && !b1) {
                itemstack2 = stack.split(1);
                if (stack.isEmpty() && entity instanceof Player) {
                    ((Player)entity).getInventory().removeItem(stack);
                }
            } else {
                itemstack2 = stack.copy();
            }

            addChargedProjectile(itemstack1, itemstack2);
            return true;
        }
    }


    public static boolean isCharged(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null && compoundtag.getBoolean("Charged");
    }

    public static void setCharged(ItemStack stack, boolean b1) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putBoolean("Charged", b1);
    }

    private static void addChargedProjectile(ItemStack stack, ItemStack itemstack) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        ListTag listtag;
        if (compoundtag.contains("ChargedProjectiles", 9)) {
            listtag = compoundtag.getList("ChargedProjectiles", 10);
        } else {
            listtag = new ListTag();
        }

        CompoundTag compoundtag1 = new CompoundTag();
        itemstack.save(compoundtag1);
        listtag.add(compoundtag1);
        compoundtag.put("ChargedProjectiles", listtag);
    }


    private static List<ItemStack> getChargedProjectiles(ItemStack stack) {
        List<ItemStack> list = Lists.newArrayList();
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag != null && compoundtag.contains("ChargedProjectiles", 9)) {
            ListTag listtag = compoundtag.getList("ChargedProjectiles", 10);
            if (listtag != null) {
                for(int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag1 = listtag.getCompound(i);
                    list.add(ItemStack.of(compoundtag1));
                }
            }
        }
        return list;
    }

    private static void clearChargedProjectiles(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag != null) {
            ListTag listtag = compoundtag.getList("ChargedProjectiles", 9);
            listtag.clear();
            compoundtag.put("ChargedProjectiles", listtag);
        }
    }

    public static boolean containsChargedProjectile(ItemStack stack, Item item) {
        return getChargedProjectiles(stack).stream().anyMatch((ItemStack) -> {
            return ItemStack.is(item);
        });
    }



    private void shootProjectile(Level level, LivingEntity entity, InteractionHand hand, ItemStack stack, ItemStack itemstack, float f1, boolean b1, float f2, float f3, float f4) {
        if (!level.isClientSide) {
            boolean flag = itemstack.is(Items.FIREWORK_ROCKET);
            Object projectile;
            if (flag) {
                projectile = new FireworkRocketEntity(level, itemstack, entity, entity.getX(), entity.getEyeY() - (double)0.15F, entity.getZ(), true);
            } else {
                projectile = this.getArrow(level, entity, stack, itemstack);
                if (b1 || f4 != 0.0F) {
                    ((AbstractArrow)projectile).pickup = Pickup.CREATIVE_ONLY;
                }
            }

            if (entity instanceof CrossbowAttackMob) {
                CrossbowAttackMob crossbowattackmob = (CrossbowAttackMob)entity;
                crossbowattackmob.shootCrossbowProjectile(crossbowattackmob.getTarget(), stack, (Projectile)projectile, f4);
            } else {
                Vec3 vec31 = entity.getUpVector(1.0F);
                Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(f4 * ((float)Math.PI / 180F)), vec31.x, vec31.y, vec31.z);
                Vec3 vec3 = entity.getViewVector(1.0F);
                Vector3f vector3f = vec3.toVector3f().rotate(quaternionf);
                ((Projectile)projectile).shoot((double) vector3f.x(), (double) vector3f.y(), (double) vector3f.z(), f2, f3);
            }

            stack.hurtAndBreak(flag ? 3 : 1, entity, (LivingEntity) -> {
                LivingEntity.broadcastBreakEvent(hand);
            });
            level.addFreshEntity((Entity)projectile);
            level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, f1);
        }

    }
    
    private AbstractArrow getArrow(Level level, LivingEntity entity, ItemStack itemstack, ItemStack stack) {
        ArrowItem arrowitem = (ArrowItem)(stack.getItem() instanceof ArrowItem ? stack.getItem() : Items.ARROW);
        AbstractArrow abstractarrow = arrowitem.createArrow(level, stack, entity);
        if (entity instanceof Player) {
            abstractarrow.setCritArrow(true);
        }

        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + this.tier.getAttackDamageBonus());

        abstractarrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
        abstractarrow.setShotFromCrossbow(true);
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PIERCING, itemstack);
        if (i > 0) {
            abstractarrow.setPierceLevel((byte)i);
        }

        return abstractarrow;
    }

    public void CustomPerformShooting(Level level, LivingEntity entity_, InteractionHand hand, ItemStack stack_, float n1, float n2) {
        if (entity_ instanceof Player player && net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack_, entity_.level(), player, 1, true) < 0) return;
        List<ItemStack> list = getChargedProjectiles(stack_);
        float[] afloat = getShotPitches(entity_.getRandom());

        for(int i = 0; i < list.size(); ++i) {
            ItemStack itemstack = list.get(i);
            boolean flag = entity_ instanceof Player && ((Player)entity_).getAbilities().instabuild;
            if (!itemstack.isEmpty()) {
                if (i == 0) {
                    shootProjectile(level, entity_, hand, stack_, itemstack, afloat[i], flag, n1, n2, 0.0F);
                } else if (i == 1) {
                    shootProjectile(level, entity_, hand, stack_, itemstack, afloat[i], flag, n1, n2, -10.0F);
                } else if (i == 2) {
                    shootProjectile(level, entity_, hand, stack_, itemstack, afloat[i], flag, n1, n2, 10.0F);
                }
            }
        }

        onCrossbowShot(level, entity_, stack_);
    }

    private static float[] getShotPitches(RandomSource random) {
        boolean flag = random.nextBoolean();
        return new float[]{1.0F, getRandomShotPitch(flag, random), getRandomShotPitch(!flag, random)};
    }

    private static float getRandomShotPitch(boolean b1, RandomSource random) {
        float f = b1 ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
    }

    private static void onCrossbowShot(Level level, LivingEntity entity, ItemStack stack) {
        if (entity instanceof ServerPlayer serverplayer) {
            if (!level.isClientSide) {
                CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, stack);
            }

            serverplayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }

        clearChargedProjectiles(stack);
    }


    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int chargeTime) {
        if (!level.isClientSide) {
            int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundevent = this.getStartSound(i);
            SoundEvent soundevent1 = i == 0 ? SoundEvents.CROSSBOW_LOADING_MIDDLE : null;
            float f = (float)(stack.getUseDuration() - chargeTime) / (float)this.getChargeDuration(stack);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }

            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), soundevent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }

            if (f >= 0.5F && soundevent1 != null && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                level.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), soundevent1, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    public int getUseDuration(ItemStack stack) {
        return this.getCustomChargeDuration(stack) + 3;
    }

    public int getCustomChargeDuration(ItemStack stack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? 25 - this.tier.getChargeTime() : 25 - 5 * i - this.tier.getChargeTime();
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }
    private SoundEvent getStartSound(int cases) {
        switch (cases) {
            case 1:
                return SoundEvents.CROSSBOW_QUICK_CHARGE_1;
            case 2:
                return SoundEvents.CROSSBOW_QUICK_CHARGE_2;
            case 3:
                return SoundEvents.CROSSBOW_QUICK_CHARGE_3;
            default:
                return SoundEvents.CROSSBOW_LOADING_START;
        }
    }
    private float getPowerForTime(int chargeTime, ItemStack stack) {
        float f = (float)chargeTime / ((float)this.getChargeDuration(stack) - (float)this.tier.getChargeTime());
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        List<ItemStack> list = getChargedProjectiles(stack);
        if (isCharged(stack) && !list.isEmpty()) {
            ItemStack itemstack = (ItemStack)list.get(0);
            tooltip.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemstack.getDisplayName()));
            if (tooltipFlag.isAdvanced() && itemstack.is(Items.FIREWORK_ROCKET)) {
                List<Component> list1 = Lists.newArrayList();
                Items.FIREWORK_ROCKET.appendHoverText(itemstack, level, list1, tooltipFlag);
                if (!list1.isEmpty()) {
                    for(int i = 0; i < list1.size(); ++i) {
                        list1.set(i, Component.literal("  ").append(list1.get(i)).withStyle(ChatFormatting.GRAY));
                    }

                    tooltip.addAll(list1);
                }
            }
        }
        tooltip.add(Component.translatable("archery.dpsvarmod.customacheryitem").withStyle(ChatFormatting.DARK_GREEN).append(Component.literal("+" + Float.toString(this.tier.getAttackDamageBonus())).withStyle(ChatFormatting.DARK_GREEN)));
    }
}
