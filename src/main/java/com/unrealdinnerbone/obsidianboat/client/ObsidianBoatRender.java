package com.unrealdinnerbone.obsidianboat.client;

import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;

public class ObsidianBoatRender extends BoatRenderer {

    private final static ResourceLocation LOCATION = new ResourceLocation(OB.MOD_ID, "textures/entity/boat.png");

    public ObsidianBoatRender(EntityRendererManager dispatcher) {
        super(dispatcher);
    }

    @Override
    public ResourceLocation getTextureLocation(BoatEntity boat) {
        return LOCATION;
    }
}
