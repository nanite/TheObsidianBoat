package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.api.OBServices;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Arrays;
import java.util.List;

public class BoatUtil {

    private static final List<Boat.Status> DAMAGE = Arrays.asList(Boat.Status.UNDER_WATER, Boat.Status.UNDER_FLOWING_WATER);

    public static boolean isImmune(Entity entity) {
        if (entity.isPassenger()) {
            if (entity.getVehicle() instanceof Boat boat) {
                Boat.Status status = OBServices.COMPACT.getBoatStatus(boat);
                return !DAMAGE.contains(status);
            }
        }
        return false;
    }
}
