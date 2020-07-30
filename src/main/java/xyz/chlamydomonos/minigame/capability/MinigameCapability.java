package xyz.chlamydomonos.minigame.capability;

import net.minecraft.nbt.CompoundNBT;

public class MinigameCapability implements IMinigameCapability
{
    private int playerType; //0: normal player; 1: runner; 2: chaser
    private int runnerType; //0: not runner; 1: still; 2: running; 3-oo:unstable
    private int score;

    public MinigameCapability(int playerType, int runnerType, int score)
    {
        this.playerType = playerType;
        this.runnerType = runnerType;
        this.score = score;
    }

    public MinigameCapability()
    {
        this(0, 0, 0);
    }

    @Override
    public int getPlayerType()
    {
        return this.playerType;
    }

    @Override
    public void setPlayerType(int type)
    {
        this.playerType = type;
    }

    @Override
    public int getRunnerType()
    {
        return this.runnerType;
    }

    @Override
    public void setRunnerType(int type)
    {
        this.runnerType = type;
    }

    @Override
    public int tickRunnerType()
    {
        if(this.runnerType > 2)
            this.runnerType -= 1;
        return this.runnerType;
    }

    @Override
    public int getScore()
    {
        return this.score;
    }

    @Override
    public void setScore(int score)
    {
        this.score = score;
    }

    @Override
    public int addScore(int score)
    {
        this.score += score;
        return this.score;
    }

    @Override
    public int minusScore(int score)
    {
        this.score -= score;
        if (this.score < 0)
            this.score = 0;
        return this.score;
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        CompoundNBT minigameNBT = new CompoundNBT();
        minigameNBT.putInt("player_type", this.playerType);
        minigameNBT.putInt("runner_type", this.runnerType);
        minigameNBT.putInt("score", this.score);
        return minigameNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        this.playerType = nbt.getInt("player_type");
        this.runnerType = nbt.getInt("runner_type");
        this.score = nbt.getInt("score");
    }
}
