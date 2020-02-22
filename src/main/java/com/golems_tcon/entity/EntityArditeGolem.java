package com.golems_tcon.entity;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TconGolems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityArditeGolem extends GolemBase {
	
	public EntityArditeGolem(World world) {
		super(world);
		this.isImmuneToFire = true;
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22D);
		this.setLootTableLoc(TconGolems.MODID, "golem_ardite");
		this.setCreativeReturn(TinkerCommons.blockArdite);
	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeTexture(TconGolems.MODID, "golem_ardite");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_METAL_STEP;
	}
}