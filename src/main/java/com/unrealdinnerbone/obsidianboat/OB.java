package com.unrealdinnerbone.obsidianboat;

import com.unrealdinnerbone.obsidianboat.data.BoatTrigger;
import com.unrealdinnerbone.obsidianboat.data.DataEvent;
import com.unrealdinnerbone.obsidianboat.entity.ObsidianBoatEntity;
import com.unrealdinnerbone.obsidianboat.item.ObsidianBoatItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(OB.MOD_ID)
public class OB {
    public static final String MOD_ID = "obsidianboat";

    private static final Logger LOGGER = LogManager.getLogger();


    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<EntityType<ObsidianBoatEntity>> ENTITY_TYPE = ENTITY_TYPES.register(MOD_ID, () ->
            EntityType.Builder.<ObsidianBoatEntity>of(ObsidianBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .setCustomClientFactory((spawnEntity, world) -> new ObsidianBoatEntity(world))
                            .clientTrackingRange(10)
                    .fireImmune()
                    .build(MOD_ID + ":" + MOD_ID));

    public static final RegistryObject<ObsidianBoatItem> ITEM = ITEMS.register(MOD_ID, () -> new ObsidianBoatItem((new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_TRANSPORTATION)));

    public OB() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(DataEvent::onData);
        CriteriaTriggers.register(BoatTrigger.INSTANCE);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(OBClient::doClientStuff));
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(OBClient::doClientStuffTwo));
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
