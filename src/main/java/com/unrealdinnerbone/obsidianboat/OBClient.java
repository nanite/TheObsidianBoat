package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.client.ObsidianBoatRender;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class OBClient
{
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(OB.ENTITY_TYPE.get(), ObsidianBoatRender::new);
    }
}
