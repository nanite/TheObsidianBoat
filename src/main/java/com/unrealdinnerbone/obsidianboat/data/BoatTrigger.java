package com.unrealdinnerbone.obsidianboat.data;

import com.google.gson.JsonObject;
import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class BoatTrigger extends SimpleCriterionTrigger<BoatTrigger.Instance> {

   public static BoatTrigger INSTANCE = new BoatTrigger();

   public static final ResourceLocation ID = new ResourceLocation(OB.MOD_ID, OB.MOD_ID);

   public ResourceLocation getId() {
      return ID;
   }

   public void trigger(ServerPlayer serverPlayerEntity) {
      this.trigger(serverPlayerEntity, (value) -> true);
   }

   @Override
   protected Instance createInstance(JsonObject jsonObject, EntityPredicate.Composite composite, DeserializationContext context) {
      return new BoatTrigger.Instance(composite);
   }

   public static class Instance extends AbstractCriterionTriggerInstance {
      public Instance(EntityPredicate.Composite andPredicate) {
         super(BoatTrigger.ID, andPredicate);
      }
   }
}
