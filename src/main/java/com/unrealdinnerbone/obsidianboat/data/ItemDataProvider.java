package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemDataProvider extends ItemModelProvider {

    public ItemDataProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, OB.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        itemGenerated(OB.ITEM.get(), new ResourceLocation(OB.MOD_ID, "item/" + OB.MOD_ID));
    }

    public void itemGenerated(Item item, ResourceLocation texture) {
        getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", texture);
    }
}
