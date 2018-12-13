package com.teamtsla.electricrevolution.items;

import com.teamtsla.electricrevolution.capabilities.CapabilityProviderEnergyStorage;
import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class BasicBattery extends Item {

    private static final String name = "battery";

    public BasicBattery(String name)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxDamage(10);
        setCreativeTab(ModInit.electricRevolutionTab);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new CapabilityProviderEnergyStorage();
    }
}
