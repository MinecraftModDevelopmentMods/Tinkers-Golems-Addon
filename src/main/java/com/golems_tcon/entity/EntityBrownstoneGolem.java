package com.golems_tcon.entity;

import java.util.List;

import com.golems.entity.GolemBase;
import com.golems.util.WeightedItem;
import com.golems_tcon.event.handler.TconCommonEventHandler;
import com.golems_tcon.init.TGConfig;
import com.golems_tcon.init.TconGolems;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityBrownstoneGolem extends GolemBase
{
	public EntityBrownstoneGolem(World world) 
	{
		super(world, TGConfig.BROWNSTONE.getBaseAttack(), TconCommonEventHandler.getTconBlock("brownstone"));
	}

	@Override
	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) 
	{
		Block brownstone = TconCommonEventHandler.getTconBlock("brownstone");
		if(brownstone != null)
		{
			this.addDrop(dropList, brownstone, 0, 6, Math.min(rand.nextInt(8 + lootingLevel * 4), 30), 100);
		}
	}

	@Override
	protected void applyAttributes() 
	{
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(TGConfig.BROWNSTONE.getMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29D);
	}

	@Override
	protected ResourceLocation applyTexture() 
	{
		return GolemBase.makeGolemTexture(TconGolems.MODID, "brownstone");
	}

	@Override
	public SoundEvent getGolemSound() 
	{
		return SoundEvents.BLOCK_GLASS_STEP;
	}
}