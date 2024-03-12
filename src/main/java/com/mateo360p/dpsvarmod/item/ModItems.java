package com.mateo360p.dpsvarmod.item;

import com.mateo360p.dpsvarmod.block.ModBlocks;
import com.mateo360p.dpsvarmod.item.itemUtil.ModArmorMaterials;
import com.mateo360p.dpsvarmod.item.itemUtil.ModBowTiers;
import com.mateo360p.dpsvarmod.item.itemUtil.ModFoods;
import com.mateo360p.dpsvarmod.item.items.*;
import com.mateo360p.dpsvarmod.item.itemUtil.ModCrossbowTiers;
import com.mateo360p.dpsvarmod.item.itemUtil.ModToolTiers;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> LEMON;
    public static final RegistryObject<Item> GARLIC;
    public static final RegistryObject<Item> ONION;
    
// Foods
    public static final RegistryObject<Item> LETTUCE;
    public static final RegistryObject<Item> TOMATO;
    public static final RegistryObject<Item> CORN;
    public static final RegistryObject<Item> FRIED_EGGS;
    public static final RegistryObject<Item> BOILED_EGG;
    public static final RegistryObject<Item> CHEESE;
    public static final RegistryObject<Item> COMBINED_ROTTEN_FLESH;
    public static final RegistryObject<Item> LEMON_BOTTLE;
    public static final RegistryObject<Item> OIL_BOTTLE;

// Horse Armors
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

// Tools & Swords
    public static final RegistryObject<Item> DENDERITE_SWORD;
    public static final RegistryObject<Item> DENDERITE_PICKAXE;
    public static final RegistryObject<Item> DENDERITE_AXE;
    public static final RegistryObject<Item> DENDERITE_SHOVEL;
    public static final RegistryObject<Item> DENDERITE_HOE;

