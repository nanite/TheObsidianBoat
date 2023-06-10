//package com.unrealdinnerbone.obsidianboat.data;
//
//import com.unrealdinnerbone.obsidianboat.OB;
//import net.minecraft.advancements.Advancement;
//import net.minecraft.advancements.FrameType;
//import net.minecraft.advancements.critereon.EntityPredicate;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.data.PackOutput;
//import net.minecraft.data.advancements.AdvancementProvider;
//import net.minecraft.data.advancements.AdvancementSubProvider;
//import net.minecraft.data.metadata.PackMetadataGenerator;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.data.event.GatherDataEvent;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.function.Consumer;
//
//
//public class DataEvent {
//
//    public static void onData(GatherDataEvent event) {
//        event.getGenerator().addProvider(true, new RecipeDataProvider(event.getGenerator().getPackOutput()));
//        event.getGenerator().addProvider(true, new AdvancementDataProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
//        event.getGenerator().addProvider(true, new ItemDataProvider(event.getGenerator().getPackOutput(), event.getExistingFileHelper()));
//        event.getGenerator().addProvider(true, new LangProvider(event.getGenerator().getPackOutput()));
//        event.getGenerator().addProvider(true, PackMetadataGenerator.forFeaturePack(event.getGenerator().getPackOutput(), Component.literal("Obsidian Boat")));
//    }
//
//
//    public static class AdvancementDataProvider extends AdvancementProvider {
//
//        public AdvancementDataProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
//            super(packOutput, provider, List.of(new AdvancementData()));
//        }
//    }
//
//    public static class AdvancementData implements AdvancementSubProvider {
//        @Override
//        public void generate(HolderLookup.Provider p_255901_, Consumer<Advancement> consumer) {
//            ResourceLocation background = new ResourceLocation("minecraft", "textures/gui/advancements/backgrounds/stone.png");
//
//            Advancement advancement = Advancement.Builder.advancement()
//                    .display(OB.ITEM.get(), getTranslation("title"), getTranslation("description"), background, FrameType.TASK, true, true, true)
//                    .addCriterion("placed_boat", new BoatTrigger.Instance(EntityPredicate.Composite.ANY))
//                    .save(consumer, OB.MOD_ID + ":" + OB.MOD_ID);
//        }
//    }
//
//    private static Component getTranslation(String key) {
//        return Component.translatable("advancements." + OB.MOD_ID + ".root." + key);
//    }
//}
