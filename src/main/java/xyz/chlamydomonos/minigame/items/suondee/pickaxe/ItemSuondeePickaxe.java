package xyz.chlamydomonos.minigame.items.suondee.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.chlamydomonos.minigame.algorithm.IDrawSuondeea;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;
import xyz.chlamydomonos.minigame.items.suondee.SuondeeItemTier;

public class ItemSuondeePickaxe extends PickaxeItem implements IDrawSuondeea
{
    private String name = "suondee_pickaxe";

    private static IItemTier itemTier = new SuondeeItemTier();

    public ItemSuondeePickaxe()
    {
        super(itemTier, 0, 0, new Properties().group(ItemGroupLoader.MINIGAME_MISC));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        if(!worldIn.isRemote)
        {
            drawSuondeea((ServerWorld) worldIn, entityLiving.getPosition());


        }

        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }
}
