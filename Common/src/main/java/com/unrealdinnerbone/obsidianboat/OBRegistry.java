package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.advancements.BoatTrigger;
import com.unrealdinnerbone.obsidianboat.api.OBServices;
import com.unrealdinnerbone.obsidianboat.item.ObsidianBoatItem;
import com.unrealdinnerbone.trenzalore.api.platform.services.IRegistry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryEntry;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryFactory;
import com.unrealdinnerbone.trenzalore.api.registry.RegistryObjects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;

public class OBRegistry implements IRegistry {

    private static final RegistryObjects<Item> ITEMS = RegistryFactory.create(Registries.ITEM);
    private static final RegistryObjects<EntityType<?>> ENTITY_TYPES = RegistryFactory.create(Registries.ENTITY_TYPE);

    public static final RegistryEntry<ObsidianBoatItem> BOAT_ITEM = ITEMS.register("obsidian_boat", ObsidianBoatItem::new);


    public static BoatTrigger BOAT_TRIGGER = RegistryFactory.registerCriterion(new BoatTrigger());

    public static final RegistryEntry<EntityType<? extends Boat>> ENTITY_TYPE = ENTITY_TYPES.register("obsidian_boat", OBServices.COMPACT.getFactorySupplier());



    @Override
    public void afterRegistered() {
        //Todo move this to trenzalore
        BuiltInRegistries.CREATIVE_MODE_TAB.registryKeySet().stream()
                .filter(tabResourceKey -> tabResourceKey.location().equals(new ResourceLocation("tools_and_utilities")))
                .findFirst()
                .ifPresent(tabResourceKey -> RegistryFactory.registerCreativeTabItems(tabResourceKey, List.of(BOAT_ITEM)));
    }

    @Override
    public List<RegistryObjects<?>> getRegistryObjects() {
        return List.of(ITEMS, ENTITY_TYPES);
    }

    @Override
    public String getModID() {
        return ObsidianBoat.MOD_ID;
    }
}
