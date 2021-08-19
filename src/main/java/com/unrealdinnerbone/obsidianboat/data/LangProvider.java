package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangProvider extends LanguageProvider {

    public LangProvider(DataGenerator generator) {
        super(generator, OB.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(OB.ITEM.get(), "Obsidian Boat");
        add(OB.ENTITY_TYPE.get(), "Obsidian Boat");
        add("advancements.obsidianboat.root.title", "Did you really think that would work?");
        add("advancements.obsidianboat.root.description", "Place an obsidian boat on water");
    }
}
