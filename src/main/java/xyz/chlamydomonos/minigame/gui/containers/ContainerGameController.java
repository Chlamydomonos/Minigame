package xyz.chlamydomonos.minigame.gui.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.chlamydomonos.minigame.block.tileentities.TileEntityGameController;
import xyz.chlamydomonos.minigame.core.loaders.ContainerLoader;

public class ContainerGameController extends Container
{
    private IntArray intInit;

    public ContainerGameController(int id, PlayerInventory playerInventory, BlockPos pos, World world, IntArray intArray)
    {
        super(ContainerLoader.GAME_CONTROLLER, id);
        this.intInit = intArray;
        trackIntArray(this.intInit);
        TileEntityGameController tileEntity = (TileEntityGameController) world.getTileEntity(pos);
        this.addSlot(new Slot(tileEntity.getInventory(), 0, 8,30));
        this.addSlot(new Slot(tileEntity.getInventory(), 1, 32,30));
        this.addSlot(new Slot(tileEntity.getInventory(), 2, 56,30));
        this.addSlot(new Slot(tileEntity.getInventory(), 3, 80,30));
        this.addSlot(new Slot(tileEntity.getInventory(), 4, 8,60));
        this.addSlot(new Slot(tileEntity.getInventory(), 5, 26,60));
        this.addSlot(new Slot(tileEntity.getInventory(), 6, 44,60));
        this.addSlot(new Slot(tileEntity.getInventory(), 7, 62,60));
        this.addSlot(new Slot(tileEntity.getInventory(), 8, 80,60));
        layoutPlayerInventorySlots(playerInventory, 8, 84);
    }

    //抄的neutrino教程（实在懒得写了）
    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow)
    {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    //抄的neutrino教程（实在懒得写了）
    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
    {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    //抄的neutrino教程（实在懒得写了）
    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx)
    {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }

    public IIntArray getIntArray() {
        return intInit;
    }
}
