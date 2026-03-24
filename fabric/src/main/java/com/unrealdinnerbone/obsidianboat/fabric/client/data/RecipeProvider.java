package com.unrealdinnerbone.obsidianboat.fabric.client.data;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {

    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new net.minecraft.data.recipes.RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                ShapedRecipeBuilder.shaped(registries.lookupOrThrow(Registries.ITEM), RecipeCategory.TRANSPORTATION, OBRegistry.BOAT_ITEM.get(), 1)
                        .pattern("O O")
                        .pattern("OOO")
                .unlockedBy("has_obsidian", has(Blocks.OBSIDIAN))
                        .define('O', Blocks.OBSIDIAN).save(output, ResourceKey.create(Registries.RECIPE, ObsidianBoat.id("obsidian_boat")));
            }
        };

    }

    @Override
    public String getName() {
        return "ObsidianBoat Recipes";
    }

//    @Override
//    public void buildRecipes(RecipeOutput exporter) {

//    }
}
