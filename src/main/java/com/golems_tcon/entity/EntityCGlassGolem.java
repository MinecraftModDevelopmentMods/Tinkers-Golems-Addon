package com.golems_tcon.entity;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityCGlassGolem extends GolemBase {
	
	public EntityCGlassGolem(World world) {
		super(world);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22D);
		this.setLootTableLoc(TconGolems.MODID, "golem_clear_glass");
	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeTexture(TconGolems.MODID, "golem_clear_glass");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_GLASS_STEP;
	}
}