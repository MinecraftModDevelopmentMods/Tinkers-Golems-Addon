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
	}

//	@Override
//	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) 
//	{
//		ItemStack stack = TinkerCommons.ingotArdite.copy();
//		stack.setCount(Math.min(6 + this.rand.nextInt(8 + lootingLevel * 4), 36));
//		this.addDrop(dropList, stack, 100);
//	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeGolemTexture(TconGolems.MODID, "ardite");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_METAL_STEP;
	}
	
	/** 
	 * Called after golem has been spawned. Parameters are the exact IBlockStates used to
	 * make this golem (especially used with multi-textured golems)
	 **/
	@Override
	public void onBuilt(IBlockState body, IBlockState legs, IBlockState arm1, IBlockState arm2) { 
		// TCon uses block states instead of separate block instances,
		// so check here and 'replace' this golem with a new one if needed
		System.out.println("spawned ardite golem");
		if(body.getBlock().equals(TinkerCommons.blockOre)) {
			System.out.println("blockstate: " + body.toString());
		}
	}
}