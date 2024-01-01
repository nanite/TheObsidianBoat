package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, OBRegistry.BOAT_ITEM.get(), 1)
                .pattern("O O")
                .pattern("OOO")
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                .define('O', Blocks.OBSIDIAN).save(exporter, new ResourceLocation(ObsidianBoat.MOD_ID, "obsidian_boat"));
    }
}
