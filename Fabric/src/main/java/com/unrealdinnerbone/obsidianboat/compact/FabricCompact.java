package com.unrealdinnerbone.obsidianboat.compact;

import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import com.unrealdinnerbone.obsidianboat.api.IOBCompact;
import com.unrealdinnerbone.obsidianboat.api.OBServices;
import com.unrealdinnerbone.obsidianboat.entity.ObsidianBoatEntity;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class FabricCompact implements IOBCompact {
    @Override
    public Supplier<EntityType<? extends Boat>> getFactorySupplier() {
        return () -> FabricEntityType.Builder.of(OBServices.COMPACT.getFactory(), MobCategory.MISC)
                .sized(1.375F, 0.5625F)
                .clientTrackingRange(10)
                .fireImmune().build(ObsidianBoat.MOD_ID + ":" + ObsidianBoat.MOD_ID);
    }
    @Override
    public Boat createBoatEntity(Level level, Vec3 location) {
        return new ObsidianBoatEntity(level, location.x, location.y, location.z);
    }

    @Override
    public EntityType.EntityFactory<Boat> getFactory() {
        return ObsidianBoatEntity::new;
    }

    @Override
    public Boat.Status getBoatStatus(Boat boat) {
        return boat.getStatus();
    }
}
