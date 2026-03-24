package com.unrealdinnerbone.obsidianboat.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@NullMarked
public class BoatTrigger extends SimpleCriterionTrigger<BoatTrigger.Instance> {

   public static final Identifier ID = ObsidianBoat.id(ObsidianBoat.MOD_ID);

   public void trigger(ServerPlayer serverPlayerEntity) {
      this.trigger(serverPlayerEntity, (value) -> true);
   }

   @Override
   public Codec<Instance> codec() {
      return Instance.CODEC;
   }

   public static record Instance(Optional<ContextAwarePredicate> player) implements SimpleCriterionTrigger.SimpleInstance {

      public static final Codec<Instance> CODEC = RecordCodecBuilder.create((instance) ->
              instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player))
                      .apply(instance, Instance::new));

   }
}
