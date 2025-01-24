package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import com.unrealdinnerbone.obsidianboat.advancements.BoatTrigger;
import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.ChangeDimensionTrigger;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {

    public AdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {

            consumer.accept(Advancement.Builder.advancement()
                    .parent(RLUtils.rl("minecraft", "story/root"))
                    .display(OBRegistry.BOAT_ITEM.get(), getTranslation("title"), getTranslation("description"), null, AdvancementType.TASK, true, true, true)
                    .addCriterion("placed_boat", OBRegistry.BOAT_TRIGGER.get().createCriterion(new BoatTrigger.Instance(Optional.empty())))
                    .build(ObsidianBoat.rl( ObsidianBoat.MOD_ID)));

    }

    private static Component getTranslation(String key) {
        return Component.translatable("advancements." + ObsidianBoat.MOD_ID + ".root." + key);
    }
}
