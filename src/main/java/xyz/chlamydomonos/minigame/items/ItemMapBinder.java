package xyz.chlamydomonos.minigame.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderType;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.IBinderCapability;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderCapabilityProvider;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMapBinder extends Item
{
    private final String name = "map_binder";

    public ItemMapBinder()
    {
        super(new Properties()
                .group(ItemGroupLoader.MINIGAME)
                .maxStackSize(1));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        return new BinderCapabilityProvider(BinderType.MAP);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        LazyOptional<IBinderCapability> MapBinderCap = context.getItem().getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        MapBinderCap.ifPresent(
                (s) -> {
                    s.setPos2(context.getPos());
                    if(!context.getWorld().isRemote)
                        context.getPlayer().sendMessage(new StringTextComponent("Second point set to " + context.getPos().getX() + ", " + context.getPos().getY() + ", " + context.getPos().getZ()));
                });

        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player)
    {
        LazyOptional<IBinderCapability> MapBinderCap = itemstack.getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        MapBinderCap.ifPresent(
                (s) -> {
                    s.setPos1(pos);
                    if(!player.world.isRemote)
                        player.sendMessage(new StringTextComponent("First point set to " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()));
                });

        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        LazyOptional<IBinderCapability> MapBinderCap = stack.getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        MapBinderCap.ifPresent(
                (s) -> {
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    int x1 = pos1.getX();
                    int y1 = pos1.getY();
                    int z1 = pos1.getZ();
                    int x2 = pos2.getX();
                    int y2 = pos2.getY();
                    int z2 = pos2.getZ();
                    tooltip.add(new StringTextComponent(I18n.format("bound_to") + ":"));
                    tooltip.add(new StringTextComponent(I18n.format("point") + "1: " + x1 + ", " + y1 + ", " + z1));
                    tooltip.add(new StringTextComponent(I18n.format("point") + "2: " + x2 + ", " + y2 + ", " + z2));
                }
        );
    }
}
