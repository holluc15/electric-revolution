package com.teamtsla.electricrevolution.solarcell;

import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import com.teamtsla.electricrevolution.blocks.BlockBase;
import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class SolarCell extends BlockBase {

    public SolarCell(String name)
    {
        super(name, Material.IRON, ModInit.electricRevolutionTab);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
        {
            playerIn.openGui(ElectricRevolutionMod.instance, ElectricRevolutionMod.GUI_SOLAR_CELL,
                    worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }


    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntitySolarCell();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntitySolarCell tileentity = (TileEntitySolarCell)worldIn.getTileEntity(pos);
        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
        super.breakBlock(worldIn, pos, state);
    }
}