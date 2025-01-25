package com.mateo360p.dpsvarmod.block;

import com.mateo360p.dpsvarmod.block.blocks.customCrops.customCropBlock;
import com.mateo360p.dpsvarmod.block.blocks.customCrops.*;
import com.mateo360p.dpsvarmod.block.blocks.customFacingBlock;
import com.mateo360p.dpsvarmod.dpsvarmod;
import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS;
    public static final RegistryObject<Block> CARROT_BASKET;
    public static final RegistryObject<Block> POTATO_BASKET;
    public static final RegistryObject<Block> APPLE_BASKET;
    public static final RegistryObject<Block> BEETROOT_BASKET;
    public static final RegistryObject<Block> DENDERITE_BLOCK;
    public static final RegistryObject<Block> DENDERITE_ORE;
    public static final RegistryObject<Block> DENDERITE_SCRAP_BLOCK;
    public static final RegistryObject<Block> COOKING_TABLE;
    public static final RegistryObject<Block> ONION_CROP;
    public static final RegistryObject<Block> CORN_CROP;
    public static final RegistryObject<Block> TOMATO_CROP;
    public static final RegistryObject<Block> LETTUCE_CROP;

    //ALL BLOCKS
    static{
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, dpsvarmod.MODID);
        CARROT_BASKET = registerBlock("carrot_basket",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_ORANGE)),new Item.Properties());
        POTATO_BASKET = registerBlock("potato_basket",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_YELLOW)),new Item.Properties());
        APPLE_BASKET = registerBlock("apple_basket",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_RED)),new Item.Properties());
        BEETROOT_BASKET = registerBlock("beetroot_basket",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_PINK)),new Item.Properties());

        DENDERITE_BLOCK = registerBlock("denderite_block",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).mapColor(MapColor.COLOR_MAGENTA)),new Item.Properties().fireResistant());
        DENDERITE_ORE = registerBlock("denderite_ore",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).mapColor(MapColor.TERRACOTTA_MAGENTA)),new Item.Properties().fireResistant());
        DENDERITE_SCRAP_BLOCK = registerBlock("denderite_scrap_block",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).mapColor(MapColor.TERRACOTTA_PURPLE)),new Item.Properties().fireResistant());
        COOKING_TABLE = registerBlock("cooking_table",
                () -> new customFacingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)),new Item.Properties());

        ONION_CROP = BLOCKS.register("onion_crop",
                () -> new customCropBlock(ModItems.ONION));
        LETTUCE_CROP = BLOCKS.register("lettuce_crop",
                () -> new customCropBlock(ModItems.LETTUCE));
        TOMATO_CROP = BLOCKS.register("tomato_crop",
                TomatoCropBlock::new);
        CORN_CROP = BLOCKS.register("corn_crop",
                () -> new doubleCropBlock(ModItems.CORN));

    }

//Auto register
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
