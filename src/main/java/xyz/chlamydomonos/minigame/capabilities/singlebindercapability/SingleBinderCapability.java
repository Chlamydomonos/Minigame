package xyz.chlamydomonos.minigame.capabilities.singlebindercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class SingleBinderCapability implements ISingleBinderCapability
{
    private BlockPos pos;

    public SingleBinderCapability()
    {
        this.pos = new BlockPos(0, 0, 0);
    }

    @Override
    public BlockPos getPos()
    {
        return this.pos;
    }

    @Override
    public void setPos(BlockPos pos)
    {
        this.pos = pos;
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        CompoundNBT binderNBT = new CompoundNBT();
        binderNBT.putInt("x", pos.getX());
        binderNBT.putInt("y", pos.getY());
        binderNBT.putInt("z", pos.getZ());
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        int x = nbt.getInt("x");
        int y = nbt.getInt("y");
        int z = nbt.getInt("z");
        this.pos = new BlockPos(x, y, z);
    }
}
