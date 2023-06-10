package com.unrealdinnerbone.obsidianboat.client;

import com.mojang.datafixers.util.Pair;
import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

public class ObsidianBoatRender extends BoatRenderer {

    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(ObsidianBoat.MOD_ID, "obsidian_boat"), "main");

    private final Pair<ResourceLocation, ListModel<Boat>> texture;
    public ObsidianBoatRender(EntityRendererProvider.Context dispatcher) {
        super(dispatcher, false);
        this.texture =  Pair.of(new ResourceLocation(ObsidianBoat.MOD_ID, "textures/entity/obsidian_boat.png"), new BoatModel(dispatcher.bakeLayer(LOCATION)));

    }
    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        return texture;
    }
}
