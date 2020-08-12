package xyz.chlamydomonos.minigame.items.suondee.axe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.chlamydomonos.minigame.algorithm.IDrawSuondeea;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;
import xyz.chlamydomonos.minigame.core.loaders.ItemLoader;

import java.util.Random;

public class ItemShangDiAxe extends AxeItem implements IDrawSuondeea
{
    private String name = "shang_di_axe";

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

    public ItemShangDiAxe()
    {
        super(itemTier, 0, 0, new Properties().group(ItemGroupLoader.MINIGAME_MISC));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        Random random = new Random();

        if(!worldIn.isRemote)
        {
            this.drawSuondeea((ServerWorld) worldIn, entityLiving.getPosition());

            int minx = pos.getX() - 64, miny = pos.getY() - 64, minz = pos.getZ() - 64, maxx = pos.getX() + 64, maxy = pos.getY() + 64, maxz = pos.getZ() + 64;

            for (int xi = minx; xi < maxx; xi++)
            {
                for (int yi = miny; yi < maxy; yi++)
                {
                    for (int zi = minz; zi < maxz; zi++)
                    {
                        BlockPos woodPos = new BlockPos(xi, yi, zi);
                        Material woodMaterial = worldIn.getBlockState(woodPos).getMaterial();
                        if(woodMaterial == Material.WOOD || woodMaterial == Material.PLANTS || woodMaterial == Material.TALL_PLANTS || woodMaterial == Material.BAMBOO || woodMaterial == Material.LEAVES)
                        {
                            worldIn.destroyBlock(woodPos, true, entityLiving);
                            if(random.nextInt(10) == 1)
                            {
                                LightningBoltEntity bolt = new LightningBoltEntity(worldIn, woodPos.getX(), woodPos.getY(), woodPos.getZ(), true);
                                ((ServerWorld) worldIn).addLightningBolt(bolt);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        Block block = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
        if (block != null)
        {
            PlayerEntity playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world.isRemote)
            {
                world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, blockstate.get(RotatedPillarBlock.AXIS)), 11);
            }

            return ActionResultType.SUCCESS;
        }
        else
        {
            return ActionResultType.PASS;
        }
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        return true;
    }
}
