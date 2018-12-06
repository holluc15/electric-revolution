package com.teamtsla.electricrevolution.items;

import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.energy.IEnergyStorage;

public class Battery extends Item implements IEnergyStorage {

    private int energy = 0;
    private final int MAX_ENERGY = 100;

    public Battery(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxDamage(10);
        setCreativeTab(ModInit.electricRevolutionTab);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        energy += maxReceive;
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        energy -= maxExtract;
        return 1;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return MAX_ENERGY;
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
