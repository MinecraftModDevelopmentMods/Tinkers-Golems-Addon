package com.golems_tcon.init;

import com.golems.integration.ModIds;
import com.golems_tcon.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TconGolems.MODID, name = TconGolems.NAME, version = TconGolems.VERSION, dependencies = TconGolems.DEPS)
public class TconGolems 
{
	public static final String MODID = "golems_tcon";
	public static final String NAME = "Tinkers' Construct Golems";
	public static final String VERSION = "6.02.1";
	public static final String DEPS = "required-after:golems@[6.02,);required-after:tconstruct";
	
	@SidedProxy(clientSide = "com." + MODID + ".proxy.ClientProxy", serverSide = "com." + MODID + ".proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static TconGolems instance;
	
	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		TGConfig.mainRegistry(new Configuration(event.getSuggestedConfigurationFile()));
		proxy.preInitRenders();
		proxy.registerEntities();
	}
	
	@Mod.EventHandler
	public static void init(FMLInitializationEvent event)
	{
		proxy.registerEvents();
		
		if(Loader.isModLoaded(ModIds.WAILA) && TGConfig.enableWaila)
		{
			FMLInterModComms.sendMessage(ModIds.WAILA, "register", "com.golems_tcon.integration.waila.WailaTconGolems.callbackRegister");
		}
		if(Loader.isModLoaded(ModIds.TOP))
		{
			FMLInterModComms.sendFunctionMessage(ModIds.TOP, "getTheOneProbe", "com.golems_tcon.integration.theoneprobe.TOPTconGolems$GetTheOneProbe");
		}
	}
}
