package com.teamtsla.electricrevolution.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Battery extends Item {

    public Battery(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);
    }
}
