package xyz.chlamydomonos.minigame;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.chlamydomonos.minigame.block.itemblocks.ItemBlockGameController;

@Mod.EventBusSubscriber()
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
