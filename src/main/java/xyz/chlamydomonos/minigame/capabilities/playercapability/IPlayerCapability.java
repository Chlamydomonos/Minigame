package xyz.chlamydomonos.minigame.capabilities.playercapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerCapability extends INBTSerializable<CompoundNBT>
{
    int getPlayerType();
    void setPlayerType(int type);
    int getRunnerType();
    void setRunnerType(int type);
    int tickRunnerType();
    int getScore();
    void setScore(int score);
    int addScore(int score);
    int minusScore(int score);
}
