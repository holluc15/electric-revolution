package com.teamtsla.electricrevolution.solarcell;

import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSolarCell extends GuiContainer
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(ElectricRevolutionMod.MODID + ":textures/gui/glowstone_generator.png");
    private final InventoryPlayer player;
    private final TileEntitySolarCell tileentity;

    public GuiSolarCell(InventoryPlayer player, TileEntitySolarCell tileentity)
    {
        super(new ContainerSolarCell(player, tileentity));
        this.player = player;
        this.tileentity = tileentity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String tileName = this.tileentity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(Integer.toString(this.tileentity.getEnergyStored()), 115, 72, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int l = getCookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 113, this.guiTop + 32, 176, 14, l + 1, 16);

        int k = getEnergyStoredScaled(73);
        this.drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 7, 176, 31, 16, 73 - k);
    }

    private int getEnergyStoredScaled(int pixels)
    {
        int i = this.tileentity.getEnergyStored();
        int j = this.tileentity.getMaxEnergyStored();
        float progress = ((float)i) / ((float)j) * pixels;
        return  Math.round(progress);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileentity.getCookTime();
        float progress = ((float)i) / ((float)TileEntitySolarCell.MAX_COOKING_TIME) * pixels;
        return Math.round(progress);
    }
}
