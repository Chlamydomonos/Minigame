package xyz.chlamydomonos.minigame.capabilities.bindercapability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class BinderCapabilityLoader
{
    @CapabilityInject(IBinderCapability.class)
    public static Capability<IBinderCapability> BINDER_CAPABILITY;
}
