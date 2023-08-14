package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.entity.ObsidianBoatEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Arrays;
import java.util.List;

public class BoatUtil {

    private static final List<Boat.Status> DAMAGE = Arrays.asList(Boat.Status.UNDER_WATER, Boat.Status.UNDER_FLOWING_WATER);

    public static boolean isImmune(Entity entity) {
        if (entity.isPassenger()) {
            if (entity.getVehicle() instanceof ObsidianBoatEntity boat) {
                return !DAMAGE.contains(boat.getStatus());
            }
        }
        return false;
    }
}
