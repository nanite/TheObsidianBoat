package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.trenzalore.events.ReplaceItemModifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(ObsidianBoat.MOD_ID)
public class ObsidianBoatForge {
    
    public ObsidianBoatForge() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> OBClientForge.init());
    }

}