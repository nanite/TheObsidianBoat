package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.trenzalore.lib.RLUtils;
import net.minecraft.resources.ResourceLocation;

public class ObsidianBoat {
    public static final String MOD_ID = "obsidianboat";


    public static ResourceLocation rl(String path) {
        return RLUtils.rl(MOD_ID, path);
    }

}