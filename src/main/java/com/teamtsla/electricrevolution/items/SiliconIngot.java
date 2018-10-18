package com.teamtsla.electricrevolution.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SiliconIngot extends Item {

    public SiliconIngot(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);
    }
}