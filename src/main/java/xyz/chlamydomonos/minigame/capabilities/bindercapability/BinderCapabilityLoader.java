package xyz.chlamydomonos.minigame.capabilities.bindercapability;

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
public class BinderCapabilityLoader
{
    @CapabilityInject(IBinderCapability.class)
    public static Capability<IBinderCapability> BINDER_CAPABILITY;

    @SubscribeEvent
    public static void registerCapability(FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(
                IBinderCapability.class,
                new Capability.IStorage<IBinderCapability>()
                {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IBinderCapability> capability, IBinderCapability instance, Direction side)
                    {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IBinderCapability> capability, IBinderCapability instance, Direction side, INBT nbt)
                    {

                    }
                },
                () -> null
        );
    }
}
