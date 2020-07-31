package xyz.chlamydomonos.minigame.block.itemblocks;

import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import xyz.chlamydomonos.minigame.core.loaders.BlockLoader;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;
import xyz.chlamydomonos.minigame.core.Minigame;

public class ItemBlockGameController extends BlockItem
{
    private String name = "game_controller";

    public ItemBlockGameController()
    {
        super(BlockLoader.GAME_CONTROLLER, new Properties().group(ItemGroupLoader.MINIGAME));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }
}
