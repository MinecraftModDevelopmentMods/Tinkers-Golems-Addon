package com.golems_tcon.entity;

import java.util.List;

import com.golems.entity.GolemBase;
import com.golems.util.WeightedItem;
import com.golems_tcon.init.TGConfig;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityFirewoodGolem extends GolemBase 
{
	public static final String ALLOW_SPECIAL = "Allow Special: Burn Mobs";
	
	public EntityFirewoodGolem(World world) 
	{
		super(world, TGConfig.FIREWOOD.getBaseAttack());
		this.isImmuneToFire = true;
	}
	
	/** Attack by lighting on fire as well */
	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if(super.attackEntityAsMob(entity))
		{
			if(TGConfig.FIREWOOD.getBoolean(ALLOW_SPECIAL))
			{
				entity.setFire(2 + rand.nextInt(5));
			}
			return true;
		}
		return false;
	}

	@Override
	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) 
	{
		ItemStack planks = TinkerCommons.lavawood.copy();
		ItemStack logs = TinkerCommons.firewood.copy();
		planks.setCount(1 + rand.nextInt(3));
		this.addDrop(dropList, logs, 50 + lootingLevel * 8);
		this.addDrop(dropList, planks, 90 + lootingLevel * 5);
	}

	@Override
	protected void applyAttributes() 
	{
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(TGConfig.FIREWOOD.getMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.255D);
	}

	@Override
	protected ResourceLocation applyTexture() 
	{
		return GolemBase.makeGolemTexture(TconGolems.MODID, "firewood");
	}

	@Override
	public SoundEvent getGolemSound() 
	{
		return SoundEvents.BLOCK_WOOD_STEP;
	}
}