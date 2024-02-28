package com.mateo360p.dpsvarmod.util;

import com.mateo360p.dpsvarmod.item.items.CustomBowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
        modid = "dpsvarmod",
        bus = Bus.FORGE,
        value = {Dist.CLIENT} 
)
public class FOVupdate {     
    public FOVupdate() {
    	
    }

    @SubscribeEvent
    public static void onFOVUpdate(ComputeFovModifierEvent event) {
        LivingEntity player = event.getPlayer();
        Item item = player.getUseItem().getItem();
        if (item instanceof CustomBowItem) {
            float FOVModifier = (float)player.getTicksUsingItem() / 20.0F;
            if (FOVModifier > 1.0F) {
                FOVModifier = 1.0F;
            } else {
                FOVModifier *= FOVModifier;
            }

            event.setNewFovModifier(event.getNewFovModifier() * (1.0F - FOVModifier * 0.15F));
        }

    }
}

