package com.unrealdinnerbone.obsidianboat.client;

import com.mojang.datafixers.util.Pair;
import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.List;

public class ObsidianBoatRender extends BoatRenderer {

    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(OB.MOD_ID, "obsidianboat"), "main");
    private final Pair<ResourceLocation, ListModel<Boat>> model;

    public ObsidianBoatRender(EntityRendererProvider.Context dispatcher) {
        super(dispatcher, false);
        model = Pair.of(new ResourceLocation(OB.MOD_ID, "textures/entity/boat.png"), new BoatModel(dispatcher.bakeLayer(LOCATION)));;
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        return model;
    }
}
