package xyz.chlamydomonos.minigame.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import xyz.chlamydomonos.minigame.core.Minigame;

public class BlockGameController extends Block
{
    public String name = "game_controller";

    public BlockGameController()
    {
        super(Properties.create(Material.ROCK).hardnessAndResistance(-1, 6000000));//Same as bedrock
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }
}
