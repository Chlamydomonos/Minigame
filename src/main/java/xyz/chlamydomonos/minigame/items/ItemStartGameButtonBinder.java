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
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderCapabilityProvider;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.IBinderCapability;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.ISingleBinderCapability;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.SingleBinderCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.SingleBinderCapabilityProvider;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.SingleBinderType;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.core.loaders.ItemGroupLoader;

import javax.annotation.Nullable;
import java.util.List;

public class ItemStartGameButtonBinder extends Item {
    private String name = "start_game_button_binder";

    public ItemStartGameButtonBinder() {
        super(new Properties().group(ItemGroupLoader.MINIGAME));
        this.setRegistryName(new ResourceLocation(Minigame.MODID, this.name));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        return new SingleBinderCapabilityProvider(SingleBinderType.START_GAME_BUTTON);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player)
    {
        if(!player.getEntityWorld().isRemote)
        {
            LazyOptional<ISingleBinderCapability> StartGameButtonBinderCap = itemstack.getCapability(SingleBinderCapabilityLoader.SINGLE_BINDER_CAPABILITY);
            StartGameButtonBinderCap.ifPresent(
                    (s) -> {
                        s.setPos(pos);
                        player.sendMessage(new StringTextComponent("Point set to " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()));
                    }
            );
        }

        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        LazyOptional<ISingleBinderCapability> StartGameButtonBinderCap = stack.getCapability(SingleBinderCapabilityLoader.SINGLE_BINDER_CAPABILITY);
        StartGameButtonBinderCap.ifPresent(
                (s) -> {
                    BlockPos pos1 = s.getPos();
                    int x1 = pos1.getX();
                    int y1 = pos1.getY();
                    int z1 = pos1.getZ();
                    tooltip.add(new StringTextComponent(I18n.format("bound_to") + ":"));
                    tooltip.add(new StringTextComponent(I18n.format("point") + ": " + x1 + ", " + y1 + ", " + z1));
                }
        );
    }
}


