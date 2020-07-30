package xyz.chlamydomonos.minigame.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import xyz.chlamydomonos.minigame.Minigame;

public class BlockPlayerTypeSetter extends Block
{
    private final String name = "player_type_setter";

    public BlockPlayerTypeSetter(Properties properties)
    {
        super(Properties.create(Material.ROCK).hardnessAndResistance(999999));
        this.setRegistryName(Minigame.MODID, this.name);
    }
}
