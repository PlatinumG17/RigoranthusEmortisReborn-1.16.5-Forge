package com.platinumg17.rigoranthusemortisreborn.magica.common.datagen;

import com.platinumg17.rigoranthusemortisreborn.api.RigoranthusEmortisRebornAPI;
import com.platinumg17.rigoranthusemortisreborn.api.apimagic.recipe.CraftingPressRecipe;
import com.platinumg17.rigoranthusemortisreborn.canis.common.lib.EmortisConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class CraftingPressRecipeBuilder {

    CraftingPressRecipe recipe;
    public CraftingPressRecipeBuilder() { this.recipe = new CraftingPressRecipe(); }

    public static CraftingPressRecipeBuilder builder() { return new CraftingPressRecipeBuilder(); }

    public CraftingPressRecipeBuilder withResult(IItemProvider output) {
        this.recipe.output = new ItemStack(output);
        return this;
    }
    public CraftingPressRecipeBuilder withResult(ItemStack output){
        this.recipe.output = output;
        return this;
    }

    public CraftingPressRecipeBuilder withCategory(RigoranthusEmortisRebornAPI.PatchouliCategories category){
        this.recipe.category = category.name();
        return this;
    }

    public CraftingPressRecipeBuilder withReagent(IItemProvider reagentProvider){
        this.recipe.reagent = Ingredient.of(reagentProvider);
        return this;
    }

    public CraftingPressRecipeBuilder withReagent(Ingredient reagentItem){
        this.recipe.reagent = reagentItem;
        return this;
    }

    public CraftingPressRecipeBuilder withBaseItem(Ingredient baseItem){
        this.recipe.base = baseItem;
        return this;
    }

    public CraftingPressRecipeBuilder withBaseItem(IItemProvider baseProvider){
        this.recipe.base = Ingredient.of(baseProvider);
        return this;
    }

    public CraftingPressRecipe build(){
        if(recipe.id.getPath().equals("empty"))
            recipe.id = new ResourceLocation(EmortisConstants.MOD_ID, recipe.output.getItem().getRegistryName().getPath());
        return recipe;
    }
}