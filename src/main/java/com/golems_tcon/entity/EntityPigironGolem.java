package com.golems_tcon.entity;

import java.util.List;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TGConfig;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityPigironGolem extends GolemBase {
	
	public static final String BACON_CHANCE = "Bacon Drop Chance";

	private int CHANCE;
	
	public EntityPigironGolem(World world) {
		super(world);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32D);
		this.CHANCE = TGConfig.PIGIRON.getInt(BACON_CHANCE);
	}

//	@Override
//	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) 
//	{
//		ItemStack stack = TinkerCommons.ingotPigIron.copy();
//		stack.setCount(Math.min(6 + this.rand.nextInt(8 + lootingLevel * 4), 36));
//		this.addDrop(dropList, stack, 100);
//	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if(super.attackEntityAsMob(entity)) {
			// occasionally drop bacon when attacking
			if(this.world.rand.nextInt(100) < this.CHANCE) {
				ItemStack stack = TinkerCommons.bacon.copy();
				EntityItem entityitem = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, stack);
				float f3 = 0.4F;
				entityitem.motionX = (double)((float)this.world.rand.nextGaussian() * f3);
				entityitem.motionY = (double)((float)this.world.rand.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double)((float)this.world.rand.nextGaussian() * f3);
				if(!this.world.isRemote) {
					this.world.spawnEntity(entityitem);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeGolemTexture(TconGolems.MODID, "pigiron");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_METAL_STEP;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PIG_AMBIENT;
	}
	
	@Override
	public int getTalkInterval() {
		return 240 - this.rand.nextInt(30);
	}

	@Override
	protected SoundEvent getHurtSound(final DamageSource ignored) {
		return rand.nextInt(4) == 0 ? SoundEvents.ENTITY_PIG_HURT : getGolemSound();
	}
	
	@Override
	public List<String> addSpecialDesc(final List<String> list) {
		if(this.CHANCE > 0) {
			String sTasty = TextFormatting.LIGHT_PURPLE + trans("modifier.baconlicious.name");
			list.add(sTasty);
		}
		return list; 
	}
}