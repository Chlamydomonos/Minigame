package xyz.chlamydomonos.minigame.capabilities.singlebindercapability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber()
public class SingleBinderCapabilityLoader
{
    @CapabilityInject(ISingleBinderCapability.class)
    public static Capability<ISingleBinderCapability> SINGLE_BINDER_CAPABILITY;

    @SubscribeEvent
    public static void registerCapability(FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(
                ISingleBinderCapability.class,
                new Capability.IStorage<ISingleBinderCapability>()
                {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<ISingleBinderCapability> capability, ISingleBinderCapability instance, Direction side)
                    {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<ISingleBinderCapability> capability, ISingleBinderCapability instance, Direction side, INBT nbt)
                    {

                    }
                },
                () -> null
        );
    }
}
