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
                        //BLOCKS
                        output.accept(ModBlocks.CARROT_BASKET.get());
                        output.accept(ModBlocks.POTATO_BASKET.get());
                        output.accept(ModBlocks.APPLE_BASKET.get());
                        output.accept(ModBlocks.BEETROOT_BASKET.get());
                        output.accept(ModBlocks.DENDERITE_BLOCK.get());
                        //ITEMS
                        output.accept(ModItems.DENDERITE_SCRAP.get());
                        output.accept(ModItems.DENDERITE_INGOT.get());
                        //BOWS
                        output.accept(ModItems.DIAMOND_BOW.get());
                        output.accept(ModItems.NETHERITE_BOW.get());
                        output.accept(ModItems.DENDERITE_BOW.get());
                        //CROSSBOWS
                        output.accept(ModItems.DIAMOND_CROSSBOW.get());
                        output.accept(ModItems.NETHERITE_CROSSBOW.get());

                        //...(ModItems.<NAME>.get())---> get() solo es usado para cosas del mod, no para los vanilla.
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
