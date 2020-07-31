package xyz.chlamydomonos.minigame.capabilities.playercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT>
{
    private IPlayerCapability playerCapability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return cap == PlayerCapabilityLoader.PLAYER_CAPABILITY ? LazyOptional.of(
                () -> {
                    return this.getOrCreateCapability();
                }).cast() : LazyOptional.empty();
    }

    @Nonnull
    IPlayerCapability getOrCreateCapability()
    {
        if (this.playerCapability == null)
            this.playerCapability = new PlayerCapability();
        return this.playerCapability;
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
