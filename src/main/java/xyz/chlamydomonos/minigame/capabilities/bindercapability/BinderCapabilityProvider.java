package xyz.chlamydomonos.minigame.capabilities.bindercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BinderCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT>
{
    private IBinderCapability binderCapability;
    private BinderType type;

    public BinderCapabilityProvider(BinderType type)
    {
        this.type = type;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return cap == BinderCapabilityLoader.BINDER_CAPABILITY ? LazyOptional.of(
                () -> this.getOrCreateCapability()).cast() : LazyOptional.empty();
    }

    @Nonnull
    IBinderCapability getOrCreateCapability()
    {
        if (this.binderCapability == null)
        {
            this.binderCapability = new BinderCapability();
            this.binderCapability.setType(this.type);
        }
        return this.binderCapability;
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
