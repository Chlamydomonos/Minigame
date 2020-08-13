package xyz.chlamydomonos.minigame.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;

public class ItemOver extends Item
{
    private String name = "i_am_over";

    public ItemOver() {
        super(new Properties().group(ItemGroupLoader.MINIGAME));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        context.getPlayer().attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
        return ActionResultType.SUCCESS;
    }
}
