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

    public SingleBinderCapabilityProvider(SingleBinderType type)
    {
        this.getOrCreateCapability().setType(type);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return cap == SingleBinderCapabilityLoader.SINGLE_BINDER_CAPABILITY ? LazyOptional.of(
                () -> {
                    return this.getOrCreateCapability();
                }).cast() : LazyOptional.empty();
    }

    @Nonnull
    ISingleBinderCapability getOrCreateCapability()
    {
        if (this.singleBinderCapability == null)
            this.singleBinderCapability = new SingleBinderCapability();
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
