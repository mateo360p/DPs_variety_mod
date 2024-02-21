package com.mateo360p.dpsvarmod.block;

import com.mateo360p.dpsvarmod.dpsvarmod;
import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
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

//ALL BLOCKS
    static{
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, dpsvarmod.MODID);
        CARROT_BASKET = registerBlock("carrot_basket",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_ORANGE)),new Item.Properties());
        POTATO_BASKET = registerBlock("potato_basket",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_YELLOW)),new Item.Properties());
        APPLE_BASKET = registerBlock("apple_basket",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_ORANGE)),new Item.Properties());
        BEETROOT_BASKET = registerBlock("beetroot_basket",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).mapColor(MapColor.TERRACOTTA_YELLOW)),new Item.Properties());
        DENDERITE_BLOCK = registerBlock("denderite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).mapColor(MapColor.COLOR_MAGENTA)),new Item.Properties().fireResistant());
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
