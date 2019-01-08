package com.teamtsla.electricrevolution.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import javax.annotation.Nullable;

public class BaseEnergyStorage implements IEnergyStorage {

    private int energy;
    private int capacity;
    private int chargeSpeed;
    private int dischargeSpeed;

    public BaseEnergyStorage() { }

    public BaseEnergyStorage(int capacity)
    {
        this(0, capacity, capacity, capacity);
    }

    public BaseEnergyStorage(int capacity, int maxTransferSpeed)
    {
        this(0, capacity, maxTransferSpeed, maxTransferSpeed);
    }

    public BaseEnergyStorage(int capacity, int chargeSpeed, int dischargeSpeed)
    {
        this(0, capacity, chargeSpeed, dischargeSpeed);
    }

    public BaseEnergyStorage(int energy, int capacity, int chargeSpeed, int dischargeSpeed)
    {
        this.energy = Math.max(0, Math.min(capacity, energy));
        this.capacity = capacity;
        this.chargeSpeed = chargeSpeed;
        this.dischargeSpeed = dischargeSpeed;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        int charge = Math.min(maxReceive, Math.min(this.chargeSpeed, (this.capacity - this.energy)));

        if(!simulate) {
            this.energy += charge;
            return charge;
        }
        return charge;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        int discharge = Math.min(maxExtract, Math.min(this.energy, this.dischargeSpeed));

        if(!simulate) {
            this.energy -= discharge;
            return discharge;
        }
        return discharge;
    }

    @Override
    public int getEnergyStored()
    {
        return this.energy;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return this.capacity;
    }

    @Override
    public boolean canExtract()
    {
        return this.energy > 0 && this.capacity > 0;
    }

    @Override
    public boolean canReceive()
    {
        return this.energy < this.capacity;
    }

    public void setCapacity(int capacity)
    {
        if(capacity < this.energy) {
            this.energy = capacity;
        }
        this.capacity = capacity;
    }

    public void setEnergy(int energy)
    {
        if(energy > this.capacity) {
            energy = this.capacity;
        }
        this.energy = energy;
    }

    public int getChargeSpeed() {
        return chargeSpeed;
    }

    public int getDischargeSpeed() {
        return dischargeSpeed;
    }

    public void setChargeSpeed(int chargeSpeed)
    {
        if(chargeSpeed > this.capacity) {
            chargeSpeed = this.capacity;
        }
        this.chargeSpeed = chargeSpeed;
    }

    public void setDischargeSpeed(int dischargeSpeed)
    {
        if(dischargeSpeed > this.capacity) {
            dischargeSpeed = this.capacity;
        }
        this.dischargeSpeed = dischargeSpeed;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        // DEBUG
        /*
        int cap = compound.getInteger("capacity");
        int eng = compound.getInteger("energy");
        int chs = compound.getInteger("chargeSpeed");
        int dcs = compound.getInteger("dischargeSpeed");

        System.out.println("[READ] CAP: " + cap + " ENG: " + eng + " CHS: " + chs + " DCS: " + dcs);
        */
        setCapacity(compound.getInteger("capacity"));
        setEnergy(compound.getInteger("energy"));
        setChargeSpeed(compound.getInteger("chargeSpeed"));
        setDischargeSpeed(compound.getInteger("dischargeSpeed"));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        // DEBUG
        /*
        int cap = getMaxEnergyStored();
        int eng = getEnergyStored();
        int chs = getChargeSpeed();
        int dcs = getDischargeSpeed();

        System.out.println("[WRITE] CAP: " + cap + " ENG: " + eng + " CHS: " + chs + " DCS: " + dcs);
        */
        compound.setInteger("capacity", getMaxEnergyStored());
        compound.setInteger("energy", getEnergyStored());
        compound.setInteger("chargeSpeed", getChargeSpeed());
        compound.setInteger("dischargeSpeed", getDischargeSpeed());
        return compound;
    }
}
