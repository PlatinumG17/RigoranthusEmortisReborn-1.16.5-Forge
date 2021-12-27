package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.blocks.BlockInit;
import com.platinumg17.rigoranthusemortisreborn.blocks.DecorativeOrStorageBlocks;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.CanisTags;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;
import com.platinumg17.rigoranthusemortisreborn.core.init.Registration;
import com.platinumg17.rigoranthusemortisreborn.magica.common.lib.RitualLib;
import com.platinumg17.rigoranthusemortisreborn.magica.common.spell.effect.EffectHeal;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.BlockRegistry;
import com.platinumg17.rigoranthusemortisreborn.magica.setup.MagicItemsRegistry;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    public static ITag.INamedTag<Item> DOMINION_GEM_TAG = ItemTags.bind("forge:gems/mana");
    public static ITag.INamedTag<Item> DOMINION_GEM_BLOCK_TAG = ItemTags.bind("forge:storage_blocks/mana");
    public static ITag.INamedTag<Block> DECORATIVE_RE =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "re_decorative"));
    public static ITag.INamedTag<Block> MAGIC_SAPLINGS =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "magic_saplings"));
    public static ITag.INamedTag<Block> MAGIC_PLANTS =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "magic_plants"));
    public static ITag.INamedTag<Item> MAGIC_FOOD = ItemTags.bind("rigoranthusemortisreborn:magic_food");

    public static ITag.INamedTag<Block> AZULOREAL_LOGS_TAG =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "azuloreal_logs"));
    public static ITag.INamedTag<Block> JESSIC_LOGS_TAG =  BlockTags.createOptional(new ResourceLocation(EmortisConstants.MOD_ID, "jessic_logs"));

    public static Ingredient DOMINION_GEM = Ingredient.of(MagicItemsRegistry.dominionGem);
    public static Ingredient DOMINION_GEM_BLOCK = Ingredient.of(BlockRegistry.DOMINION_GEM_BLOCK);
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        {
            //makeArmor("novice", consumer, MagicItemsRegistry.DOMINION_FIBER);
            makeArmor("apprentice", consumer, ItemInit.GHAST_IRON_INGOT.get());
            makeArmor("emortic", consumer, MagicItemsRegistry.DWELLER_FLESH);

            CookingRecipeBuilder.smelting(Ingredient.of(Registration.RECONDITE_ORE.get()), MagicItemsRegistry.dominionGem,0.5f, 200)
                    .unlockedBy("has_ore", InventoryChangeTrigger.Instance.hasItems(Registration.RECONDITE_ORE.get())).save(consumer);

            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.emorticOrigins).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .requires(DOMINION_GEM, 1)
                    .requires(Items.BOOK).save(consumer);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.unadornedRing).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("xyx")
                    .pattern("x x")
                    .pattern("xxx").define('x', Tags.Items.NUGGETS_IRON).define('y', Tags.Items.INGOTS_IRON).save(consumer);

            ShapedRecipeBuilder.shaped(MagicItemsRegistry.unadornedAmulet).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("xyx")
                    .pattern("l l")
                    .pattern(" l ").define('x', Tags.Items.NUGGETS_IRON).define('y', Tags.Items.INGOTS_IRON).define('l', Tags.Items.LEATHER).save(consumer);
            
            ShapedRecipeBuilder.shaped(BlockRegistry.DOMINION_JAR).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("xyx")
                    .pattern("x x")
                    .pattern("xxx").define('x', Tags.Items.GLASS).define('y', BlockInit.OPULENT_MAGMA.get()).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.EMORTIC_CRAFTING_PRESS_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("sgs")
                    .pattern("xyx")
                    .pattern("aba").define('x', Registration.POWDERED_ESOTERICUM.get()).define('y', Items.PISTON).define('g', Tags.Items.GLASS_PANES).define('s', Items.OBSIDIAN)
                    .define('a', Tags.Items.GEMS_DIAMOND).define('b', Items.ENCHANTING_TABLE).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.SPLINTERED_PEDESTAL).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("xgx")
                    .pattern(" m ")
                    .pattern("bmb").define('b', Items.BLACKSTONE).define('x', Tags.Items.INGOTS_GOLD).define('m', Items.MOSSY_STONE_BRICKS)
                    .define('g', Tags.Items.GLASS_PANES).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.PSYGLYPHIC_AMALG_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("ydy")
                    .pattern("gpg")
                    .pattern("beb").define('g', Tags.Items.INGOTS_GOLD).define('b', Items.BLACKSTONE).define('p', Tags.Items.GLASS_PANES).define('d', MagicItemsRegistry.dominionGem)
                    .define('e', Registration.POWDERED_ESOTERICUM.get()).define('y', Tags.Items.GEMS_DIAMOND).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.TABLE_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("xxx")
                    .pattern("yzy")
                    .pattern("y y").define('x',Ingredient.of(DecorativeOrStorageBlocks.AZULOREAL_SLAB.get()))
                    .define('y', Items.STICK)
                    .define('z', Ingredient.of(CanisTags.AZULOREAL_LOGS)).save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.EMORTIC_CORTEX_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("sys")
                    .pattern("yby")
                    .pattern("ses").define('s',  Tags.Items.STONE).define('y', Tags.Items.GLASS).define('b', BlockInit.DWELLER_BRAIN.get().asItem())
                    .define('e', Registration.POWDERED_ESOTERICUM.get()).save(consumer);

