package com.teamtsla.electricrevolution.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.energy.IEnergyStorage;

public class Battery extends Item implements IEnergyStorage {

    public Battery(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxDamage(10);
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 1;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 100;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
