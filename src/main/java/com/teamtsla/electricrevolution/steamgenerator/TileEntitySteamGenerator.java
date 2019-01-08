package com.teamtsla.electricrevolution.steamgenerator;

import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import com.teamtsla.electricrevolution.blocks.BaseEnergyStorage;
import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntitySteamGenerator extends TileEntity implements ITickable
{
    public ItemStackHandler handler = new ItemStackHandler(3);
    private BaseEnergyStorage storage = new BaseEnergyStorage(100000);

    private String customName;
    private int cookTime;

    public static final int MAX_COOKING_TIME = 60;

    @Override
    public void update()
    {
        //System.out.println("START: "+cookTime);
        if(!handler.getStackInSlot(1).isEmpty() && isItemFuel(handler.getStackInSlot(1)) &&
           !handler.getStackInSlot(0).isEmpty() && handler.getStackInSlot(0).getItem() == Items.WATER_BUCKET)
        {
            if(!handler.getStackInSlot(2).isEmpty() && handler.getStackInSlot(2).getItem() == ModInit.BATTERY &&
                ((BaseEnergyStorage) handler.getStackInSlot(2).getCapability(CapabilityEnergy.ENERGY, null)).canReceive())
            {
                BaseEnergyStorage battery = (BaseEnergyStorage) handler.getStackInSlot(2).getCapability(CapabilityEnergy.ENERGY, null);

                if(cookTime == MAX_COOKING_TIME)
                {
                    battery.receiveEnergy(getFuelValue(handler.getStackInSlot(1)),false);

                    handler.getStackInSlot(1).shrink(1);
                    cookTime = 0;
                }
                else {
                    cookTime++;
                }
            }
            else {
                cookTime = 0;
            }
        }
        else if(cookTime > 0 && (handler.getStackInSlot(0).isEmpty() || handler.getStackInSlot(1).isEmpty()))
        {
            cookTime = 0;
        }
        //System.out.println("END: "+cookTime);
/*
        if(storage.canExtract() && !handler.getStackInSlot(2).isEmpty() && handler.getStackInSlot(2).getItem() == ModInit.BATTERY)
        {
            BaseEnergyStorage battery = (BaseEnergyStorage) handler.getStackInSlot(2).getCapability(CapabilityEnergy.ENERGY, null);

            if(battery.canReceive())
            {
                int extractableEnergy = storage.extractEnergy(battery.getChargeSpeed(), true);
                int receiveableEnergy = battery.receiveEnergy(extractableEnergy, true);
                //System.out.println("Ex["+extractableEnergy+"] Re["+receiveableEnergy+"]");
                int transferedEnergy = Math.min(extractableEnergy, receiveableEnergy);

                storage.extractEnergy(transferedEnergy, false);
                battery.receiveEnergy(transferedEnergy, false);
            }
        }
*/
        markDirty();
    }

    public int getPercentageFromBattery()
    {
        int percentage = 0;

        if(!handler.getStackInSlot(2).isEmpty())
        {
            BaseEnergyStorage battery = (BaseEnergyStorage) handler.getStackInSlot(2).getCapability(CapabilityEnergy.ENERGY, null);

            int capacity = battery.getMaxEnergyStored();
            int energyStored = battery.getEnergyStored();

            percentage = Math.round(((float)energyStored / (float)capacity)* 100f);
        }

        return percentage;
    }

    public boolean isItemFuel(ItemStack stack)
    {
        return getFuelValue(stack) > 0;
    }

    public int getFuelValue(ItemStack stack)
    {
        if(stack.getItem() == Items.LAVA_BUCKET) return 10000;
        else if(stack.getItem() == new ItemStack(Blocks.COAL_BLOCK).getItem()) return 5000;
        else if(stack.getItem() == Items.COAL) return 1000;
        else if(stack.getItem() == new ItemStack(Blocks.LOG).getItem()) return 1000;
        else if(stack.getItem() == new ItemStack(Blocks.PLANKS).getItem()) return 1000;
        else if(stack.getItem() == Items.STICK) return 500;
        else return 0;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY) return true;
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityEnergy.ENERGY) return (T)this.storage;
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("Inventory", this.handler.serializeNBT());
        compound.setInteger("CookTime", this.cookTime);
        compound.setString("Name", getDisplayName().toString());
        this.storage.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
        int ct = compound.getInteger("CookTime");
        this.cookTime = compound.getInteger("CookTime");
        this.customName = compound.getString("Name");
        this.storage.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound compound = writeToNBT(new NBTTagCompound());
        return  compound;
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentTranslation("container.steam_generator");
    }

    public int getEnergyStored()
    {
        return this.storage.getEnergyStored();
    }

    public int getMaxEnergyStored()
    {
        return this.storage.getMaxEnergyStored();
    }

    public int getCookTime()
    {
        return this.cookTime;
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.storage.getEnergyStored();
            case 1:
                return this.cookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.storage.setEnergy(value);
                break;
            case 1:
                this.cookTime = value;
                break;
        }
    }

    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false :
                player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }
}
