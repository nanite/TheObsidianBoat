package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
import com.unrealdinnerbone.trenzalore.api.registry.Regeneration;
import net.minecraft.client.model.BoatModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class OBClientForge
{
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(OBClientForge::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(OBClientForge::doClientStuffTwo);
    }
    public static void doClientStuff(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OBRegistry.ENTITY_TYPE.get(), ObsidianBoatRender::new);
    }

    public static void doClientStuffTwo(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ObsidianBoatRender.LOCATION, () -> BoatModel.createBodyModel());
    }
}
