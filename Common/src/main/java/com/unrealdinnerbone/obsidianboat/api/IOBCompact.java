package com.unrealdinnerbone.obsidianboat.api;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public interface IOBCompact {

    Supplier<EntityType<? extends Boat>> getFactorySupplier();
    Boat createBoatEntity(Level level, Vec3 location);

//    Boat createForClient(Level level);

    EntityType.EntityFactory<Boat> getFactory();

    Boat.Status getBoatStatus(Boat boat);

}
