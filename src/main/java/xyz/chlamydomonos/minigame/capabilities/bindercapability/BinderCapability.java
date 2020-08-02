package xyz.chlamydomonos.minigame.capabilities.bindercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class BinderCapability implements IBinderCapability
{
    private BlockPos pos1;
    private BlockPos pos2;
    private BinderType type;

    public BinderCapability()
    {
        this.pos1 = new BlockPos(0, 0, 0);
        this.pos2 = new BlockPos(0, 0, 0);
    }

    @Override
    public void setType(BinderType type)
    {
        this.type = type;
    }

    @Override
    public BlockPos getPos1()
    {
        return this.pos1;
    }

    @Override
    public void setPos1(BlockPos pos1)
    {
        this.pos1 = pos1;
    }

    @Override
    public BlockPos getPos2()
    {
        return this.pos2;
    }

    @Override
    public void setPos2(BlockPos pos2)
    {
        this.pos2 = pos2;
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        CompoundNBT binderNBT = new CompoundNBT();
        binderNBT.putInt("x1", pos1.getX());
        binderNBT.putInt("x2", pos2.getX());
        binderNBT.putInt("y1", pos1.getY());
        binderNBT.putInt("y2", pos2.getY());
        binderNBT.putInt("z1", pos1.getZ());
        binderNBT.putInt("z2", pos2.getZ());
        return binderNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        int x1 = nbt.getInt("x1");
        int x2 = nbt.getInt("x2");
        int y1 = nbt.getInt("y1");
        int y2 = nbt.getInt("y2");
        int z1 = nbt.getInt("z1");
        int z2 = nbt.getInt("z2");
        this.pos1 = new BlockPos(x1, y1, z1);
        this.pos2 = new BlockPos(x2, y2, z2);
    }
}