//            ShapedRecipeBuilder.shaped(MagicItemsRegistry.BLANK_PARCHMENT, 1).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
//                    .pattern("yyy")
//                    .pattern("yxy")
//                    .pattern("yyy").define('x', Items.PAPER).define('y', MagicItemsRegistry.DOMINION_FIBER).save(consumer);

//            ShapelessRecipeBuilder.shapeless(MagicItemsRegistry.spellParchment, 1).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
//                    .requires(MagicItemsRegistry.BLANK_PARCHMENT, 1)
//                    .requires(Ingredient.of(ItemTags.bind("forge:gems/mana")), 4)
//                    .save(consumer);

            ShapedRecipeBuilder.shaped(BlockRegistry.DOMINION_EXTRACTOR_BLOCK).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern(" s ")
                    .pattern("gig")
                    .pattern(" s ")
                    .define('g', Tags.Items.INGOTS_GOLD)
                    .define('s', DOMINION_GEM)
                    .define('i', Items.LAVA_BUCKET).save(consumer);

            shapelessBuilder(BlockRegistry.DOMINION_GEM_BLOCK,1).requires(DOMINION_GEM, 9).save(consumer);
            shapelessBuilder(MagicItemsRegistry.dominionGem, 9).requires(BlockRegistry.DOMINION_GEM_BLOCK,1).save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "dominion_gem_from_block"));

            ShapedRecipeBuilder.shaped(Items.ARROW, 2)
                    .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("x")
                    .pattern("y")
                    .pattern("z")
                    .define('x', ItemInit.RAZORTOOTH_KUNAI.get())
                    .define('y', Items.STICK)
                    .define('z', Items.FEATHER)
                    .save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, "razortooth_to_arrow"));

            shapelessBuilder(BlockRegistry.RITUAL_BLOCK)
                    .requires(BlockRegistry.SPLINTERED_PEDESTAL)
                    .requires(Recipes.DOMINION_GEM_BLOCK_TAG)
                    .requires(Ingredient.of(Tags.Items.INGOTS_GOLD), 3)
                    .save(consumer);

            shapelessBuilder(getRitualItem(RitualLib.RESTORATION))
                    .requires(BlockRegistry.ICHOR_JAR)
                    .requires(Items.GOLDEN_APPLE)
                    .requires(RigoranthusEmortisRebornAPI.getInstance().getGlyphItem(EffectHeal.INSTANCE), 1)
                    .save(consumer);

            shapelessBuilder(MagicItemsRegistry.GREATER_EXPERIENCE_GEM)
                    .requires(MagicItemsRegistry.EXPERIENCE_GEM, 4)
                    .save(consumer);
            shapelessBuilder(MagicItemsRegistry.EXPERIENCE_GEM, 4)
                    .requires(MagicItemsRegistry.GREATER_EXPERIENCE_GEM)
                    .save(consumer);

            STONECUTTER_COUNTER = 1;

            ShapedRecipeBuilder.shaped(BlockRegistry.EMORTIC_RELAY).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                    .pattern("gbg")
                    .pattern("gMg")
                    .pattern("gbg")
                    .define('g', Tags.Items.INGOTS_GOLD)
                    .define('M', DOMINION_GEM_BLOCK)
                    .define('b', Items.BLACKSTONE)
                    .save(consumer);

            shapelessBuilder(getRitualItem(RitualLib.BINDING))
                    .requires(BlockRegistry.ICHOR_JAR)
                    .requires(MagicItemsRegistry.BLANK_PARCHMENT)
                    .requires(Items.ENDER_PEARL, 1)
                    .requires(DOMINION_GEM, 3)
                    .save(consumer);
        }
    }

    public Item getRitualItem(String id){
        return RigoranthusEmortisRebornAPI.getInstance().getRitualItemMap().get(id);
    }

    public static ShapedRecipeBuilder makeWood(IItemProvider logs, IItemProvider wood, int count){
        return ShapedRecipeBuilder.shaped(wood, count).unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .pattern("xx ")
                .pattern("xx ").define('x', logs);
    }
    private static void shapedWoodenTrapdoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider trapdoor, IItemProvider input) {
        ShapedRecipeBuilder.shaped(trapdoor, 2).define('#', input).pattern("###").pattern("###").group("wooden_trapdoor")
                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE)).save(recipeConsumer);
    }
    public static void shapedWoodenStairs(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stairs, IItemProvider input) {
        ShapedRecipeBuilder.shaped(stairs, 4)
                .define('#', input)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###").unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(recipeConsumer);
    }
    private static void shapelessWoodenButton(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider button, IItemProvider input) {
        ShapelessRecipeBuilder.shapeless(button).requires(input)
                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(recipeConsumer);
    }
    private static void strippedLogToWood(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider stripped, IItemProvider output) {
        ShapedRecipeBuilder.shaped(output, 3).define('#', stripped).pattern("##").pattern("##").group("bark")
                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(recipeConsumer);
    }
