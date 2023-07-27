package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;

public class OBFabricData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AdvancementProvider::new);
        pack.addProvider((output, registriesFuture) -> PackMetadataGenerator.forFeaturePack(output, Component.literal(ObsidianBoat.MOD_ID)));
        pack.addProvider(ModelProvider::new);
        pack.addProvider(LangProvider::new);
        pack.addProvider(RecipeProvider::new);
    }
}
