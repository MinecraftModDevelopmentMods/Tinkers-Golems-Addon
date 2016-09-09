package com.golems_tcon.entity;

import java.util.List;

import com.golems.entity.GolemBase;
import com.golems.util.WeightedItem;
import com.golems_tcon.init.TGConfig;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityArditeGolem extends GolemBase
{
	public EntityArditeGolem(World world) 
	{
		super(world, TGConfig.ARDITE.getBaseAttack(), TinkerCommons.blockArdite);
		this.isImmuneToFire = true;
	}

	@Override
	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) 
	{
		ItemStack stack = TinkerCommons.ingotArdite.copy();
		stack.stackSize = Math.min(6 + this.rand.nextInt(8 + lootingLevel * 4), 36);
		this.addDrop(dropList, stack, 100);
	}

	@Override
	protected void applyAttributes() 
	{
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(TGConfig.ARDITE.getMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22D);
	}

	@Override
	protected ResourceLocation applyTexture() 
	{
		return this.makeGolemTexture(TconGolems.MODID, "ardite");
	}

	@Override
	public SoundEvent getGolemSound() 
	{
		return SoundEvents.BLOCK_METAL_STEP;
	}
}