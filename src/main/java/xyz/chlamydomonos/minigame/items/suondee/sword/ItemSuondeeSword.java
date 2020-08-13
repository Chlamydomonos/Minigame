package xyz.chlamydomonos.minigame.items.suondee.sword;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
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
import xyz.chlamydomonos.minigame.algorithm.IDrawSuondeea;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;
import xyz.chlamydomonos.minigame.core.loaders.ItemLoader;
import xyz.chlamydomonos.minigame.items.suondee.SuondeeItemTier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemSuondeeSword extends SwordItem implements IDrawSuondeea
{
    private String name = "suondee_sword";

    private static IItemTier itemTier = new SuondeeItemTier();

    private List<BlockPos> webs;
    private BlockPos origin;

    public ItemSuondeeSword()
    {
        super(itemTier, 0, 0, new Properties().group(ItemGroupLoader.MINIGAME_MISC));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        DamageSource source = target.getLastDamageSource();
        World world = attacker.world;

        boolean result = super.hitEntity(stack, target, attacker);

        this.drawSuondeea((ServerWorld) world, attacker.getPosition());

        if(result && !world.isRemote)
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
        return result;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        Random random = new Random();
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

                    if(random.nextInt(5) == 1)
                    {
                        ((ServerWorld) worldIn).spawnParticle(ParticleTypes.EXPLOSION, i.getX(), i.getY(), i.getZ(), random.nextInt(10), 0, 0, 0, 0);
                    }
                }
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
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
