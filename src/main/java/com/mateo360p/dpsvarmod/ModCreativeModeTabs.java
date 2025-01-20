package com.mateo360p.dpsvarmod;

import com.mateo360p.dpsvarmod.block.ModBlocks;
import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = //creative tabs register
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, dpsvarmod.MODID);

    public static final RegistryObject<CreativeModeTab> DPS_TAB = CREATIVE_MODE_TABS.register("dps_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DP_TAB_ITEM.get()))
                    .title(Component.translatable("creativetab.dps_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //FOOD STUFF
                        output.accept(ModItems.EGG_YAW.get());
                        output.accept(ModItems.BOILED_EGG.get());
                        output.accept(ModItems.FRIED_EGGS.get());
                        output.accept(ModItems.CHEESE.get());
                        output.accept(ModItems.COMBINED_ROTTEN_FLESH.get());
                        output.accept(ModItems.LETTUCE.get());
                        output.accept(ModItems.TOMATO.get());
                        output.accept(ModItems.ONION.get());
                        output.accept(ModItems.GARLIC.get());
                        output.accept(ModItems.LEMON.get());
                        output.accept(ModItems.OIL_BOTTLE.get());
                        output.accept(ModItems.LEMON_BOTTLE.get());
                        output.accept(ModBlocks.CARROT_BASKET.get());
                        output.accept(ModBlocks.POTATO_BASKET.get());
                        output.accept(ModBlocks.APPLE_BASKET.get());
                        output.accept(ModBlocks.BEETROOT_BASKET.get());
                        output.accept(ModBlocks.COOKING_TABLE.get());
                        //DENDERITE STUFF
                        output.accept(ModItems.DENDERITE_UPGRADE_TEMPLATE.get());
                        output.accept(ModItems.DENDERITE_SCRAP.get());
                        output.accept(ModItems.DENDERITE_INGOT.get());
                        output.accept(ModBlocks.DENDERITE_ORE.get());
                        output.accept(ModBlocks.DENDERITE_SCRAP_BLOCK.get());
                        output.accept(ModBlocks.DENDERITE_BLOCK.get());
                        output.accept(ModItems.DENDERITE_SWORD.get());;
                        output.accept(ModItems.DENDERITE_PICKAXE.get());
                        output.accept(ModItems.DENDERITE_AXE.get());
                        output.accept(ModItems.DENDERITE_SHOVEL.get());
                        output.accept(ModItems.DENDERITE_HOE.get());
                        output.accept(ModItems.DENDERITE_HELMET.get());
                        output.accept(ModItems.DENDERITE_CHESTPLATE.get());
                        output.accept(ModItems.DENDERITE_LEGGINGS.get());
                        output.accept(ModItems.DENDERITE_BOOTS.get());
                        //HORSE ARMORS STUFF
                        output.accept(ModItems.NETHERITE_HORSE_ARMOR.get());
                        output.accept(ModItems.DENDERITE_HORSE_ARMOR.get());
                        //ARCHERY STUFF
                        output.accept(ModItems.DIAMOND_BOW.get());
                        output.accept(ModItems.NETHERITE_BOW.get());
                        output.accept(ModItems.DENDERITE_BOW.get());
                        output.accept(ModItems.DIAMOND_CROSSBOW.get());
                        output.accept(ModItems.NETHERITE_CROSSBOW.get());
                        output.accept(ModItems.DENDERITE_CROSSBOW.get());

                        //...(ModItems.<NAME>.get())---> get() solo es usado para cosas del mod, no para los vanilla.
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
