package com.unrealdinnerbone.obsidianboat.advancements;

import com.google.gson.JsonObject;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class BoatTrigger extends SimpleCriterionTrigger<BoatTrigger.Instance> {

   public static final ResourceLocation ID = new ResourceLocation(ObsidianBoat.MOD_ID, ObsidianBoat.MOD_ID);

   public ResourceLocation getId() {
      return ID;
   }

   public void trigger(ServerPlayer serverPlayerEntity) {
      this.trigger(serverPlayerEntity, (value) -> true);
   }

   @Override
   protected Instance createInstance(JsonObject jsonObject, ContextAwarePredicate composite, DeserializationContext context) {
      return new Instance(composite);
   }

   public static class Instance extends AbstractCriterionTriggerInstance {
      public Instance(ContextAwarePredicate andPredicate) {
         super(BoatTrigger.ID, andPredicate);
      }
   }
}
