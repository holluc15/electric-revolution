package com.teamtsla.electricrevolution.tileentities;

import com.teamtsla.electricrevolution.blocks.BaseEnergyStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import scala.xml.dtd.impl.Base;

import javax.annotation.Nullable;

public class TileEntityBaseEnergyStorage extends TileEntity implements ITickable {

    private BaseEnergyStorage storage = new BaseEnergyStorage(10000);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY) {
            return (T)this.storage;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void update()
    {
        this.storage.receiveEnergy(100, false);
        this.storage.extractEnergy(200, false);
    }
}