// Armor
public static final RegistryObject<Item> DENDERITE_HELMET;
    public static final RegistryObject<Item> DENDERITE_CHESTPLATE;
    public static final RegistryObject<Item> DENDERITE_LEGGINGS;
    public static final RegistryObject<Item> DENDERITE_BOOTS;

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
        LEMON = ITEMS.register("lemon", () -> {
            return new Item(new Item.Properties().stacksTo(64));
        });
        GARLIC = ITEMS.register("garlic", () -> {
            return new Item(new Item.Properties().stacksTo(64));
        });
        ONION = ITEMS.register("onion", () -> {
            return new ItemNameBlockItem(ModBlocks.ONION_CROP.get(),new Item.Properties().stacksTo(64));
        });
        OIL_BOTTLE = ITEMS.register("vegetable_oil_bottle", () -> {
            return new CustomContainerItem(Items.GLASS_BOTTLE, new Item.Properties().stacksTo(64));
        });


        //FOODS

        /*
        [TEST_DRINK] = [ITEMS].register(-String- [name], () -> {
            return new CustomDrinkableBottleItem(-Item- [REMAIN ITEM], -int- [DRINK DURATION], -boolean- [REMAIN ABLE], new Item.Properties.[properties]));
        });
        */

        FRIED_EGGS = ITEMS.register("fried_eggs", () -> {
            return new Item(new Item.Properties().stacksTo(64).food(ModFoods.FRIED_EGGS));
        });
        BOILED_EGG = ITEMS.register("boiled_egg", () -> {
            return new Item(new Item.Properties().stacksTo(64).food(ModFoods.BOILED_EGG));
        });
        LETTUCE = ITEMS.register("lettuce", () -> {
            return new ItemNameBlockItem(ModBlocks.LETTUCE_CROP.get(), new Item.Properties().stacksTo(64).food(ModFoods.LETTUCE));
        });
        TOMATO = ITEMS.register("tomato", () -> {
            return new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(),new Item.Properties().stacksTo(64).food(ModFoods.TOMATO));
        });
        CORN = ITEMS.register("corn", () -> {
            return new Item(new Item.Properties().stacksTo(64).food(ModFoods.CORN));
        });
        CHEESE = ITEMS.register("cheese", () -> {
            return new Item(new Item.Properties().stacksTo(64).food(ModFoods.CHEESE));
        });
        LEMON_BOTTLE = ITEMS.register("lemon_juice_bottle", () -> {
            return new CustomDrinkableItem(Items.GLASS_BOTTLE,32, true, new Item.Properties().stacksTo(64).food(ModFoods.LEMON_BOTTLE));
        });
        COMBINED_ROTTEN_FLESH = ITEMS.register("combined_rotten_flesh", () -> {
            return new Item(new Item.Properties().stacksTo(64).food(ModFoods.COMBINED_ROTTEN_FLESH));
        });




        //HORSE ARMORS
        NETHERITE_HORSE_ARMOR = ITEMS.register("netherite_horse_armor", () -> {
            return new HorseArmorItem(17, "netherite",new Item.Properties().fireResistant().stacksTo(1));
        });
        DENDERITE_HORSE_ARMOR = ITEMS.register("denderite_horse_armor", () -> {
            return new HorseArmorItem(25, "denderite",new Item.Properties().fireResistant().stacksTo(1));
        });


        //BOWS
        /*
        [TEST_BOW] = [BOWS].register(-String- [name], () -> {
            return new CustomBowItem(ModBowTiers.[TIER], new Item.Properties.[properties]));
        });
        */
        DIAMOND_BOW = BOWS.register("diamond_bow", () -> {
            return new CustomBowItem(ModBowTiers.DIAMOND, new Item.Properties());
        });
        NETHERITE_BOW = BOWS.register("netherite_bow", () -> {
            return new CustomBowItem(ModBowTiers.NETHERITE, new Item.Properties().fireResistant());
        });
        DENDERITE_BOW = BOWS.register("denderite_bow", () -> {
            return new CustomBowItem(ModBowTiers.DENDERITE, new Item.Properties().fireResistant());
        });


        //CROSSBOWS--these are prototypes
        DIAMOND_CROSSBOW = CROSSBOWS.register("diamond_crossbow", () -> {
            return new CustomCrossbowItem(ModCrossbowTiers.DIAMOND, new Item.Properties());
        });
        NETHERITE_CROSSBOW = CROSSBOWS.register("netherite_crossbow", () -> {
            return new CustomCrossbowItem(ModCrossbowTiers.NETHERITE, new Item.Properties().fireResistant());
        });
        DENDERITE_CROSSBOW = CROSSBOWS.register("denderite_crossbow", () -> {
            return new CustomCrossbowItem(ModCrossbowTiers.DENDERITE, new Item.Properties().fireResistant());
        });


        //TOOLS & SWORDS
        DENDERITE_SWORD = ITEMS.register("denderite_sword", () -> {
            return new SwordItem(ModToolTiers.DENDERITE, 4, -2.2F, (new Item.Properties()).fireResistant());
        });
        DENDERITE_PICKAXE = ITEMS.register("denderite_pickaxe", () -> {
            return new PickaxeItem(ModToolTiers.DENDERITE, 1, -2.7F, (new Item.Properties()).fireResistant());
        });
        DENDERITE_AXE = ITEMS.register("denderite_axe", () -> {
            return new AxeItem(ModToolTiers.DENDERITE, 6.0F, -2.8F, (new Item.Properties()).fireResistant());
        });
        DENDERITE_SHOVEL = ITEMS.register("denderite_shovel", () -> {
            return new ShovelItem(ModToolTiers.DENDERITE, 1.45F, -2.8F, (new Item.Properties()).fireResistant());
        });
        DENDERITE_HOE = ITEMS.register("denderite_hoe", () -> {
            return new HoeItem(ModToolTiers.DENDERITE, -4, 0.0F, (new Item.Properties()).fireResistant());
        });

        //ARMOR
        DENDERITE_HELMET = ITEMS.register("denderite_helmet", () -> {
            return new ArmorItem(ModArmorMaterials.DENDERITE, ArmorItem.Type.HELMET, (new Item.Properties()).fireResistant());
        });
        DENDERITE_CHESTPLATE = ITEMS.register("denderite_chestplate", () -> {
            return new ArmorItem(ModArmorMaterials.DENDERITE, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).fireResistant());
        });
        DENDERITE_LEGGINGS = ITEMS.register("denderite_leggings", () -> {
            return new ArmorItem(ModArmorMaterials.DENDERITE, ArmorItem.Type.LEGGINGS, (new Item.Properties()).fireResistant());
        });
        DENDERITE_BOOTS = ITEMS.register("denderite_boots", () -> {
            return new ArmorItem(ModArmorMaterials.DENDERITE, ArmorItem.Type.BOOTS, (new Item.Properties()).fireResistant());
        });
    }
}
