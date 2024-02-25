package com.mateo360p.dpsvarmod.item;

import com.mateo360p.dpsvarmod.item.custombow.BowTiers;
import com.mateo360p.dpsvarmod.item.custombow.CustomBowItem;
import com.mateo360p.dpsvarmod.item.customsmithingtemplate.DenderiteSmithingTemplate;
import com.mateo360p.dpsvarmod.item.items.DPsTabItem;
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
    public static final DeferredRegister<Item> ITEMS;
    public static final DeferredRegister<Item> BOWS;
    public static final DeferredRegister<Item> CROSSBOWS;
    
// Items
    public static final RegistryObject<Item> DP_TAB_ITEM;
    public static final RegistryObject<Item> DENDERITE_UPGRADE_TEMPLATE;
    public static final RegistryObject<Item> DENDERITE_SCRAP;
    public static final RegistryObject<Item> DENDERITE_INGOT;
    public static final RegistryObject<Item> EGG_YAW;
    
// Foods
    public static final RegistryObject<Item> FRIED_EGGS;
    public static final RegistryObject<Item> BOILED_EGG;
// Items
    public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR;
    public static final RegistryObject<Item> DENDERITE_HORSE_ARMOR;
// Bows
    public static final RegistryObject<Item> DIAMOND_BOW;
    public static final RegistryObject<Item> NETHERITE_BOW;
    public static final RegistryObject<Item> DENDERITE_BOW;

// Crossbows
    public static final RegistryObject<Item> DIAMOND_CROSSBOW;
    public static final RegistryObject<Item> NETHERITE_CROSSBOW;
    public static final RegistryObject<Item> DENDERITE_CROSSBOW;
//ALL ITEMS
    static {
        //REGISTRIES
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "dpsvarmod");
        BOWS = DeferredRegister.create(ForgeRegistries.ITEMS, "dpsvarmod");
        CROSSBOWS = DeferredRegister.create(ForgeRegistries.ITEMS, "dpsvarmod");

        //ITEMS
        DP_TAB_ITEM = ITEMS.register("dps_tab_item", DPsTabItem::new);
        DENDERITE_UPGRADE_TEMPLATE = ITEMS.register("denderite_upgrade_smithing_template",
            DenderiteSmithingTemplate::createUpgrade);
        DENDERITE_SCRAP = ITEMS.register("denderite_scrap", () -> {
            return new Item(new Item.Properties().fireResistant().stacksTo(64));
        });
        DENDERITE_INGOT = ITEMS.register("denderite_ingot", () -> {
            return new Item(new Item.Properties().fireResistant().stacksTo(64));
        });
        EGG_YAW = ITEMS.register("egg_yolk_and_white", () -> {
            return new Item(new Item.Properties().stacksTo(64));
        });


        //FOODS
        FRIED_EGGS = ITEMS.register("fried_eggs", () -> {
            return new Item(new Item.Properties().stacksTo(64).food(ModFoods.FRIED_EGGS));
        });
        BOILED_EGG = ITEMS.register("boiled_egg", () -> {
            return new Item(new Item.Properties().stacksTo(64).food(ModFoods.BOILED_EGG));
        });


        //HORSE ARMORS
        NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor", () -> {
            return new HorseArmorItem(17, "netherite",new Item.Properties().fireResistant().stacksTo(1));
        });
        DENDERITE_HORSE_ARMOR = ITEMS.register("denderite_horse_armor", () -> {
            return new HorseArmorItem(25, "denderite",new Item.Properties().fireResistant().stacksTo(1));
        });


        //BOWS
        DIAMOND_BOW = BOWS.register("diamond_bow", () -> {
            return new CustomBowItem(BowTiers.DIAMOND, new Item.Properties());
        });
        NETHERITE_BOW = BOWS.register("netherite_bow", () -> {
            return new CustomBowItem(BowTiers.NETHERITE, new Item.Properties().fireResistant());
        });
        DENDERITE_BOW = BOWS.register("denderite_bow", () -> {
            return new CustomBowItem(BowTiers.DENDERITE, new Item.Properties().fireResistant());
        });


        //CROSSBOWS--these are prototypes
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
