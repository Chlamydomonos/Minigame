package xyz.chlamydomonos.minigame.core.loaders;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.items.*;

@Mod.EventBusSubscriber(modid = Minigame.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemLoader
{
    public static final Item MAP_BINDER = new ItemMapBinder();
    public static final Item PREPARE_ZONE_BINDER = new ItemPrepareZoneBinder();
    public static final Item SPAWN_POINT_BINDER = new ItemSpawnPointBinder();
    public static final Item SUONDEE_METAL_INGOT = new ItemSuondeeMetalIngot();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                MAP_BINDER,
                PREPARE_ZONE_BINDER,
                SPAWN_POINT_BINDER,
                SUONDEE_METAL_INGOT
        );
    }
}
