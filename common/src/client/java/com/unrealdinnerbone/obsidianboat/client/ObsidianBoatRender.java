package com.unrealdinnerbone.obsidianboat.client;

import com.mojang.datafixers.util.Pair;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ObsidianBoatRender extends BoatRenderer {

    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(ObsidianBoat.MOD_ID, "obsidian_boat"), "main");


    private final ResourceLocation texture;
    public ObsidianBoatRender(EntityRendererProvider.Context dispatcher) {
        super(dispatcher, false);
        this.texture = new ResourceLocation(ObsidianBoat.MOD_ID, "textures/entity/obsidian_boat.png");
        boatResources = Map.of(Boat.Type.OAK, Pair.of(texture, new BoatModel(dispatcher.bakeLayer(LOCATION))));
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(Boat boat) {
        return texture;
    }
}
