package com.unrealdinnerbone.obsidianboat.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class BoatTrigger extends SimpleCriterionTrigger<BoatTrigger.Instance> {

   public static final ResourceLocation ID = ObsidianBoat.rl(ObsidianBoat.MOD_ID);

   public void trigger(ServerPlayer serverPlayerEntity) {
      this.trigger(serverPlayerEntity, (value) -> true);
   }

   @Override
   @NotNull
   public Codec<Instance> codec() {
      return Instance.CODEC;
   }

   public static record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

      public static final Codec<Instance> CODEC = RecordCodecBuilder.create((instance) ->
              instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player))
                      .apply(instance, Instance::new));

   }
}
