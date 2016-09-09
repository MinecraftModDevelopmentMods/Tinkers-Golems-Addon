package com.golems_tcon.event.handler;

import javax.annotation.Nullable;

import com.golems.entity.EntityStainedGlassGolem;
import com.golems.entity.GolemColorizedMultiTextured;
import com.golems.events.GolemBuildEvent;
import com.golems.main.Config;
import com.golems_tcon.entity.EntityArditeGolem;
import com.golems_tcon.entity.EntityBrownstoneGolem;
import com.golems_tcon.entity.EntityCGlassGolem;
import com.golems_tcon.entity.EntityCobaltGolem;
import com.golems_tcon.entity.EntityCongealSlimeGolem;
import com.golems_tcon.entity.EntityFirewoodGolem;
import com.golems_tcon.entity.EntityKnightSlimeGolem;
import com.golems_tcon.entity.EntityManyullynGolem;
import com.golems_tcon.entity.EntityPigironGolem;
import com.golems_tcon.entity.EntitySearedGolem;
import com.golems_tcon.entity.EntitySilkyGolem;
import com.golems_tcon.init.TGConfig;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.shared.TinkerCommons;

public class TconCommonEventHandler 
{
	@SubscribeEvent
	public void onGolemBuild(GolemBuildEvent event)
	{
		if(event.isGolemNull())
		{		
			ItemStack golemStack = new ItemStack(event.blockBelow, 1, event.blockBelow.getMetaFromState(event.blockState));
			if(event.areBlocksSameMeta)
			{
				if(ItemStack.areItemsEqual(golemStack, TinkerCommons.blockCobalt))
				{
					event.setGolem(new EntityCobaltGolem(event.worldObj), TGConfig.COBALT.canSpawn());
				}
				else if(ItemStack.areItemsEqual(golemStack, TinkerCommons.blockArdite))
				{
					event.setGolem(new EntityArditeGolem(event.worldObj), TGConfig.ARDITE.canSpawn());
				}
				else if(ItemStack.areItemsEqual(golemStack, TinkerCommons.blockManyullyn))
				{
					event.setGolem(new EntityManyullynGolem(event.worldObj), TGConfig.MANYULLYN.canSpawn());
				}
				else if(ItemStack.areItemsEqual(golemStack, TinkerCommons.blockKnightSlime))
				{
					event.setGolem(new EntityKnightSlimeGolem(event.worldObj), TGConfig.KNIGHTSLIME.canSpawn());
				}
				else if(ItemStack.areItemsEqual(golemStack, TinkerCommons.blockPigIron))
				{
					event.setGolem(new EntityPigironGolem(event.worldObj), TGConfig.PIGIRON.canSpawn());
				}
				else if(ItemStack.areItemsEqual(golemStack, TinkerCommons.blockSilkyJewel))
				{
					event.setGolem(new EntitySilkyGolem(event.worldObj), TGConfig.SILKY.canSpawn());
				}
				else if(ItemStack.areItemsEqual(golemStack, TinkerCommons.firewood))
				{
					event.setGolem(new EntityFirewoodGolem(event.worldObj), TGConfig.FIREWOOD.canSpawn());
				}
				else if(event.blockBelow == TinkerCommons.blockClearGlass)
				{
					event.setGolem(new EntityCGlassGolem(event.worldObj), TGConfig.CGLASS.canSpawn());
				}
			}
			
			Block brownstone = getTconBlock("brownstone");
			Block seared = getTconBlock("seared");
			Block slimeCongealed = getTconBlock("slime_congealed");
			
			if(brownstone != null && event.blockBelow == brownstone)
			{
				event.setGolem(new EntityBrownstoneGolem(event.worldObj), TGConfig.BROWNSTONE.canSpawn());
			}
			else if(seared != null && event.blockBelow == seared)
			{
				event.setGolem(new EntitySearedGolem(event.worldObj), TGConfig.SEARED.canSpawn());
			}
			else if(slimeCongealed != null && event.blockBelow == slimeCongealed)
			{
				event.setGolem(new EntityCongealSlimeGolem(event.worldObj), TGConfig.CONGEAL_SLIME.canSpawn());
			}
			else if(event.blockBelow == TinkerCommons.blockClearStainedGlass)
			{
				GolemColorizedMultiTextured golem = new EntityStainedGlassGolem(event.worldObj);
				// use block metadata to give this golem the right texture
				int meta = golemStack.getMetadata() % golem.getColorArray().length;
				golem.setTextureNum((byte)(golem.getColorArray().length - meta - 1));
				// actually set the golem
				event.setGolem(golem, Config.STAINED_GLASS.canSpawn());
			}
		}
	}
	
	@Nullable
	public static Block getTconBlock(String name)
	{
		Block found = Block.REGISTRY.getObject(new ResourceLocation("tconstruct", name));
		return found.equals(Blocks.AIR) ? null : found;
	}
	
	/*
	public static boolean matchesOreDict(Block b, String name, int meta)
	{
		if(OreDictionary.doesOreNameExist(name))
		{
			List<ItemStack> ores = OreDictionary.getOres(name);
			ItemStack blockStack = new ItemStack(b, 1, meta);
			for(ItemStack stack : ores)
			{
				if(OreDictionary.itemMatches(blockStack, stack, meta != OreDictionary.WILDCARD_VALUE))
				{
					//System.out.println("found ore dict match for " + name + " with meta " + meta);
					return true;
				}
			}
		}
		return false;
	}
	*/
}
