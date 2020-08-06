package xyz.chlamydomonos.minigame.itemgroups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import xyz.chlamydomonos.minigame.core.loaders.ItemBlockLoader;
import xyz.chlamydomonos.minigame.core.loaders.ItemLoader;

public class ItemGroupMinigameMisc extends ItemGroup
{
    public ItemGroupMinigameMisc()
    {
        super("minigame_misc_group");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ItemLoader.SUONDEE_METAL_INGOT);
    }
}
