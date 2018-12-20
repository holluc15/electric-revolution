package com.teamtsla.electricrevolution.solarcell;


import com.teamtsla.electricrevolution.blocks.BaseEnergyStorage;
import com.teamtsla.electricrevolution.init.ModInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntitySolarCell extends TileEntity implements ITickable
{
    public ItemStackHandler handler = new ItemStackHandler(1);
    private BaseEnergyStorage storage = new BaseEnergyStorage(100000);

    private String customName;
    private int cookTime;

    public static final int MAX_COOKING_TIME = 60;

    @Override
    public void update()
    {
        if(!handler.getStackInSlot(0).isEmpty() && isItemFuel(handler.getStackInSlot(0)) && this.storage.canReceive())
        {
            if(cookTime == MAX_COOKING_TIME)
            {
                int receivedEnergy = this.storage.receiveEnergy(getFuelValue(handler.getStackInSlot(0),this.world),false);
                System.out.println(receivedEnergy + " RECEIVED ENERGY");

                handler.getStackInSlot(0).shrink(1);
                cookTime = 0;
            }
            else {
                System.out.println(cookTime + " COOK TIME");
                cookTime++;
            }
        }
        else if(cookTime > 0 && handler.getStackInSlot(0).isEmpty())
        {
            cookTime = 0;
        }
        markDirty();
    }

    public boolean isItemFuel(ItemStack stack)
    {
        return getFuelValue(stack,this.world) > 0;
    }

    public int getFuelValue(ItemStack stack, World worldIn)
    {
        if (worldIn.provider.hasSkyLight() &&  stack.getItem() == ModInit.BATTERY) {
            int light = worldIn.getLightFor(EnumSkyBlock.SKY, pos) - worldIn.getSkylightSubtracted();

            if (light > 0) {
                return 1000;
            }
        }
        return 0;

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
        this.storage.writeToBNT(compound);
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
        this.storage.readFromBNT(compound);
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
        return new TextComponentTranslation("container.solarcell");
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


    public boolean isUsableByPlayer(EntityPlayer player)
    {
        return this.world.getTileEntity(this.pos) != this ? false :
                player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }
}