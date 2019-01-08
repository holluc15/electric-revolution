package com.teamtsla.electricrevolution.steamgenerator;

import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSteamGenerator extends GuiContainer
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(ElectricRevolutionMod.MODID + ":textures/gui/steam_generator.png");
    private final InventoryPlayer player;
    private final TileEntitySteamGenerator tileentity;

    public GuiSteamGenerator(InventoryPlayer player, TileEntitySteamGenerator tileentity)
    {
        super(new ContainerSteamGenerator(player, tileentity));
        this.player = player;
        this.tileentity = tileentity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String tileName = this.tileentity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
        // Draw Battery Percentage
        //System.out.println("REQ from TEXT");
        this.fontRenderer.drawString(String.format("%3d%s",this.tileentity.getPercentageFromBattery(), "%"), 146, 74, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int l = getCookProgressScaled(22);
        this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 35, 177, 14, l, 16);

        int k = getEnergyStoredScaled(64);
        this.drawTexturedModalRect(this.guiLeft + 152, this.guiTop + 7, 176, 31, 16, 64 - k);
    }

    // to render Tooltips for Items (won't without)
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    private int getEnergyStoredScaled(int pixels)
    {
        //System.out.println("REQ from ENG_SCALED");
        int percentage = this.tileentity.getPercentageFromBattery();
        int progress = Math.round((float)percentage * ((float)pixels / 100));
        return  progress;
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileentity.getCookTime();
        float progress = ((float)i) / ((float) TileEntitySteamGenerator.MAX_COOKING_TIME) * pixels;
        return Math.round(progress);
    }
}
