package com.teamtsla.electricrevolution.items;

import com.teamtsla.electricrevolution.blocks.BaseEnergyStorage;
import com.teamtsla.electricrevolution.capabilities.CapabilityProviderEnergyStorage;
import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.List;

public class Battery extends Item {

    public Battery(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setMaxDamage(10);
        this.setMaxStackSize(1);
        setCreativeTab(ModInit.electricRevolutionTab);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new CapabilityProviderEnergyStorage(10000, 1000);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        BaseEnergyStorage bes = (BaseEnergyStorage) stack.getCapability(CapabilityEnergy.ENERGY, null);
        tooltip.add(bes.getEnergyStored()+"/"+bes.getMaxEnergyStored());
    }
}
