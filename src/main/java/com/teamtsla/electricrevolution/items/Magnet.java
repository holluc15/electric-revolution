package com.teamtsla.electricrevolution.items;

import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;


public class Magnet extends Item {

    public Magnet(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModInit.electricRevolutionTab);
    }


}
