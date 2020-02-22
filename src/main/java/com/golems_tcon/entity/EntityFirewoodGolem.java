package com.golems_tcon.entity;

import java.util.List;

import com.golems.entity.GolemBase;
import com.golems_tcon.init.TGConfig;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityFirewoodGolem extends GolemBase {
	
	public static final String ALLOW_SPECIAL = "Allow Special: Burn Mobs";
	
	public EntityFirewoodGolem(World world) {
		super(world);
		this.isImmuneToFire = true;
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.255D);
		this.setLootTableLoc(TconGolems.MODID, "golem_firewood");
		this.setCreativeReturn(TinkerCommons.firewood);
	}
	
	/** Attack by lighting on fire as well */
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if(super.attackEntityAsMob(entity)) {
			if(getConfig(this).getBoolean(ALLOW_SPECIAL)) {
				entity.setFire(2 + rand.nextInt(5));
			}
			return true;
		}
		return false;
	}
	
	@Override
	public ItemStack getPickedResult(final RayTraceResult target) {
		return TinkerCommons.firewood;
	}

	@Override
	protected ResourceLocation applyTexture() {
		return GolemBase.makeTexture(TconGolems.MODID, "golem_firewood");
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_WOOD_STEP;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender() {
		return super.getBrightnessForRender() + 32;
	}
	
	@Override
	public List<String> addSpecialDesc(final List<String> list) {
		if(getConfig(this).getBoolean(ALLOW_SPECIAL)) {
			String sBurn = TextFormatting.RED + trans("entitytip.lights_mobs");
			list.add(sBurn);
		}
		return list; 
	}
}