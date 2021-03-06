package com.teamtsla.electricrevolution.gui;

import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import com.teamtsla.electricrevolution.glowstonecharger.ContainerGlowstoneCharger;
import com.teamtsla.electricrevolution.glowstonecharger.GuiGlowstoneCharger;
import com.teamtsla.electricrevolution.glowstonecharger.TileEntityGlowstoneCharger;
import com.teamtsla.electricrevolution.lightgenerator.ContainerLightGenerator;
import com.teamtsla.electricrevolution.lightgenerator.GuiLightGenerator;
import com.teamtsla.electricrevolution.lightgenerator.TileEntityLightGenerator;
import com.teamtsla.electricrevolution.solarcell.ContainerSolarCell;
import com.teamtsla.electricrevolution.solarcell.GuiSolarCell;
import com.teamtsla.electricrevolution.solarcell.TileEntitySolarCell;
import com.teamtsla.electricrevolution.steamgenerator.ContainerSteamGenerator;
import com.teamtsla.electricrevolution.steamgenerator.GuiSteamGenerator;
import com.teamtsla.electricrevolution.steamgenerator.TileEntitySteamGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == ElectricRevolutionMod.GUI_SOLAR_CELL) {
            return new ContainerSolarCell(player.inventory,
                    (TileEntitySolarCell) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if(ID == ElectricRevolutionMod.GUI_LIGHT_GENERATOR) {
            return new ContainerLightGenerator(player.inventory,
                    (TileEntityLightGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        }
        if(ID == ElectricRevolutionMod.GUI_STEAM_GENERATOR) {
            return new ContainerSteamGenerator(player.inventory,
                    (TileEntitySteamGenerator) world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == ElectricRevolutionMod.GUI_SOLAR_CELL) {
            return new GuiSolarCell(player.inventory,
                    (TileEntitySolarCell) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if(ID == ElectricRevolutionMod.GUI_LIGHT_GENERATOR) {
            return new GuiLightGenerator(player.inventory,
                    (TileEntityLightGenerator) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if(ID == ElectricRevolutionMod.GUI_STEAM_GENERATOR) {
            return new GuiSteamGenerator(player.inventory,
                    (TileEntitySteamGenerator) world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }
}
