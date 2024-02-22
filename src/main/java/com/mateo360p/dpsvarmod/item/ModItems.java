package com.mateo360p.dpsvarmod.item;

import com.mateo360p.dpsvarmod.item.custombow.BowTiers;
import com.mateo360p.dpsvarmod.item.custombow.CustomBowItem;
import com.mateo360p.dpsvarmod.item.customsmithingtemplate.DenderiteSmithingTemplate;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
//Blocks are automatically added

// Items
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> DP_TAB_ITEM;
    public static final RegistryObject<Item> DENDERITE_UPGRADE_TEMPLATE;
    public static final RegistryObject<Item> DENDERITE_SCRAP;
    public static final RegistryObject<Item> DENDERITE_INGOT;
// Items
    public static final DeferredRegister<Item> HORSE_ARMORS;
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR;
    public static final RegistryObject<Item> DENDERITE_HORSE_ARMOR;
// Bows
    public static final DeferredRegister<Item> BOWS;
    public static final RegistryObject<Item> DIAMOND_BOW;
    public static final RegistryObject<Item> NETHERITE_BOW;
    public static final RegistryObject<Item> DENDERITE_BOW;

// Crossbows
    public static final DeferredRegister<Item> CROSSBOWS;
    public static final RegistryObject<Item> DIAMOND_CROSSBOW;
    public static final RegistryObject<Item> NETHERITE_CROSSBOW;
    public static final RegistryObject<Item> DENDERITE_CROSSBOW;
//ALL ITEMS
    static {
        ////ITEMS

        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "dpsvarmod");
        DP_TAB_ITEM = ITEMS.register("dps_tab_item", () -> {
            return new Item(new Item.Properties().fireResistant().stacksTo(1));
        });
        DENDERITE_UPGRADE_TEMPLATE = ITEMS.register("denderite_upgrade_smithing_template",
            DenderiteSmithingTemplate::createUpgrade);
        DENDERITE_SCRAP = ITEMS.register("denderite_scrap", () -> {
            return new Item(new Item.Properties().fireResistant().stacksTo(64));
        });
        DENDERITE_INGOT = ITEMS.register("denderite_ingot", () -> {
            return new Item(new Item.Properties().fireResistant().stacksTo(64));
        });


        //HORSE ARMORS
        HORSE_ARMORS = DeferredRegister.create(ForgeRegistries.ITEMS, "dpsvarmod");
        NETHERITE_HORSE_ARMOR = HORSE_ARMORS.register("netherite_horse_armor", () -> {
            return new HorseArmorItem(17, "netherite",new Item.Properties().fireResistant().stacksTo(1));
        });
        DENDERITE_HORSE_ARMOR = HORSE_ARMORS.register("denderite_horse_armor", () -> {
            return new HorseArmorItem(25, "denderite",new Item.Properties().fireResistant().stacksTo(1));
        });


        //BOWS
        BOWS = DeferredRegister.create(ForgeRegistries.ITEMS, "dpsvarmod");
        DIAMOND_BOW = BOWS.register("diamond_bow", () -> {
            return new CustomBowItem(BowTiers.DIAMOND, new Item.Properties());
        });
        NETHERITE_BOW = BOWS.register("netherite_bow", () -> {
            return new CustomBowItem(BowTiers.NETHERITE, new Item.Properties().fireResistant());
        });
        DENDERITE_BOW = BOWS.register("denderite_bow", () -> {
            return new CustomBowItem(BowTiers.DENDERITE, new Item.Properties().fireResistant());
        });


        //CROSSBOWS
        CROSSBOWS = DeferredRegister.create(ForgeRegistries.ITEMS, "dpsvarmod");
        DIAMOND_CROSSBOW = CROSSBOWS.register("diamond_crossbow", () -> {
            return new CrossbowItem(new Item.Properties().durability(793));
        });
        NETHERITE_CROSSBOW = CROSSBOWS.register("netherite_crossbow", () -> {
            return new CrossbowItem(new Item.Properties().durability(1372).fireResistant());
        });
        DENDERITE_CROSSBOW = CROSSBOWS.register("denderite_crossbow", () -> {
            return new CrossbowItem(new Item.Properties().durability(2390).fireResistant());
        });
    }
}
