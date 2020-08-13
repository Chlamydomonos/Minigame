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
import net.minecraft.util.*;
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

public class ItemShangDiSword extends SwordItem implements IDrawSuondeea
{
    private String name = "shang_di_sword";

    private List<BlockPos> webs;
    private BlockPos origin;

    private static IItemTier itemTier = new SuondeeItemTier();

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

        this.drawSuondeea((ServerWorld) world, attacker.getPosition());

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

                    if(random.nextInt(10) == 1)
                    {
                        ((ServerWorld) worldIn).spawnParticle(ParticleTypes.EXPLOSION, i.getX(), i.getY(), i.getZ(), random.nextInt(10), 0, 0, 0, 0);
                        worldIn.playSound(null, i.getX(), i.getY(), i.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1);
                    }
                }
            }
        }
        return true;
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
