package xyz.chlamydomonos.minigame;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.chlamydomonos.minigame.block.blocks.BlockGameController;

@Mod.EventBusSubscriber()
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
