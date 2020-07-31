package xyz.chlamydomonos.minigame.core.loaders;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class EventLoader
{
    public static final EventBus EVENT_BUS = new EventBus();
}
