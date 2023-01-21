package com.unrealdinnerbone.obsidianboat.data;

import com.unrealdinnerbone.obsidianboat.OB;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemDataProvider extends ItemModelProvider {

    public ItemDataProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OB.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        itemGenerated(OB.ITEM.get(), new ResourceLocation(OB.MOD_ID, "item/" + OB.MOD_ID));
    }

    public void itemGenerated(Item item, ResourceLocation texture) {
        ResourceLocation resourcelocation = ForgeRegistries.ITEMS.getKey(item);
        getBuilder(resourcelocation.getPath()).parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", texture);
    }
}
