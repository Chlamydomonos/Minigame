package xyz.chlamydomonos.minigame.capabilities.singlebindercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SingleBinderCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT>
{
    private ISingleBinderCapability singleBinderCapability;
    private SingleBinderType type;

    public SingleBinderCapabilityProvider(SingleBinderType type)
    {
        this.type = type;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return cap == SingleBinderCapabilityLoader.SINGLE_BINDER_CAPABILITY ? LazyOptional.of(
                () -> this.getOrCreateCapability()).cast() : LazyOptional.empty();
    }

    @Nonnull
    ISingleBinderCapability getOrCreateCapability()
    {
        if (this.singleBinderCapability == null)
        {
            this.singleBinderCapability = new SingleBinderCapability();
            this.singleBinderCapability.setType(this.type);
        }
        return this.singleBinderCapability;
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        return this.getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        this.getOrCreateCapability().deserializeNBT(nbt);
    }
}
