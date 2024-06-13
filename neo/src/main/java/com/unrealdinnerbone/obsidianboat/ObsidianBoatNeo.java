package com.unrealdinnerbone.obsidianboat;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ObsidianBoat.MOD_ID)
public class ObsidianBoatNeo {
    
    public ObsidianBoatNeo(IEventBus eventBus, Dist dist) {
        if(dist == Dist.CLIENT) {
            OBClientNeo.init(eventBus);
        }
    }

}