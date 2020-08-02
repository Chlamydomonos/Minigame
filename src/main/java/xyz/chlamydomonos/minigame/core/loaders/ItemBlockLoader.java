package xyz.chlamydomonos.minigame.core.loaders;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.chlamydomonos.minigame.block.itemblocks.*;
import xyz.chlamydomonos.minigame.core.Minigame;

@Mod.EventBusSubscriber(modid = Minigame.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemBlockLoader
{
    public static final Item GAME_CONTROLLER = new ItemBlockGameController();

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                GAME_CONTROLLER
        );
    }
}
