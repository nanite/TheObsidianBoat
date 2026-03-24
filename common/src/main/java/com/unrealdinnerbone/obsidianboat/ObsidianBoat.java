package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.trenzalore.lib.IDUtils;
import net.minecraft.resources.Identifier;

public class ObsidianBoat {
    public static final String MOD_ID = "obsidianboat";

    public static Identifier id(String path) {
        return IDUtils.id(MOD_ID, path);
    }

}