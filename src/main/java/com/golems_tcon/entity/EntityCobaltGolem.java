package com.golems_tcon.entity;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TconGolems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.BlockMetal;

public class EntityCobaltGolem extends GolemBase {
	
	public EntityCobaltGolem(World world) {
		super(world);
		this.isImmuneToFire = true;
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.238D);
		this.setLootTableLoc(TconGolems.MODID, "golem_cobalt");
	}

	/** 
	 * Called after golem has been spawned. Parameters are the exact IBlockStates used to
	 * make this golem (especially used with multi-textured golems)
	 **/
	@Override
	public void onBuilt(IBlockState body, IBlockState legs, IBlockState arm1, IBlockState arm2) { 
		// TCon uses block states instead of separate block instances,
		// so check here and 'replace' this golem with a new one if needed
		switch(body.getValue(BlockMetal.TYPE)) {
		case ALUBRASS:
			// what's this?
			break;
		case ARDITE:
			this.replaceWith(new EntityArditeGolem(this.getEntityWorld()));
			break;
		case COBALT:
			// do nothing
			break;
		case KNIGHTSLIME:
			this.replaceWith(new EntityKnightSlimeGolem(this.getEntityWorld()));
			break;
		case MANYULLYN:
			this.replaceWith(new EntityManyullynGolem(this.getEntityWorld()));
			break;
		case PIGIRON:
			this.replaceWith(new EntityPigironGolem(this.getEntityWorld()));
			break;
		case SILKY_JEWEL:
			this.replaceWith(new EntitySilkyGolem(this.getEntityWorld()));
			break;
		default:
			break;
		}
	}
	
	private void replaceWith(GolemBase golem) {
		golem.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
		this.getEntityWorld().spawnEntity(golem);
		golem.setPlayerCreated(this.isPlayerCreated());
		this.setDead();
	}
	
	@Override
	public ItemStack getPickedResult(final RayTraceResult target) {
		return TinkerCommons.blockCobalt;
	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeTexture(TconGolems.MODID, "golem_cobalt");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_METAL_STEP;
	}
}