package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
import net.minecraft.client.model.BoatModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class OBClient
{
    public static void doClientStuff(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OB.ENTITY_TYPE.get(), ObsidianBoatRender::new);
    }

    public static void doClientStuffTwo(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ObsidianBoatRender.LOCATION, BoatModel::createBodyModel);
    }
}