//    private static void shapedWoodenDoor(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider door, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(door, 3).define('#', input).pattern("##").pattern("##").pattern("##").group("wooden_door")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenFence(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fence, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(fence, 3).define('#', Items.STICK).define('W', input).pattern("W#W").pattern("W#W").group("wooden_fence")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenFenceGate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider fenceGate, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(fenceGate).define('#', Items.STICK).define('W', input).pattern("#W#").pattern("#W#").group("wooden_fence_gate")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenPressurePlate(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider pressurePlate, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(pressurePlate).define('#', input).pattern("##").group("wooden_pressure_plate")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
//                .save(recipeConsumer);
//    }
//    private static void shapedWoodenSlab(Consumer<IFinishedRecipe> recipeConsumer, IItemProvider slab, IItemProvider input) {
//        ShapedRecipeBuilder.shaped(slab, 6).define('#', input).pattern("###").group("wooden_slab")
//                .unlockedBy("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
//                .save(recipeConsumer);
//    }

    public ShapelessRecipeBuilder shapelessBuilder(IItemProvider result){
        return shapelessBuilder(result, 1);
    }

    public ShapelessRecipeBuilder shapelessBuilder(IItemProvider result, int resultCount){
        return ShapelessRecipeBuilder.shapeless(result, resultCount).unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE));
    }

    private static int STONECUTTER_COUNTER = 0;
    public static void makeStonecutter(Consumer<IFinishedRecipe> consumer, IItemProvider input, IItemProvider output, String reg){
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), output).unlocks("has_journal",InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE)).save(consumer, new ResourceLocation(EmortisConstants.MOD_ID, reg + "_"+STONECUTTER_COUNTER));
        STONECUTTER_COUNTER++;
    }

    public static void makeArmor(String prefix, Consumer<IFinishedRecipe> consumer, Item material){
        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_boots")))
                .pattern("   ")
                .pattern("x x")
                .pattern("x x").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_leggings")))
                .pattern("xxx")
                .pattern("x x")
                .pattern("x x").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_hood")))
                .pattern("xxx")
                .pattern("x x")
                .pattern("   ").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ForgeRegistries.ITEMS.getValue(new ResourceLocation(EmortisConstants.MOD_ID, prefix + "_robes")))
                .pattern("x x")
                .pattern("xxx")
                .pattern("xxx").define('x', material).group(EmortisConstants.MOD_ID)
                .unlockedBy("has_journal", InventoryChangeTrigger.Instance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer);
    }
}