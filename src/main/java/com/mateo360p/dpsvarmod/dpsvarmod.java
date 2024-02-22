package com.mateo360p.dpsvarmod;

import com.mateo360p.dpsvarmod.block.ModBlocks;
import com.mateo360p.dpsvarmod.config.Config;
import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("dpsvarmod")
public class dpsvarmod {
    //This is a test
    public static final String MODID = "dpsvarmod";
    private static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
    private static final IEventBus EVENT_BUS;

    public dpsvarmod() {
        EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(Type.COMMON, Config.SPEC);

        ModCreativeModeTabs.register(MOD_EVENT_BUS);

        ModBlocks.BLOCKS.register(MOD_EVENT_BUS);
        ModItems.ITEMS.register(MOD_EVENT_BUS);
        ModItems.HORSE_ARMORS.register(MOD_EVENT_BUS);
        ModItems.BOWS.register(MOD_EVENT_BUS);
        ModItems.CROSSBOWS.register(MOD_EVENT_BUS);
    }

    static {
        EVENT_BUS = MinecraftForge.EVENT_BUS;
    }
}
