package com.teamtsla.electricrevolution.items;

import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AluminiumIngot extends Item {

    public AluminiumIngot(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModInit.electricRevolutionTab);
    }
}
