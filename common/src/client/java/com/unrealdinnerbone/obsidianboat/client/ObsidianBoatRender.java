package com.unrealdinnerbone.obsidianboat.client;

import com.unrealdinnerbone.obsidianboat.ObsidianBoat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ObsidianBoatRender extends BoatRenderer {

    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(ObsidianBoat.id( "obsidian_boat"), "main");


//    private final ResourceLocation texture;
    public ObsidianBoatRender(EntityRendererProvider.Context dispatcher) {
        super(dispatcher, LOCATION);
//        this.texture = ObsidianBoat.id("textures/entity/obsidian_boat.png");
//        boatResources = Map.of(Boat.Type.OAK, Pair.of(texture, new BoatModel(dispatcher.bakeLayer(LOCATION))));
    }

//    @Override
//    @NotNull
//    public ResourceLocation getTextureLocation(Boat boat) {
//        return texture;
//    }
}
