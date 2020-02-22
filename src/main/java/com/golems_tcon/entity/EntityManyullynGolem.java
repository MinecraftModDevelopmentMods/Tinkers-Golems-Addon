package com.golems_tcon.entity;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityManyullynGolem extends GolemBase {
	
	public EntityManyullynGolem(World world) {
		super(world);
		this.isImmuneToFire = true;
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
		this.setLootTableLoc(TconGolems.MODID, "golem_manyullyn");
	}
	
	@Override
	public ItemStack getPickedResult(final RayTraceResult target) {
		return TinkerCommons.blockManyullyn;
	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeTexture(TconGolems.MODID, "golem_manyullyn");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_METAL_STEP;
	}
}