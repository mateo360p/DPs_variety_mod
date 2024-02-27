package com.mateo360p.dpsvarmod.util;

import com.mateo360p.dpsvarmod.dpsvarmod;
import com.mateo360p.dpsvarmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p1, CompletableFuture<HolderLookup.Provider> p2, CompletableFuture<TagLookup<Block>> p3, @Nullable ExistingFileHelper existingFileHelper) {
        super(p1, p2, p3, dpsvarmod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR).add
        (
            ModItems.DENDERITE_HELMET.get(),
            ModItems.DENDERITE_CHESTPLATE.get(),
            ModItems.DENDERITE_LEGGINGS.get(),
            ModItems.DENDERITE_BOOTS.get()
        );
    }
}
