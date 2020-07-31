package xyz.chlamydomonos.minigame.items;

import net.minecraft.item.Item;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;

public class ItemMapBinder extends Item
{
    public ItemMapBinder()
    {
        super(new Properties().group(ItemGroupLoader.MINIGAME));
    }
}
