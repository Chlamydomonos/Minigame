package xyz.chlamydomonos.minigame.capability;

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
import xyz.chlamydomonos.minigame.Minigame;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber()
public class MinigameCapabilityLoader
{

    @CapabilityInject(IMinigameCapability.class)
    public static Capability<IMinigameCapability> MINIGAME_CAPABILITY;

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        Entity entity = event.getObject();
        if(entity instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Minigame.MODID, "minigame"), new MinigameCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {
        if(!event.isWasDeath())
        {
            LazyOptional<IMinigameCapability> oldMinigameCap = event.getOriginal().getCapability(MINIGAME_CAPABILITY);
            LazyOptional<IMinigameCapability> newMinigameCap = event.getPlayer().getCapability(MINIGAME_CAPABILITY);
            if (oldMinigameCap.isPresent() && newMinigameCap.isPresent())
            {
                newMinigameCap.ifPresent(
                        (newCap) -> {
                            oldMinigameCap.ifPresent(
                                    (oldCap) -> {
                                        newCap.deserializeNBT(oldCap.serializeNBT());
                                    });
                        });
            }
        }
    }

    @SubscribeEvent
    public static void registerCapability(FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(
                IMinigameCapability.class,
                new Capability.IStorage<IMinigameCapability>()
                {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IMinigameCapability> capability, IMinigameCapability instance, Direction side)
                    {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IMinigameCapability> capability, IMinigameCapability instance, Direction side, INBT nbt)
                    {

                    }
                },
                () -> null
        );
    }
}
