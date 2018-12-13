package com.teamtsla.electricrevolution.capabilities;

import com.teamtsla.electricrevolution.blocks.BaseEnergyStorage;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderEnergyStorage implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    public static final ResourceLocation NAME = new ResourceLocation("electricrevolution", "battery_empty");

    private final BaseEnergyStorage storage = new BaseEnergyStorage();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(storage);
        }
        return null;
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setInteger("energy", storage.getEnergyStored());
        dataTag.setInteger("capacity", storage.getMaxEnergyStored());
        dataTag.setInteger("chargeSpeed", storage.getChargeSpeed());
        dataTag.setInteger("dischargeSpeed", storage.getDischargeSpeed());
        return dataTag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if(nbt.hasKey("energy")) {
            storage.setEnergy(nbt.getInteger("energy"));
        }
        if(nbt.hasKey("capacity")) {
            storage.setCapacity(nbt.getInteger("capacity"));
        }
        if(nbt.hasKey("chargeSpeed")) {
            storage.setChargeSpeed(nbt.getInteger("chargeSpeed"));
        }
        if(nbt.hasKey("dischargeSpeed")) {
            storage.setDischargeSpeed(nbt.getInteger("dischargeSpeed"));
        }

        if(storage.getEnergyStored() > storage.getMaxEnergyStored()) {
            storage.setEnergy(storage.getMaxEnergyStored());
        }
    }
}
