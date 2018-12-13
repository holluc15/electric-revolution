package com.teamtsla.electricrevolution.gui;

import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import com.teamtsla.electricrevolution.glowstonegenerator.ContainerGlowstoneGenerator;
import com.teamtsla.electricrevolution.glowstonegenerator.GuiGlowstoneGenerator;
import com.teamtsla.electricrevolution.glowstonegenerator.TileEntityGlowstoneGenerator;
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
        if(ID == ElectricRevolutionMod.GUI_GLOWSTONE_GENERATOR) {
            return new ContainerGlowstoneGenerator(player.inventory,
                    (TileEntityGlowstoneGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == ElectricRevolutionMod.GUI_GLOWSTONE_GENERATOR) {
            return new GuiGlowstoneGenerator(player.inventory,
                    (TileEntityGlowstoneGenerator)world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }
}
