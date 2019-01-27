package com.golems_tcon.entity;

import java.util.List;

import com.golems.entity.GolemBase;
import com.golems.entity.GolemMultiTextured;
import com.golems_tcon.init.TconGolems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityCongealedSlimeGolem extends GolemMultiTextured
{
	public static final String ALLOW_SPECIAL = "Allow Special: Knockback";
	public static final String KNOCKBACK_FACTOR = "Knockback Factor";
	public static final String sPrefix = "congealedslime";
	
	public EntityCongealedSlimeGolem(World world) {
		super(world, sPrefix, EnumSlimeType.allNames);
		this.setCanSwim(true);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.245D);
		// after some deliberation, I decided not to change the
		// golem's loot table based on texture. The slime blocks
		// are obtained in different ways, so the change here is 
		// purely cosmetic -- it will always drop green slime balls
		this.setLootTableLoc(TconGolems.MODID, "golem_" + sPrefix);
	}

	@Override
    protected ResourceLocation getLootTable() {
		return this.lootTableLoc;
    }
	
	/** 
	 * Called after golem has been spawned. Parameters are the exact IBlockStates used to
	 * make this golem (especially used with multi-textured golems)
	 **/
	@Override
	public void onBuilt(IBlockState body, IBlockState legs, IBlockState arm1, IBlockState arm2) { 
		byte meta = (byte)(body.getBlock().getMetaFromState(body) % this.getNumTextures());
		this.setTextureNum(meta);
	}

	@Override
	public SoundEvent getGolemSound() {
		return SoundEvents.BLOCK_SLIME_HIT;
	}

	@Override
	public String getModId() {
		return TconGolems.MODID;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if(super.attackEntityAsMob(entity)) {
			if(getConfig(this).getBoolean(ALLOW_SPECIAL)) {
				knockbackTarget(entity, getConfig(this).getFloat(KNOCKBACK_FACTOR));
			}
			return true;
		}
		return false;
	}

	@Override
	protected void damageEntity(DamageSource source, float amount) {
		if (!this.isEntityInvulnerable(source)) {
			super.damageEntity(source, amount);
			if(source.getImmediateSource() != null && getConfig(this).getBoolean(ALLOW_SPECIAL)) {
				knockbackTarget(source.getImmediateSource(), getConfig(this).getFloat(KNOCKBACK_FACTOR) * 0.625F);
			}
		}
	}
	
	protected void knockbackTarget(Entity entity, final double KNOCKBACK_FACTOR) {
		double dX = Math.signum(entity.posX - this.posX) * KNOCKBACK_FACTOR;
		double dZ = Math.signum(entity.posZ - this.posZ) * KNOCKBACK_FACTOR;
		entity.addVelocity(dX, KNOCKBACK_FACTOR / 4, dZ);
		entity.velocityChanged = true;
	}
	
	public EnumSlimeType getSlimeType() {
		return EnumSlimeType.get(this.getTextureNum());
	}
	
	@Override
	public List<String> addSpecialDesc(final List<String> list) {
		if(getConfig(this).getBoolean(ALLOW_SPECIAL)) {
			TextFormatting format = this.getSlimeType().getColor();
			String sKnockback = format + trans("entitytip.has_knockback");
			list.add(sKnockback);
		}
		return list; 
	}
	
	public static enum EnumSlimeType {
		GREEN("green", TextFormatting.GREEN),
		BLUE("blue", TextFormatting.AQUA),
		PURPLE("purple", TextFormatting.DARK_PURPLE),
		BLOOD("blood", TextFormatting.DARK_RED),
		MAGMA("magma", TextFormatting.GOLD);
		
		public static final String[] allNames = {GREEN.getName(), BLUE.getName(), PURPLE.getName(), BLOOD.getName(), MAGMA.getName()};
		private static final ItemStack matSlimeBallGreen = new ItemStack(Items.SLIME_BALL);
		
		private String name;
		private TextFormatting color;
		
		private EnumSlimeType(String nameIn, TextFormatting colorIn) {
			this.name = nameIn;
			this.color = colorIn;
		}
		
		public String getName() {
			return this.name;
		}
		
		public TextFormatting getColor() {
			return this.color;
		}
		
		@Deprecated
		public ItemStack getDropStack() {
			switch(this)
			{
			case BLUE:	return TinkerCommons.matSlimeBallBlue.copy();
			case PURPLE: return TinkerCommons.matSlimeBallPurple.copy();
			case BLOOD: return TinkerCommons.matSlimeBallBlood.copy();
			case MAGMA: return TinkerCommons.matSlimeBallMagma.copy();
			case GREEN: default: return matSlimeBallGreen.copy();
			}
		}
		
		public static EnumSlimeType get(int index) {
			return EnumSlimeType.values()[index % EnumSlimeType.values().length];
		}
		
		public static EnumSlimeType get(GolemBase golem) {
			if(golem instanceof GolemMultiTextured) {
				return get(((GolemMultiTextured)golem).getTextureNum());
			}
			return GREEN;
		}
	}
	
	
}