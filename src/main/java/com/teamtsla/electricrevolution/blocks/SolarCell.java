package com.teamtsla.electricrevolution.blocks;

import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.Random;

public class SolarCell extends BlockDaylightDetector implements IItemHandler {


    private boolean inverted;
    public SolarCell(String name,boolean inverted) {

        super(inverted);
        this.inverted = inverted;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModInit.electricRevolutionTab);


        this.setHardness(2f);
        this.setResistance(4f);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune){
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public void updatePower(World worldIn, BlockPos pos) {
        System.out.println("update Power!");
        if (worldIn.provider.hasSkyLight())
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            int i = worldIn.getLightFor(EnumSkyBlock.SKY, pos) - worldIn.getSkylightSubtracted();
            float f = worldIn.getCelestialAngleRadians(1.0F);

            if (this.inverted)
            {
                i = 15 - i;
            }

            if (i > 0 && !this.inverted)
            {
                float f1 = f < (float)Math.PI ? 0.0F : ((float)Math.PI * 2F);
                f = f + (f1 - f) * 0.2F;
                i = Math.round((float)i * MathHelper.cos(f));
            }

            i = MathHelper.clamp(i, 0, 15);

            if (((Integer)iblockstate.getValue(POWER)).intValue() != i)
            {
                worldIn.setBlockState(pos, iblockstate.withProperty(POWER, Integer.valueOf(i)), 3);
            }
            System.out.println(((Integer)iblockstate.getValue(POWER)).intValue() + " POWER");
        }
    }

    @Override
    public boolean isStickyBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }


    @Override
    public int getSlots() {
        return 1;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return new ItemStack(ModInit.BATTERY);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return new ItemStack(ModInit.BATTERY,amount);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }
}