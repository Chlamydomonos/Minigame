package xyz.chlamydomonos.minigame.itemgroups;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import xyz.chlamydomonos.minigame.core.loaders.ItemBlockLoader;

public class ItemGroupMinigame extends ItemGroup
{
    public ItemGroupMinigame()
    {
        super("minigame_group");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ItemBlockLoader.GAME_CONTROLLER);
    }
}
