package xyz.chlamydomonos.minigame.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import xyz.chlamydomonos.minigame.ItemBlockLoader;

public class ItemGroupMinigame extends ItemGroup
{
    public ItemGroupMinigame()
    {
        super("minigame_group");
    }

    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ItemBlockLoader.GAME_CONTROLLER.getItem());
    }
}
