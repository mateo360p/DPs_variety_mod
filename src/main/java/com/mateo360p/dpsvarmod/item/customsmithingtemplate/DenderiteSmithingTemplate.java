package com.mateo360p.dpsvarmod.item.customsmithingtemplate;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

public class DenderiteSmithingTemplate extends SmithingTemplateItem {
    private static final String DESCRIPTION_ID =
    Util.makeDescriptionId("item", new ResourceLocation("dpsvarmod", "smithing_template"));
    private static final Component DENDERITE_UPGRADE_TEXT;
    private static final Component DENDERITE_UPGRADE_APPLIES_TO_TEXT;
    private static final Component DENDERITE_UPGRADE_INGREDIENTS_TEXT;
    private static final Component DENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT;
    private static final Component DENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT;
    private static final ChatFormatting TITLE_FORMATTING;
    private static final ChatFormatting DESCRIPTION_FORMATTING;
    private static final ResourceLocation EMPTY_ARMOR_SLOT_HELMET_TEXTURE;
    private static final ResourceLocation EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE;
    private static final ResourceLocation EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE;
    private static final ResourceLocation EMPTY_ARMOR_SLOT_BOOTS_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_INGOT_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_SWORD_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_PICKAXE_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_AXE_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_SHOVEL_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_HOE_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_HORSE_ARMOR_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_BOW_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_CROSSBOW_TEXTURE;
    private static final ResourceLocation EMPTY_SLOT_ELYTRA_TEXTURE;

    static {
        TITLE_FORMATTING = ChatFormatting.GRAY;
        DESCRIPTION_FORMATTING = ChatFormatting.BLUE;

        DENDERITE_UPGRADE_TEXT = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("dpsvarmod", "denderite_upgrade"))).withStyle(TITLE_FORMATTING);
        DENDERITE_UPGRADE_APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("dpsvarmod", "smithing_template.denderite_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMATTING);
        DENDERITE_UPGRADE_INGREDIENTS_TEXT = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("dpsvarmod", "smithing_template.denderite_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMATTING);
        DENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("dpsvarmod", "smithing_template.denderite_upgrade.base_slot_description")));
        DENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("dpsvarmod", "smithing_template.denderite_upgrade.additions_slot_description")));

        EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new ResourceLocation("item/empty_armor_slot_helmet");
        EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new ResourceLocation("item/empty_armor_slot_chestplate");
        EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new ResourceLocation("item/empty_armor_slot_leggings");
        EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new ResourceLocation("item/empty_armor_slot_boots");

        EMPTY_SLOT_INGOT_TEXTURE = new ResourceLocation("item/empty_slot_ingot");
        EMPTY_SLOT_SWORD_TEXTURE = new ResourceLocation("item/empty_slot_sword");
        EMPTY_SLOT_PICKAXE_TEXTURE = new ResourceLocation("item/empty_slot_pickaxe");
        EMPTY_SLOT_AXE_TEXTURE = new ResourceLocation("item/empty_slot_axe");
        EMPTY_SLOT_SHOVEL_TEXTURE = new ResourceLocation("item/empty_slot_shovel");
        EMPTY_SLOT_HOE_TEXTURE = new ResourceLocation("item/empty_slot_hoe");

        EMPTY_SLOT_HORSE_ARMOR_TEXTURE = new ResourceLocation("dpsvarmod:item/empty_slot_horse_armor");
        EMPTY_SLOT_BOW_TEXTURE = new ResourceLocation("dpsvarmod:item/empty_slot_bow");
        EMPTY_SLOT_CROSSBOW_TEXTURE = new ResourceLocation("dpsvarmod:item/empty_slot_crossbow");
        EMPTY_SLOT_ELYTRA_TEXTURE = new ResourceLocation("dpsvarmod:item/empty_slot_elytra");
    }

    public DenderiteSmithingTemplate(Component appliesToText, Component ingredientsText, Component titleText, Component baseSlotDescriptionText, Component additionsSlotDescriptionText, List<ResourceLocation> emptyBaseSlotTextures, List<ResourceLocation> emptyAdditionsSlotTextures) {
        super(appliesToText, ingredientsText, titleText, baseSlotDescriptionText, additionsSlotDescriptionText, emptyBaseSlotTextures, emptyAdditionsSlotTextures);
    }

    public static SmithingTemplateItem createUpgrade() {
        return new SmithingTemplateItem(DENDERITE_UPGRADE_APPLIES_TO_TEXT, DENDERITE_UPGRADE_INGREDIENTS_TEXT, DENDERITE_UPGRADE_TEXT, DENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, DENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, getDenderiteUpgradeEmptyBaseSlotTextures(), getDenderiteUpgradeEmptyAdditionsSlotTextures());
    }

    private static List<ResourceLocation> getDenderiteUpgradeEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_SLOT_PICKAXE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_SLOT_AXE_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE, EMPTY_SLOT_HOE_TEXTURE, EMPTY_SLOT_SHOVEL_TEXTURE, EMPTY_SLOT_HORSE_ARMOR_TEXTURE, EMPTY_SLOT_BOW_TEXTURE, EMPTY_SLOT_CROSSBOW_TEXTURE, EMPTY_SLOT_ELYTRA_TEXTURE);
    }

    private static List<ResourceLocation> getDenderiteUpgradeEmptyAdditionsSlotTextures() {
        return List.of(EMPTY_SLOT_INGOT_TEXTURE);
    }

    public String getDescriptionId() {
        return DESCRIPTION_ID;
    }
}
