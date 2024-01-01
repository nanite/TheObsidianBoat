package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.advancements.BoatTrigger;
import com.unrealdinnerbone.obsidianboat.entity.ObsidianBoatEntity;
import com.unrealdinnerbone.obsidianboat.item.ObsidianBoatItem;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import com.unrealdinnerbone.trenzalore.lib.CreativeTabs;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;

import java.util.List;

public class OBRegistry implements IRegistry {

    private static final RegistryObjects<Item> ITEMS = Regeneration.create(Registries.ITEM);
    private static final RegistryObjects<EntityType<?>> ENTITY_TYPES = Regeneration.create(Registries.ENTITY_TYPE);

    private static final RegistryObjects<CriterionTrigger<?>> TRIGGERS = Regeneration.create(Registries.TRIGGER_TYPE);

    public static final RegistryEntry<ObsidianBoatItem> BOAT_ITEM = ITEMS.register("obsidian_boat", ObsidianBoatItem::new);

    public static final RegistryEntry<BoatTrigger> BOAT_TRIGGER = TRIGGERS.register("boat_in_water", BoatTrigger::new);

    public static final RegistryEntry<EntityType<ObsidianBoatEntity>> ENTITY_TYPE = ENTITY_TYPES.register("obsidian_boat", () ->
            EntityType.Builder.of((EntityType.EntityFactory<ObsidianBoatEntity>) ObsidianBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10)
                    .fireImmune()
                    .build(ObsidianBoat.MOD_ID + ":" + ObsidianBoat.MOD_ID));



    @Override
    public void afterRegistered() {
        Regeneration.addItemToCreateTab(CreativeTabs.TOOLS_AND_UTILITIES, BOAT_ITEM);
    }

    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of(ITEMS, ENTITY_TYPES, TRIGGERS);
    }

    @Override
    public String getModID() {
        return ObsidianBoat.MOD_ID;
    }
}
