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
    public static final Item SUONDEE_SWORD = new ItemSuondeeSword();
    public static final Item START_GAME_BUTTON_BINDER = new ItemStartGameButtonBinder();
    public static final Item PASSIVE_SUONDEE_SWORD = new ItemPassiveSuondeeSword();
    public static final Item COMPRESSED_SUONDEE_SWORD = new ItemCompressedSuondeeSword();
    public static final Item RECOMPRESSED_SUONDEE_SWORD = new ItemRecompressedSuondeeSword();
    public static final Item DENSE_SUONDEE_SWORD = new ItemDenseSuondeeSword();
    public static final Item SHANG_DI_SWORD = new ItemShangDiSword();
    public static final Item SUONDEE_AXE = new ItemSuondeeAxe();
    public static final Item PASSIVE_SUONDEE_AXE = new ItemPassiveSuondeeAxe();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                MAP_BINDER,
                PREPARE_ZONE_BINDER,
                SPAWN_POINT_BINDER,
                SUONDEE_METAL_INGOT,
                SUONDEE_SWORD,
                START_GAME_BUTTON_BINDER,
                PASSIVE_SUONDEE_SWORD,
                COMPRESSED_SUONDEE_SWORD,
                RECOMPRESSED_SUONDEE_SWORD,
                DENSE_SUONDEE_SWORD,
                SHANG_DI_SWORD,
                SUONDEE_AXE,
                PASSIVE_SUONDEE_AXE
        );
    }
}
