package com.golems_tcon.entity;

import java.util.List;

import com.golems.entity.GolemBase;
import com.golems.entity.GolemMultiTextured;
import com.golems.util.WeightedItem;
import com.golems_tcon.event.handler.TconCommonEventHandler;
import com.golems_tcon.init.TGConfig;
import com.golems_tcon.init.TconGolems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.shared.TinkerCommons;

public class EntityCongealSlimeGolem extends GolemMultiTextured
{
	public static final String ALLOW_SPECIAL = "Allow Special: Knockback";
	public static final String KNOCKBACK_FACTOR = "Knockback Factor";
	public static final String sPrefix = "slime";
	
	public EntityCongealSlimeGolem(World world) 
	{
		super(world, TGConfig.CONGEAL_SLIME.getBaseAttack(), new ItemStack(TconCommonEventHandler.getTconBlock("slime_congealed")), sPrefix, EnumSlimeType.allNames);
		this.setCanSwim(true);
	}

	@Override
	public void addGolemDrops(List<WeightedItem> dropList, boolean recentlyHit, int lootingLevel) 
	{
		ItemStack stack = EnumSlimeType.get(this.getTextureNum()).getDropStack();
		stack.stackSize = Math.min(3 + this.rand.nextInt(6 + lootingLevel), 12);
		this.addDrop(dropList, stack, 100);
	}

	@Override
	protected void applyAttributes() 
	{
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(TGConfig.CONGEAL_SLIME.getMaxHealth());
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.245D);
	}

	@Override
	public SoundEvent getGolemSound() 
	{
		return SoundEvents.BLOCK_SLIME_HIT;
	}

	@Override
	public String getModId() 
	{
		return TconGolems.MODID;
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if(super.attackEntityAsMob(entity))
		{
			if(TGConfig.CONGEAL_SLIME.getBoolean(ALLOW_SPECIAL))
			{
				knockbackTarget(entity, TGConfig.CONGEAL_SLIME.getFloat(KNOCKBACK_FACTOR));
			}
			return true;
		}
		return false;
	}

	@Override
	protected void damageEntity(DamageSource source, float amount) 
	{
		if (!this.isEntityInvulnerable(source))
		{
			super.damageEntity(source, amount);
			if(source.getSourceOfDamage() != null && TGConfig.CONGEAL_SLIME.getBoolean(ALLOW_SPECIAL))
			{
				knockbackTarget(source.getSourceOfDamage(), TGConfig.CONGEAL_SLIME.getFloat(KNOCKBACK_FACTOR) * 0.625F);
			}
		}
	}
	
	protected void knockbackTarget(Entity entity, final double KNOCKBACK_FACTOR)
	{
		double dX = Math.signum(entity.posX - this.posX) * KNOCKBACK_FACTOR;
		double dZ = Math.signum(entity.posZ - this.posZ) * KNOCKBACK_FACTOR;
		entity.addVelocity(dX, KNOCKBACK_FACTOR / 4, dZ);
		entity.velocityChanged = true;
	}
	
	public static enum EnumSlimeType
	{
		GREEN("green", TextFormatting.GREEN),
		BLUE("blue", TextFormatting.AQUA),
		PURPLE("purple", TextFormatting.DARK_PURPLE),
		BLOOD("blood", TextFormatting.DARK_RED),
		MAGMA("magma", TextFormatting.GOLD);
		
		public static final String[] allNames = {GREEN.getName(), BLUE.getName(), PURPLE.getName(), BLOOD.getName(), MAGMA.getName()};
		private static final ItemStack matSlimeBallGreen = new ItemStack(Items.SLIME_BALL);
		
		private String name;
		private TextFormatting color;
		
		private EnumSlimeType(String nameIn, TextFormatting colorIn)
		{
			this.name = nameIn;
			this.color = colorIn;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public TextFormatting getColor()
		{
			return this.color;
		}
		
		public ItemStack getDropStack()
		{
			switch(this)
			{
			case BLUE:	return TinkerCommons.matSlimeBallBlue.copy();
			case PURPLE: return TinkerCommons.matSlimeBallPurple.copy();
			case BLOOD: return TinkerCommons.matSlimeBallBlood.copy();
			case MAGMA: return TinkerCommons.matSlimeBallMagma.copy();
			case GREEN: default: return matSlimeBallGreen.copy();
			}
		}
		
		public static EnumSlimeType get(int index)
		{
			return EnumSlimeType.values()[index];
		}
		
		public static EnumSlimeType get(GolemBase golem)
		{
			if(golem instanceof GolemMultiTextured)
			{
				return get(((GolemMultiTextured)golem).getTextureNum());
			}
			return GREEN;
		}
	}
}