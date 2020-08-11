package xyz.chlamydomonos.minigame.block.tileentities;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderType;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.IBinderCapability;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.ISingleBinderCapability;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.SingleBinderCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.SingleBinderType;
import xyz.chlamydomonos.minigame.core.loaders.TileEntityLoader;
import xyz.chlamydomonos.minigame.gui.containers.ContainerGameController;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

public class TileEntityGameController extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    private Inventory inventory = new Inventory(9);
    private IntArray information = new IntArray(48);

    private boolean initialized = false;

    private NonNullList<ItemStack> controllerContents = NonNullList.withSize(27, ItemStack.EMPTY);

    public TileEntityGameController()
    {
        super(TileEntityLoader.GAME_CONTROLLER);
    }

    public Inventory getInventory()
    {
        return inventory;
    }


    @Override
    public ITextComponent getDisplayName()
    {
        return new StringTextComponent(I18n.format("block.xbmngm.game_controller"));
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new ContainerGameController(i, playerInventory, this.pos, this.world, information);
    }

    @Override
    public void tick()
    {
        if(!this.initialized)
        {
            this.initMap();
        }
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);

        this.controllerContents = NonNullList.withSize(this.getInventory().getSizeInventory(), ItemStack.EMPTY);

        ItemStackHelper.loadAllItems(compound, this.controllerContents);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);

        ItemStackHelper.saveAllItems(compound, this.controllerContents);

        return compound;
    }

    public void start()
    {
        this.initialized = false;
    }

    private void initMap()
    {
        final boolean[] ok = {false};

        for (int i = 0; i < 48; i++)
            this.information.set(i, 0);

        LazyOptional<IBinderCapability> cap1 = inventory.getStackInSlot(0).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        cap1.ifPresent(
                (s) -> {
                    ok[0] = true;
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    int[] temp = {pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ()};
                    if((temp[0] == temp[3] && temp[1] == temp[4] && temp[2] == temp[5]) || s.getType() != BinderType.MAP)
                        ok[0] = false;
                    else
                    {
                        for (int i = 0; i < 6; i++)
                            this.information.set(i, temp[i]);
                        ok[0] = true;
                    }
                }
        );

        if(ok[0])
            ok[0] = false;
        else
            return;

        LazyOptional<IBinderCapability> cap2 = inventory.getStackInSlot(1).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        cap2.ifPresent(
                (s) -> {
                    ok[0] = true;
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    int[] temp = {pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ()};
                    if((temp[0] == temp[3] && temp[1] == temp[4] && temp[2] == temp[5]) || s.getType() != BinderType.PREPARE_ZONE)
                        ok[0] = false;
                    else
                    {
                        for (int i = 0; i < 6; i++)
                            this.information.set(i + 6, temp[i]);
                        ok[0] = true;
                    }
                }
        );

        if(ok[0])
            ok[0] = false;
        else
            return;
        
        LazyOptional<ISingleBinderCapability> cap3 = inventory.getStackInSlot(2).getCapability(SingleBinderCapabilityLoader.SINGLE_BINDER_CAPABILITY);
        cap3.ifPresent(
                (s) -> {
                    ok[0] = true;
                    BlockPos pos1 = s.getPos();
                    int[] temp = {pos1.getX(), pos1.getY(), pos1.getZ()};
                    if(s.getType() != SingleBinderType.START_GAME_BUTTON)
                        ok[0] = false;
                    else
                    {
                        for (int i = 0; i < 3; i++)
                        {
                            this.information.set(i + 12, temp[i]);
                        }
                    }
                }
        );
    }
}
