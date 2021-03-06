package com.elisis.att.item;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBattery extends Item implements IEnergyContainerItem {
	
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	public ItemBattery() {
		
	}
	
	public ItemBattery(int capacity) {
		
		this(capacity, capacity, capacity);
	}
	
	public ItemBattery(int capacity, int maxTransfer) {
		
		this(capacity, maxTransfer, maxTransfer);

	}
	
	public ItemBattery(int capacity, int maxReceive, int maxExtract) {
		
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	
	}
	
	public ItemBattery setCapacity(int capacity) {
		
		this.capacity = capacity;
		return this;
	}
	
	public void setMaxReceive(int maxReceive) {
		
		this.maxReceive = maxReceive;
	}
	
	public void setMaxExtract(int maxExtract) {
		
		this.maxExtract = maxExtract;
	}
	
	
	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		
		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
		
		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}
	
	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
			
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		
		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyExtracted;
	
	}
	
	@Override
	public int getEnergyStored(ItemStack container) {
		
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
			
		}
		return container.stackTagCompound.getInteger("Energy");
		
	}
	
	@Override
	public int getMaxEnergyStored(ItemStack container) {
		
		return capacity;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		if (getEnergyStored(stack) >= getMaxEnergyStored(stack)) {
			return false;
		}
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return (double) (1 - getEnergyStored(stack) / getMaxEnergyStored(stack));
	}


}
