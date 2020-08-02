package xyz.chlamydomonos.minigame.capabilities.bindercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

public interface IBinderCapability extends INBTSerializable<CompoundNBT>
{
    BlockPos getPos1();
    void setPos1(BlockPos pos1);
    BlockPos getPos2();
    void setPos2(BlockPos pos2);
    void setType(BinderType type);
}
