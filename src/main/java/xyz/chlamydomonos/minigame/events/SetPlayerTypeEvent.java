package xyz.chlamydomonos.minigame.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class SetPlayerTypeEvent extends PlayerEvent
{
    public final int playerType;

    public SetPlayerTypeEvent(PlayerEntity player, int type)
    {
        super(player);
        this.playerType = type;
    }
}
