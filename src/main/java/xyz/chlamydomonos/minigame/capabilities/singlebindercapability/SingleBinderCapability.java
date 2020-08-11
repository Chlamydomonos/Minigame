package xyz.chlamydomonos.minigame.capabilities.singlebindercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderType;

public class SingleBinderCapability implements ISingleBinderCapability
{
    private BlockPos pos;
    private SingleBinderType type;

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
    public void setType(SingleBinderType type)
    {
        this.type = type;
    }

    @Override
    public SingleBinderType getType()
    {
        return type;
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        CompoundNBT binderNBT = new CompoundNBT();
        binderNBT.putInt("x", pos.getX());
        binderNBT.putInt("y", pos.getY());
        binderNBT.putInt("z", pos.getZ());
        return binderNBT;
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
