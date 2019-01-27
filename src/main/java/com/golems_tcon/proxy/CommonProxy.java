package com.golems_tcon.proxy;

import javax.annotation.Nullable;

import com.golems.entity.EntityStainedGlassGolem;
import com.golems.entity.GolemBase;
import com.golems.util.GolemLookup;
import com.golems_tcon.entity.EntityArditeGolem;
import com.golems_tcon.entity.EntityBrownstoneGolem;
import com.golems_tcon.entity.EntityCGlassGolem;
import com.golems_tcon.entity.EntityCobaltGolem;
import com.golems_tcon.entity.EntityCongealedSlimeGolem;
import com.golems_tcon.entity.EntityFirewoodGolem;
import com.golems_tcon.entity.EntityKnightSlimeGolem;
import com.golems_tcon.entity.EntityManyullynGolem;
import com.golems_tcon.entity.EntityPigironGolem;
import com.golems_tcon.entity.EntitySearedGolem;
import com.golems_tcon.entity.EntitySilkyGolem;
import com.golems_tcon.init.TconGolems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import slimeknights.tconstruct.shared.TinkerCommons;

public class CommonProxy  {
	
	private static int golemCount;
	
	public void preInitRenders() { }
	
	public void registerEntities() {
		golemCount = 0;
		
		register(EntityArditeGolem.class, "golem_ardite", null);
		register(EntityBrownstoneGolem.class, "golem_brownstone", getTconBlock("brownstone"));
		register(EntityCGlassGolem.class, "golem_clear_glass", TinkerCommons.blockClearGlass);
		register(EntityCobaltGolem.class, "golem_cobalt", TinkerCommons.blockMetal);
		register(EntityCongealedSlimeGolem.class, "golem_congealedslime", TinkerCommons.blockSlimeCongealed);
		register(EntityFirewoodGolem.class, "golem_firewood", TinkerCommons.blockFirewood);
		register(EntityKnightSlimeGolem.class, "golem_knightslime", null);
		register(EntityManyullynGolem.class, "golem_manyullyn", null);
		register(EntityPigironGolem.class, "golem_pigiron", null);
		register(EntitySearedGolem.class, "golem_seared", getTconBlock("seared"));
		register(EntitySilkyGolem.class, "golem_silky", null);
		
		GolemLookup.addBlockAlias(TinkerCommons.blockClearStainedGlass, EntityStainedGlassGolem.class);		
	}
	
	/** registers the entity with an optional loot table. **/
	protected static void register(final Class<? extends GolemBase> entityClass, final String name, 
			final Block buildingBlock, final boolean registerLootTable) {
		// register the entity with Forge
		EntityRegistry.registerModEntity(
				new ResourceLocation(TconGolems.MODID, name), entityClass, 
				TconGolems.MODID + "." + name, ++golemCount,
				TconGolems.instance, 16 * 4, 3, true);
		// register building block
		GolemLookup.addGolem(entityClass, buildingBlock);
		// register loot table
		if(registerLootTable) {
			registerLootTable(name);
		}
	}
	
	/** registers the entity with a loot table. **/
	protected static void register(final Class<? extends GolemBase> entityClass, final String name, final Block block) {
		register(entityClass, name, block, true);
	}
	
	protected static void registerLootTable(final String name) {
		LootTableList.register(new ResourceLocation(TconGolems.MODID, "entities/" + name));
	}
	
	@Nullable
	public static Block getTconBlock(String name) {
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
