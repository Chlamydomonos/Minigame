package xyz.chlamydomonos.minigame.capabilities.singlebindercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISingleBinderCapability extends INBTSerializable<CompoundNBT>
{
    BlockPos getPos();
    void setPos(BlockPos pos);
    void setType(SingleBinderType type);
    SingleBinderType getType();
}
