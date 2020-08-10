package xyz.chlamydomonos.minigame.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;
import xyz.chlamydomonos.minigame.core.loaders.ItemLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemShangDiSword extends SwordItem
{
    private String name = "shang_di_sword";

    private List<BlockPos> webs;
    private BlockPos origin;

    private static IItemTier itemTier = new IItemTier()
    {
        @Override
        public int getMaxUses()
        {
            return 1;
        }

        @Override
        public float getEfficiency()
        {
            return 10;
        }

        @Override
        public float getAttackDamage()
        {
            return Float.MAX_VALUE;
        }

        @Override
        public int getHarvestLevel()
        {
            return 3;
        }

        @Override
        public int getEnchantability()
        {
            return 30;
        }

        @Override
        public Ingredient getRepairMaterial()
        {
            return Ingredient.fromItems(ItemLoader.SUONDEE_METAL_INGOT);
        }
    };

    public ItemShangDiSword()
    {
        super(itemTier, 1, 0, new Properties().group(ItemGroupLoader.MINIGAME_MISC));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        DamageSource source = target.getLastDamageSource();
        World world = attacker.world;

        this.drawSuondeea(Minecraft.getInstance().world, attacker.getPosition());

        if(!world.isRemote)
        {
            AxisAlignedBB aabb = attacker.getBoundingBox().grow(16.0D);
            List<LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, aabb);

            List<BlockPos> positions = new ArrayList<>();
            positions.add(target.getPosition());

            for (LivingEntity i : entities)
            {
                if (i != attacker)
                {
                    positions.add(i.getPosition());
                    i.attackEntityFrom(source, Float.MAX_VALUE);
                }
            }

            for(BlockPos i : positions)
            {
                LightningBoltEntity bolt = new LightningBoltEntity(world, i.getX(), i.getY(), i.getZ(), true);
                ((ServerWorld) world).addLightningBolt(bolt);
            }
        }
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        if(!worldIn.isRemote)
        {
            if (state.getBlock() == Blocks.COBWEB)
            {
                webs = new ArrayList<>();
                origin = pos;
                searchWebs(worldIn, pos);

                for (BlockPos i : webs)
                {
                    worldIn.destroyBlock(i, true, entityLiving);
                }
            }
        }
        return true;
    }

    private void drawSuondeea(World world, BlockPos pos)
    {
        double x = pos.getX() - 4, y = pos.getY() + 3, z = pos.getZ();
        double pi = Math.PI;
        Random random = new Random();

        for (double i = -(pi); i < pi / 2.0D; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        y += 0.8D;

        for (double i = 0; i < pi * 3.0D / 2.0D; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        y -= 0.8D;
        x += 1;

        for (double i = - pi; i < 0; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        for (double i = 0; i < 0.4D; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, -0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 2; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        for (double i = -0.4D; i < 0; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, -0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 2; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        for (double i = - 0.4D; i < 1.2D; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 7 / 4; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, i, random.nextInt(20)), suondeeMath(y, 0, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 7 / 4; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        for (double i = - 0.4D; i < 0.4D; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, i, random.nextInt(20)), suondeeMath(y, 0, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);

        }

        x += 1;

        for (double i = 0; i < pi * 2; i += pi / 100)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeCos(x, i, random.nextInt(20)), suondeeSin(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }

        for (double i = - 0.4D; i < 0; i += 0.01D)
        {
            world.addParticle(ParticleTypes.ENCHANT, suondeeMath(x, 0.4D, random.nextInt(20)), suondeeMath(y, i, random.nextInt(20)), suondeeMath(z, 0, random.nextInt(20)), 0, 0, 0);
        }
    }

    private double suondeeCos(double x, double i, int r)
    {
        return x + 0.4D * Math.cos(i) + ((double) r - 10) / 100;
    }

    private double suondeeSin(double y, double i, int r)
    {
        return y + 0.4D * Math.sin(i) + ((double) r - 10) / 100;
    }

    private double suondeeMath(double x, double i, int r)
    {
        return x + i + ((double) r - 10) / 100;
    }

    private void searchWebs(World world, BlockPos pos)
    {
        webs.add(pos);

        for(int i = 0; i < 6; i++)
        {
            if(world.getBlockState(pos.offset(Direction.byIndex(i))).getBlock() == Blocks.COBWEB && !webs.contains(pos.offset(Direction.byIndex(i))) && pos.withinDistance(origin, 32))
            {
                searchWebs(world, pos.offset(Direction.byIndex(i)));
            }
        }
    }
}
