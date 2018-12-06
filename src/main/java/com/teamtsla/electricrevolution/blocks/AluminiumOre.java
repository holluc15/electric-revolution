package com.teamtsla.electricrevolution.blocks;

import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class AluminiumOre extends Block {


    public AluminiumOre(String name) {
        super(Material.ROCK);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModInit.electricRevolutionTab);

        //Config
        this.setHardness(2f);
        this.setResistance(4f);
        this.setSoundType(SoundType.STONE);


    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune){
        return Item.getItemFromBlock(this);
    }
}
