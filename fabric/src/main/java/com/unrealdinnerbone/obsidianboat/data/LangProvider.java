package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {

    protected LangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(OBRegistry.BOAT_ITEM.get(), "Obsidian Boat");
        translationBuilder.add(OBRegistry.ENTITY_TYPE.get(), "Obsidian Boat");
        translationBuilder.add("advancements.obsidianboat.root.description", "Did you really think that would work?");
        translationBuilder.add("advancements.obsidianboat.root.title", "What ever sinks your boat");
    }
}
