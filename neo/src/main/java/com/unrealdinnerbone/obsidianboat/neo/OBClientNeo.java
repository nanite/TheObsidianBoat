package com.unrealdinnerbone.obsidianboat.neo;

import com.unrealdinnerbone.obsidianboat.OBRegistry;
import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
//import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.object.boat.BoatModel;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class OBClientNeo
{
    public static void init(IEventBus eventBus) {
        eventBus.addListener(OBClientNeo::doClientStuff);
        eventBus.addListener(OBClientNeo::doClientStuffTwo);
    }
    public static void doClientStuff(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OBRegistry.ENTITY_TYPE.entryValue(), ObsidianBoatRender::new);
    }

    public static void doClientStuffTwo(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ObsidianBoatRender.LOCATION, () -> BoatModel.createBoatModel());
    }
}
