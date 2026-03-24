package com.unrealdinnerbone.obsidianboat.fabric.client;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class ObsidianBoatFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(OBRegistry.ENTITY_TYPE.entryValue(), ObsidianBoatRender::new);
        ModelLayerRegistry.registerModelLayer(ObsidianBoatRender.LOCATION, BoatModel::createBoatModel);
//        EntityRendererRegistry.register(OBRegistry.ENTITY_TYPE.get(), ObsidianBoatRender::new);
//        EntityModelLayerRegistry.registerModelLayer(ObsidianBoatRender.LOCATION, BoatModel::createBodyModel);
    }
}
