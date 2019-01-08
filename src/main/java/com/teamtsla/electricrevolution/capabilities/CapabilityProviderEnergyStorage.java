package com.teamtsla.electricrevolution.capabilities;

import com.teamtsla.electricrevolution.blocks.BaseEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderEnergyStorage implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    //public static final ResourceLocation NAME = new ResourceLocation("electricrevolution", "battery_empty");

    private final BaseEnergyStorage storage;

    public CapabilityProviderEnergyStorage(int capacity, int transferSpeed)
    {
        storage = new BaseEnergyStorage(capacity, transferSpeed);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY) {
            return (T) this.storage;
        }
        return null;
    }

    public int getEnergyStored()
    {
        return this.storage.getEnergyStored();
    }

    public int getMaxEnergyStored()
    {
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return  storage.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        storage.readFromNBT(nbt);
    }
}
