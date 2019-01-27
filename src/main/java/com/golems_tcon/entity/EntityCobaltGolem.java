package com.golems_tcon.entity;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCobaltGolem extends GolemBase {
	
	public EntityCobaltGolem(World world) {
		super(world);
		this.isImmuneToFire = true;
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.238D);
	}

//	@Override
//	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) 
//	{
//		ItemStack stack = TinkerCommons.ingotCobalt.copy();
//		stack.setCount(Math.min(6 + this.rand.nextInt(8 + lootingLevel * 4), 36));
//		this.addDrop(dropList, stack, 100);
//	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeGolemTexture(TconGolems.MODID, "cobalt");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_METAL_STEP;
	}
}