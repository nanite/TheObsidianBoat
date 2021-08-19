package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeDataProvider extends RecipeProvider {

    public RecipeDataProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(OB.ITEM.get())
                .define('#', Items.OBSIDIAN)
                .pattern("# #")
                .pattern("###")
                .group("boat")
                .unlockedBy("has_obsidian", has(Tags.Items.OBSIDIAN))
                .save(consumer);
    }

    @Override
    public String getName() {
        return "The Recipes";
    }
}
