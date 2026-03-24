package com.unrealdinnerbone.obsidianboat.fabric.client.data;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import com.unrealdinnerbone.obsidianboat.advancements.BoatTrigger;
import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {

    public AdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {

            consumer.accept(Advancement.Builder.advancement()
                    .parent(IDUtils.id("minecraft", "story/root"))
                    .display(OBRegistry.BOAT_ITEM.get(), getTranslation("title"), getTranslation("description"), null, AdvancementType.TASK, true, true, true)
                    .addCriterion("placed_boat", OBRegistry.BOAT_TRIGGER.entryValue().createCriterion(new BoatTrigger.Instance(Optional.empty())))
                    .build(ObsidianBoat.id( ObsidianBoat.MOD_ID)));

    }

    private static Component getTranslation(String key) {
        return Component.translatable("advancements." + ObsidianBoat.MOD_ID + ".root." + key);
    }
}
