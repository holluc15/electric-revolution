package com.teamtsla.electricrevolution.lightgenerator;

import com.teamtsla.electricrevolution.blocks.BaseEnergyStorage;
import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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

public class TileEntityLightGenerator extends TileEntity implements ITickable
{
    public ItemStackHandler handler = new ItemStackHandler(1);
    private BaseEnergyStorage storage = new BaseEnergyStorage(100000);

    private String customName;
    private int cookTime;

    public static final int MAX_COOKING_TIME = 30;

    @Override
    public void update()
    {
        if(!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0)) && this.storage.canReceive())
        {
            if(cookTime == MAX_COOKING_TIME)
            {
                int receivedEnergy = this.storage.receiveEnergy(getFuelValue(handler.getStackInSlot(0)),false);

                if(storage.getEnergyStored() > 0) {
                    this.getBlockType().setLightLevel(5f);
                    sendUpdates();
                }
                handler.getStackInSlot(0).shrink(1);
                cookTime = 0;
            }
            else {
                cookTime++;
            }
        }
        else if(cookTime > 0 && handler.getStackInSlot(0).isEmpty())
        {
            cookTime = 0;
        }

        if(storage.getEnergyStored() >= 10 && cookTime == 0) {
            storage.setEnergy(storage.getEnergyStored() - 10);
        }
        if(storage.getEnergyStored() == 0) {
            this.getBlockType().setLightLevel(0f);
            sendUpdates();
        }
        markDirty();
    }

    private void sendUpdates() {
        world.markBlockRangeForRenderUpdate(pos, pos);
        world.notifyBlockUpdate(pos, getState(), getState(), 3);
        world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
        markDirty();
    }

    private IBlockState getState() {
        return world.getBlockState(pos);
    }


    public boolean isItemFuel(ItemStack stack)
    {
        return getFuelValue(stack) > 0;
    }

    public int getFuelValue(ItemStack stack)
    {
        if(stack.getItem() == ModInit.BATTERY) return 10000;
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
        return new TextComponentTranslation("container.light_generator");
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
