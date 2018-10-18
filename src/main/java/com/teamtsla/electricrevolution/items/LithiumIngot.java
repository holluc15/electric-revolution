package com.teamtsla.electricrevolution.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LithiumIngot extends Item {

    public LithiumIngot(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);
    }
}