package xyz.chlamydomonos.minigame.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import xyz.chlamydomonos.minigame.core.Minigame;
import xyz.chlamydomonos.minigame.gui.containers.ContainerGameController;

public class ScreenGameController extends ContainerScreen<ContainerGameController>
{
    private String path = "textures/gui/game_controller.png";

    private ResourceLocation texture = new ResourceLocation(Minigame.MODID, this.path);

    private int textureWidth = 176;
    private int textureHeight = 166;

    public ScreenGameController(ContainerGameController screenContainer, PlayerInventory inv, ITextComponent titleIn)
    {
        super(screenContainer, inv, titleIn);
        this.xSize = textureWidth;
        this.ySize = textureHeight;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //魔数不能怪我，原版就这么写的
        this.font.drawString(I18n.format("block.xbmngm.game_controller"), 4, 4, 4210752);
        this.font.drawString(I18n.format("game_controller.tip"), 4, 14, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        this.renderBackground();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(texture);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(i, j, 0, 0, xSize, ySize);
    }
}
