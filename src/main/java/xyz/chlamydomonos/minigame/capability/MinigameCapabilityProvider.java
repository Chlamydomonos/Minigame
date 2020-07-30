package xyz.chlamydomonos.minigame.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MinigameCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT>
{
    private IMinigameCapability minigameCapability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return cap == MinigameCapabilityLoader.MINIGAME_CAPABILITY ? LazyOptional.of(
                () -> {
                    return this.getOrCreateCapability();
                }).cast() : LazyOptional.empty();
    }

    @Nonnull
    IMinigameCapability getOrCreateCapability()
    {
        if (this.minigameCapability == null)
            this.minigameCapability = new MinigameCapability();
        return this.minigameCapability;
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
