package xyz.chlamydomonos.minigame.capabilities.playercapability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import xyz.chlamydomonos.minigame.core.Minigame;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber()
public class PlayerCapabilityLoader
{

    @CapabilityInject(IPlayerCapability.class)
    public static Capability<IPlayerCapability> PLAYER_CAPABILITY;

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        Entity entity = event.getObject();
        if(entity instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Minigame.MODID, "player"), new PlayerCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {
        LazyOptional<IPlayerCapability> oldPlayerCap = event.getOriginal().getCapability(PLAYER_CAPABILITY);
        LazyOptional<IPlayerCapability> newPlayerCap = event.getPlayer().getCapability(PLAYER_CAPABILITY);
        if (oldPlayerCap.isPresent() && newPlayerCap.isPresent())
        {
            newPlayerCap.ifPresent(
                    (newCap) -> {
                        oldPlayerCap.ifPresent(
                                (oldCap) -> {
                                    newCap.deserializeNBT(oldCap.serializeNBT());
                                });
                    });
            }
    }

    @SubscribeEvent
    public static void registerCapability(FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(
                IPlayerCapability.class,
                new Capability.IStorage<IPlayerCapability>()
                {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IPlayerCapability> capability, IPlayerCapability instance, Direction side)
                    {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IPlayerCapability> capability, IPlayerCapability instance, Direction side, INBT nbt)
                    {

                    }
                },
                () -> null
        );
    }
}
