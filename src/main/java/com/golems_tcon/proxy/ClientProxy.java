package com.golems_tcon.proxy;

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
import com.golems_tcon.event.handler.TconClientEventHandler;

import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void preInitRenders() 
	{ 
		super.preInitRenders();
	}
	
	@Override
	public void registerEntities()
	{
		super.registerEntities();
		com.golems.proxies.ClientProxy.registerTextured(EntityArditeGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityBrownstoneGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityCGlassGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityCobaltGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityCongealSlimeGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityFirewoodGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityKnightSlimeGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityManyullynGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntityPigironGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntitySearedGolem.class);
		com.golems.proxies.ClientProxy.registerTextured(EntitySilkyGolem.class);
	}
	
	@Override
	public void registerEvents()
	{
		super.registerEvents();
		MinecraftForge.EVENT_BUS.register(new TconClientEventHandler());
	}
}
