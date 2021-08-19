package com.unrealdinnerbone.obsidianboat.data;

import com.google.gson.JsonObject;
import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class BoatTrigger extends AbstractCriterionTrigger<BoatTrigger.Instance> {

   public static BoatTrigger INSTANCE = new BoatTrigger();

   public static final ResourceLocation ID = new ResourceLocation(OB.MOD_ID, OB.MOD_ID);

   public ResourceLocation getId() {
      return ID;
   }

   public BoatTrigger.Instance createInstance(JsonObject jsonObject, EntityPredicate.AndPredicate andPredicate, ConditionArrayParser conditionArrayParser) {
      return new BoatTrigger.Instance(andPredicate);
   }

   public void trigger(ServerPlayerEntity serverPlayerEntity) {
      this.trigger(serverPlayerEntity, (value) -> true);
   }

   public static class Instance extends CriterionInstance {
      public Instance(EntityPredicate.AndPredicate andPredicate) {
         super(BoatTrigger.ID, andPredicate);
      }
   }
}
