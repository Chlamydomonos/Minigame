package xyz.chlamydomonos.minigame.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.chlamydomonos.minigame.block.tileentities.TileEntityGameController;
import xyz.chlamydomonos.minigame.core.Minigame;

import javax.annotation.Nullable;

public class BlockGameController extends Block
{
    public String name = "game_controller";

    public BlockGameController()
    {
        super(Properties.create(Material.ROCK).hardnessAndResistance(-1, 6000000));//Same as bedrock
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TileEntityGameController();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if(!worldIn.isRemote && handIn == Hand.MAIN_HAND)
        {
            TileEntityGameController tile = (TileEntityGameController) worldIn.getTileEntity(pos);
            tile.start();
            NetworkHooks.openGui((ServerPlayerEntity) player, tile, (PacketBuffer packerBuffer) -> packerBuffer.writeBlockPos(tile.getPos()));
        }
        return ActionResultType.SUCCESS;
    }
}
