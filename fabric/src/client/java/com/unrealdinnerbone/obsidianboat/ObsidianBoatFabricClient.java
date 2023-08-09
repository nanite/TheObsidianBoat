package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.BoatModel;

public class ObsidianBoatFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(OBRegistry.ENTITY_TYPE.get(), ObsidianBoatRender::new);
        EntityModelLayerRegistry.registerModelLayer(ObsidianBoatRender.LOCATION, BoatModel::createBodyModel);
    }
}
