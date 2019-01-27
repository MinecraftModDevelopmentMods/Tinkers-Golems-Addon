package com.golems_tcon.entity;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySearedGolem extends GolemBase {
	
	public EntitySearedGolem(World world) {
		super(world);
		this.isImmuneToFire = true;
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22D);
	}

//	@Override
//	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) {
//		ItemStack stack = TinkerCommons.searedBrick.copy();
//		stack.setCount(Math.min(6 + this.rand.nextInt(8 + lootingLevel * 4), 36));
//		this.addDrop(dropList, stack, 100);
//	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeGolemTexture(TconGolems.MODID, "seared");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_STONE_STEP;
	}
}