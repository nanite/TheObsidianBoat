package com.unrealdinnerbone.obsidianboat.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.function.Consumer;

public class DataEvent {

    public static void onData(GatherDataEvent event) {
        event.getGenerator().addProvider(new RecipeDataProvider(event.getGenerator()));
        event.getGenerator().addProvider(new AdvancementDataProvider(event.getGenerator()));
        event.getGenerator().addProvider(new ItemDataProvider(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(new LangProvider(event.getGenerator()));
        event.getGenerator().addProvider(new PackMcMeta(event.getGenerator()));
    }

    public static class PackMcMeta implements IDataProvider {

        private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        private final DataGenerator dataGenerator;

        public PackMcMeta(DataGenerator dataGenerator) {
            this.dataGenerator = dataGenerator;
        }


        @Override
        public void run(DirectoryCache directoryCache) throws IOException {
            IDataProvider.save(GSON, directoryCache, JSONUtils.parse(GSON.toJson(new Meta(OB.MOD_ID, 6))),dataGenerator.getOutputFolder().resolve("pack.mcmeta"));
        }

        @Override
        public String getName() {
            return "PackMeta";
        }


        public static class Meta {

            private Data pack;

            public Meta(String modId, int format) {
                this.pack = new Data("resources for " + modId, format);
            }

            public static class Data {
                private String description;
                private int pack_format;

                public Data(String description, int format) {
                    this.description = description;
                    this.pack_format = format;
                }
            }
        }
    }

    public static class AdvancementDataProvider extends AdvancementProvider {

        public AdvancementDataProvider(DataGenerator generator) {
            super(generator);
        }

        @Override
        public void run(DirectoryCache cache) throws IOException {
            tabs = Collections.singletonList(new AdvancementData());
            super.run(cache);
        }
    }

    public static class AdvancementData implements Consumer<Consumer<Advancement>> {

        @Override
        public void accept(Consumer<Advancement> consumer) {

            ResourceLocation background = new ResourceLocation("minecraft", "textures/gui/advancements/backgrounds/stone.png");

            Advancement advancement = Advancement.Builder.advancement()
                    .display(OB.ITEM.get(), getTranslation("title"), getTranslation("description"), background, FrameType.TASK, true, true, true)
                    .addCriterion("placed_boat", new BoatTrigger.Instance(EntityPredicate.AndPredicate.ANY))
                    .save(consumer, OB.MOD_ID + ":" + OB.MOD_ID);

        }
    }

    private static TextComponent getTranslation(String key) {
        return new TranslationTextComponent("advancements." + OB.MOD_ID + ".root." + key);
    }
}
