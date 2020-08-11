package xyz.chlamydomonos.minigame.core.loaders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.gui.containers.ContainerGameController;
import xyz.chlamydomonos.minigame.gui.screens.ScreenGameController;

@Mod.EventBusSubscriber(modid = Minigame.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerLoader
{
    public static final ContainerType<ContainerGameController> GAME_CONTROLLER = (ContainerType<ContainerGameController>) IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) -> new ContainerGameController(windowId, inv, data.readBlockPos(), Minecraft.getInstance().world.getWorld(), new IntArray(48))).setRegistryName("container_game_controller");

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event)
    {
        event.getRegistry().registerAll(
                GAME_CONTROLLER
        );
    }

    @SubscribeEvent
    public static void registerContainerScreens(FMLClientSetupEvent event)
    {
        ScreenManager.registerFactory(GAME_CONTROLLER, (ContainerGameController screenContainer, PlayerInventory inv, ITextComponent titleIn) -> new ScreenGameController(screenContainer, inv, titleIn));
    }
}
