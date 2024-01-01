package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
import net.minecraft.client.model.BoatModel;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class OBClientForge
{
    public static void init(IEventBus eventBus) {
        eventBus.addListener(OBClientForge::doClientStuff);
        eventBus.addListener(OBClientForge::doClientStuffTwo);
    }
    public static void doClientStuff(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OBRegistry.ENTITY_TYPE.get(), ObsidianBoatRender::new);
    }

    public static void doClientStuffTwo(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ObsidianBoatRender.LOCATION, () -> BoatModel.createBodyModel());
    }
}
