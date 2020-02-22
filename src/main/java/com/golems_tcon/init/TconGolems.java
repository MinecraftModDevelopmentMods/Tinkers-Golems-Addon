package com.golems_tcon.init;

import com.golems_tcon.event.handler.TconCommonEventHandler;
import com.golems_tcon.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TconGolems.MODID, name = TconGolems.NAME, version = TconGolems.VERSION, dependencies = TconGolems.DEPS)
public class TconGolems {
	
	public static final String MODID = "golems_tcon";
	public static final String NAME = "Tinkers' Construct Golems";
	public static final String VERSION = "7.1.7-0";
	public static final String DEPS = "required-after:golems@[7.1.7,);required-after:tconstruct@[1.12.2-2.12.0.135,)";
	
	@SidedProxy(clientSide = "com." + MODID + ".proxy.ClientProxy", serverSide = "com." + MODID + ".proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static TconGolems instance;
	
	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		TGConfig.mainRegistry(new Configuration(event.getSuggestedConfigurationFile()));
		proxy.preInitRenders();
	}
	
	@Mod.EventHandler
	public static void init(FMLInitializationEvent event) {		
		proxy.registerEntities();
		MinecraftForge.EVENT_BUS.register(TconCommonEventHandler.class);
	}
}
