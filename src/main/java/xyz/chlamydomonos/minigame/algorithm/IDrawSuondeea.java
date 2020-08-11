package xyz.chlamydomonos.minigame.algorithm;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public interface IDrawSuondeea
{
    default void drawSuondeea(ServerWorld world, BlockPos pos)
    {
        double x = pos.getX() - 4, y = pos.getY() + 3, z = pos.getZ();
        double pi = Math.PI;
        Random random = new Random();

        for (double i = -(pi); i < pi / 2.0D; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        y += 0.8D;

        for (double i = 0; i < pi * 3.0D / 2.0D; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        y -= 0.8D;
        x += 1;

        for (double i = - pi; i < 0; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        for (double i = 0; i < 0.4D; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, -0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 2; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        for (double i = -0.4D; i < 0; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, -0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 2; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        for (double i = - 0.4D; i < 1.2D; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 7 / 4; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, i, random.nextInt(20)), suondeeMath(y, 0, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 7 / 4; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, i, random.nextInt(20)), suondeeMath(y, 0, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 2; i += pi / 100)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }

        for (double i = - 0.4D; i < 0; i += 0.01D)
        {
            world.spawnParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 1, 0, 0, 0, 0);
        }
    }

    default double suondeeCos(double x, double i, int r)
    {
        return x + 0.4D * Math.cos(i) + ((double) r - 10) / 100;
    }

    default double suondeeSin(double y, double i, int r)
    {
        return y + 0.4D * Math.sin(i) + ((double) r - 10) / 100;
    }

    default double suondeeMath(double x, double i, int r)
    {
        return x + i + ((double) r - 10) / 100;
    }
}
