package xyz.chlamydomonos.minigame.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;

public class ItemPassiveSuondeeSword extends Item
{
    private String name = "passive_suondee_sword";

    public ItemPassiveSuondeeSword() {
        super(new Properties().group(ItemGroupLoader.MINIGAME_MISC));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }
}
