package xyz.chlamydomonos.minigame.block.tileentities;

import net.minecraft.tileentity.TileEntity;
import xyz.chlamydomonos.minigame.core.loaders.TileEntityLoader;

public class TileEntityGameController extends TileEntity
{
    public String test = "Suondeea";

    public TileEntityGameController()
    {
        super(TileEntityLoader.GAME_CONTROLLER);
    }
}
