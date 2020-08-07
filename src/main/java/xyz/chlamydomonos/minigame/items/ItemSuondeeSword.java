package xyz.chlamydomonos.minigame.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
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

public class ItemSuondeeSword extends SwordItem
{
    private String name = "suondee_sword";

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

    public ItemSuondeeSword()
    {
        super(itemTier, 1, 0, new Properties().group(ItemGroupLoader.MINIGAME_MISC));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        DamageSource source = target.getLastDamageSource();
        World world = attacker.world;

        boolean result = super.hitEntity(stack, target, attacker);

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
        if(state.getBlock() != Blocks.COBWEB)
            return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
        else
        {
            return true;
            // not finished
        }
    }
}
