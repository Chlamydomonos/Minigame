package xyz.chlamydomonos.minigame.core.loaders;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.chlamydomonos.minigame.block.tileentities.TileEntityGameController;
import xyz.chlamydomonos.minigame.core.Minigame;

@Mod.EventBusSubscriber(modid = Minigame.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityLoader
{
    public static final TileEntityType<TileEntityGameController> GAME_CONTROLLER = (TileEntityType<TileEntityGameController>) TileEntityType.Builder.create(() -> new TileEntityGameController(), BlockLoader.GAME_CONTROLLER).build(null).setRegistryName(new ResourceLocation(Minigame.MODID, "tile_entity_game_controller"));

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event)
    {
        event.getRegistry().register(GAME_CONTROLLER);
    }
}
