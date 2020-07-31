package xyz.chlamydomonos.minigame.core.loaders;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.chlamydomonos.minigame.block.blocks.BlockGameController;
import xyz.chlamydomonos.minigame.core.Minigame;

@Mod.EventBusSubscriber(modid = Minigame.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockLoader
{
    public static final Block GAME_CONTROLLER = new BlockGameController();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(
                GAME_CONTROLLER
        );
    }
}
