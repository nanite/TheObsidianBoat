package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

public class OBClient
{
    public static void doClientStuff(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OB.ENTITY_TYPE.get(), ObsidianBoatRender::new);
    }

    public static void doClientStuffTwo(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ObsidianBoatRender.LOCATION, () -> BoatModel.createBodyModel(false));
    }
}
