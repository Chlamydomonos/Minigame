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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.BinderType;
import xyz.chlamydomonos.minigame.capabilities.bindercapability.IBinderCapability;
import xyz.chlamydomonos.minigame.capabilities.playercapability.IPlayerCapability;
import xyz.chlamydomonos.minigame.capabilities.playercapability.PlayerCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.ISingleBinderCapability;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.SingleBinderCapabilityLoader;
import xyz.chlamydomonos.minigame.capabilities.singlebindercapability.SingleBinderType;
import xyz.chlamydomonos.minigame.core.loaders.TileEntityLoader;
import xyz.chlamydomonos.minigame.gui.containers.ContainerGameController;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class TileEntityGameController extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{
    private Inventory inventory = new Inventory(9);
    private IntArray intInit = new IntArray(1);
    private List<PlayerEntity> players;

    private NonNullList<ItemStack> controllerContents = NonNullList.withSize(27, ItemStack.EMPTY);

    private boolean inGame = false;

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
        return new ContainerGameController(i, playerInventory, this.pos, this.world, intInit);
    }

    @Override
    public void tick()
    {
        if(this.intInit.get(0) != 0)
        {
            this.initMap();
        }
        else
        {
            if(this.inGame)
            {
                this.restrictPlayers();
            }
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
        this.intInit.set(0, 0);
    }

    private void initMap()
    {
        LazyOptional<IBinderCapability> map = inventory.getStackInSlot(0).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        map.ifPresent((s) -> {
            if(s.getType() == BinderType.MAP)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<IBinderCapability> prepareZone = inventory.getStackInSlot(1).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        prepareZone.ifPresent((s) -> {
            if(s.getType() == BinderType.PREPARE_ZONE)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<ISingleBinderCapability> startGameButton = inventory.getStackInSlot(2).getCapability(SingleBinderCapabilityLoader.SINGLE_BINDER_CAPABILITY);
        startGameButton.ifPresent((s) -> {
            if(s.getType() == SingleBinderType.START_GAME_BUTTON)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<ISingleBinderCapability> aiAmountSetter = inventory.getStackInSlot(3).getCapability(SingleBinderCapabilityLoader.SINGLE_BINDER_CAPABILITY);
        aiAmountSetter.ifPresent((s) -> {
            if(s.getType() == SingleBinderType.START_GAME_BUTTON)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<IBinderCapability> spawnPoint1 = inventory.getStackInSlot(1).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        spawnPoint1.ifPresent((s) -> {
            if(s.getType() == BinderType.SPAWN_POINT)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<IBinderCapability> spawnPoint2 = inventory.getStackInSlot(1).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        spawnPoint2.ifPresent((s) -> {
            if(s.getType() == BinderType.SPAWN_POINT)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<IBinderCapability> spawnPoint3 = inventory.getStackInSlot(1).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        spawnPoint3.ifPresent((s) -> {
            if(s.getType() == BinderType.SPAWN_POINT)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<IBinderCapability> spawnPoint4 = inventory.getStackInSlot(1).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        spawnPoint4.ifPresent((s) -> {
            if(s.getType() == BinderType.SPAWN_POINT)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;

        LazyOptional<IBinderCapability> spawnPoint5 = inventory.getStackInSlot(1).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        spawnPoint5.ifPresent((s) -> {
            if(s.getType() == BinderType.SPAWN_POINT)
                intInit.set(0, 1);
        });
        if(intInit.get(0) == 1)
            intInit.set(0, 0);
        else
            return;
    }

    public void startGame()
    {
        this.inGame = true;

        final int[] prepareZoneXYZ = {0, 0, 0, 0, 0, 0};
        LazyOptional<IBinderCapability> prepareZoneCap = inventory.getStackInSlot(0).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        prepareZoneCap.ifPresent((s) -> {
            BlockPos pos1 = s.getPos1();
            BlockPos pos2 = s.getPos2();
            prepareZoneXYZ[0] = pos1.getX();
            prepareZoneXYZ[1] = pos1.getY();
            prepareZoneXYZ[2] = pos1.getZ();
            prepareZoneXYZ[3] = pos2.getX();
            prepareZoneXYZ[4] = pos2.getY();
            prepareZoneXYZ[5] = pos2.getZ();
        });

        AxisAlignedBB prepareZone = new AxisAlignedBB(prepareZoneXYZ[0], prepareZoneXYZ[1], prepareZoneXYZ[2], prepareZoneXYZ[3], prepareZoneXYZ[4], prepareZoneXYZ[5]);
        this.players = this.world.getEntitiesWithinAABB(PlayerEntity.class, prepareZone);
        for (int i = 0; i < players.size(); i++)
        {
            PlayerEntity playerEntity = players.get(i);
            LazyOptional<IPlayerCapability> playerCap = playerEntity.getCapability(PlayerCapabilityLoader.PLAYER_CAPABILITY);
            int finalI = i;
            playerCap.ifPresent((s) -> {
                if(finalI <= players.size() / 5)
                {
                    s.setPlayerType(2);
                    s.setRunnerType(0);
                }
                else
                {
                    s.setPlayerType(1);
                    s.setRunnerType(2);
                }
            });

            if(i <= players.size() / 5)
            {
                LazyOptional<IBinderCapability> spawnPointCap = inventory.getStackInSlot(8).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
                int finalI1 = i;
                spawnPointCap.ifPresent((s) -> {
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    AxisAlignedBB spawnPoint = new AxisAlignedBB(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
                    Random random = new Random();

                    int x0 = random.nextInt((int) (spawnPoint.maxX - spawnPoint.minX)) + (int) spawnPoint.minX;
                    int y0 = random.nextInt((int) (spawnPoint.maxY - spawnPoint.minY)) + (int) spawnPoint.minY;
                    int z0 = random.nextInt((int) (spawnPoint.maxZ- spawnPoint.minZ)) + (int) spawnPoint.minZ;

                    players.get(finalI1).teleportKeepLoaded(x0, y0, z0);
                });
            }
            else if (i < players.size() / 5 * 2)
            {
                LazyOptional<IBinderCapability> spawnPointCap = inventory.getStackInSlot(4).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
                int finalI1 = i;
                spawnPointCap.ifPresent((s) -> {
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    AxisAlignedBB spawnPoint = new AxisAlignedBB(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
                    Random random = new Random();

                    int x0 = random.nextInt((int) (spawnPoint.maxX - spawnPoint.minX)) + (int) spawnPoint.minX;
                    int y0 = random.nextInt((int) (spawnPoint.maxY - spawnPoint.minY)) + (int) spawnPoint.minY;
                    int z0 = random.nextInt((int) (spawnPoint.maxZ- spawnPoint.minZ)) + (int) spawnPoint.minZ;

                    players.get(finalI1).teleportKeepLoaded(x0, y0, z0);
                });
            }
            else if (i < players.size() / 5 * 3)
            {
                LazyOptional<IBinderCapability> spawnPointCap = inventory.getStackInSlot(5).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
                int finalI1 = i;
                spawnPointCap.ifPresent((s) -> {
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    AxisAlignedBB spawnPoint = new AxisAlignedBB(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
                    Random random = new Random();

                    int x0 = random.nextInt((int) (spawnPoint.maxX - spawnPoint.minX)) + (int) spawnPoint.minX;
                    int y0 = random.nextInt((int) (spawnPoint.maxY - spawnPoint.minY)) + (int) spawnPoint.minY;
                    int z0 = random.nextInt((int) (spawnPoint.maxZ- spawnPoint.minZ)) + (int) spawnPoint.minZ;

                    players.get(finalI1).teleportKeepLoaded(x0, y0, z0);
                });
            }
            else if (i < players.size() / 5 * 4)
            {
                LazyOptional<IBinderCapability> spawnPointCap = inventory.getStackInSlot(6).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
                int finalI1 = i;
                spawnPointCap.ifPresent((s) -> {
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    AxisAlignedBB spawnPoint = new AxisAlignedBB(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
                    Random random = new Random();

                    int x0 = random.nextInt((int) (spawnPoint.maxX - spawnPoint.minX)) + (int) spawnPoint.minX;
                    int y0 = random.nextInt((int) (spawnPoint.maxY - spawnPoint.minY)) + (int) spawnPoint.minY;
                    int z0 = random.nextInt((int) (spawnPoint.maxZ- spawnPoint.minZ)) + (int) spawnPoint.minZ;

                    players.get(finalI1).teleportKeepLoaded(x0, y0, z0);
                });
            }
            else
            {
                LazyOptional<IBinderCapability> spawnPointCap = inventory.getStackInSlot(7).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
                int finalI1 = i;
                spawnPointCap.ifPresent((s) -> {
                    BlockPos pos1 = s.getPos1();
                    BlockPos pos2 = s.getPos2();
                    AxisAlignedBB spawnPoint = new AxisAlignedBB(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
                    Random random = new Random();

                    int x0 = random.nextInt((int) (spawnPoint.maxX - spawnPoint.minX)) + (int) spawnPoint.minX;
                    int y0 = random.nextInt((int) (spawnPoint.maxY - spawnPoint.minY)) + (int) spawnPoint.minY;
                    int z0 = random.nextInt((int) (spawnPoint.maxZ- spawnPoint.minZ)) + (int) spawnPoint.minZ;

                    players.get(finalI1).teleportKeepLoaded(x0, y0, z0);
                });
            }
        }
    }

    private void restrictPlayers()
    {
        final int[] mapXYZ = {0, 0, 0, 0, 0, 0};
        LazyOptional<IBinderCapability> mapCap = inventory.getStackInSlot(0).getCapability(BinderCapabilityLoader.BINDER_CAPABILITY);
        mapCap.ifPresent((s) -> {
            BlockPos pos1 = s.getPos1();
            BlockPos pos2 = s.getPos2();
            mapXYZ[0] = pos1.getX();
            mapXYZ[1] = pos1.getY();
            mapXYZ[2] = pos1.getZ();
            mapXYZ[3] = pos2.getX();
            mapXYZ[4] = pos2.getY();
            mapXYZ[5] = pos2.getZ();
        });
    }
}
