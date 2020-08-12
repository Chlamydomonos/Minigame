package xyz.chlamydomonos.minigame.items.suondee;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;

public class ItemSuondeeMetalIngot extends Item
{
    private String name = "suondee_metal_ingot";

    public ItemSuondeeMetalIngot()
    {
        super(new Properties().group(ItemGroupLoader.MINIGAME_MISC));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }
}
