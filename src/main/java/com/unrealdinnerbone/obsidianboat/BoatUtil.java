package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.entity.ObsidianBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;

import java.util.Arrays;
import java.util.List;

public class BoatUtil {

    private static final List<BoatEntity.Status> DAMAGE = Arrays.asList(BoatEntity.Status.UNDER_WATER, BoatEntity.Status.UNDER_FLOWING_WATER);

    public static boolean isImmune(Entity entity) {
        return entity.isPassenger() && entity.getVehicle() instanceof ObsidianBoatEntity && !DAMAGE.contains(((ObsidianBoatEntity) entity.getVehicle()).getStatus());
    }
}
