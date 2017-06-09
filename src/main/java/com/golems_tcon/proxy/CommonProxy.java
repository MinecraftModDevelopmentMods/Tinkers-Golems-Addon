package com.golems_tcon.proxy;

import com.golems.entity.GolemBase;
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
import com.golems_tcon.event.handler.TconCommonEventHandler;
import com.golems_tcon.init.TconGolems;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy 
{
	private static int golemCount;
	
	public void preInitRenders() { }
	
	public void registerEntities()
	{
		golemCount = 0;
		register(EntityArditeGolem.class, "golem_ardite");
		register(EntityBrownstoneGolem.class, "golem_brownstone");
		register(EntityCGlassGolem.class, "golem_clear_glass");
		register(EntityCobaltGolem.class, "golem_cobalt");
		register(EntityCongealSlimeGolem.class, "golem_congealslime");
		register(EntityFirewoodGolem.class, "golem_firewood");
		register(EntityKnightSlimeGolem.class, "golem_knightslime");
		register(EntityManyullynGolem.class, "golem_manyullyn");
		register(EntityPigironGolem.class, "golem_pigiron");
		register(EntitySearedGolem.class, "golem_seared");
		register(EntitySilkyGolem.class, "golem_silky");
	}
	
	public void registerEvents()
	{
		MinecraftForge.EVENT_BUS.register(new TconCommonEventHandler());
	}
	
	private void register(Class<? extends GolemBase> c, String name)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(name), c, name, ++golemCount, TconGolems.instance, 64, 3, true);
	}
}
