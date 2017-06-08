package com.golems_tcon.init;

import com.golems.util.GolemConfigSet;
import com.golems_tcon.entity.EntityCongealSlimeGolem;
import com.golems_tcon.entity.EntityFirewoodGolem;
import com.golems_tcon.entity.EntityPigironGolem;

import net.minecraftforge.common.config.Configuration;

public class TGConfig 
{
	public static GolemConfigSet ARDITE, BROWNSTONE, CGLASS, COBALT, CONGEAL_SLIME, FIREWOOD, KNIGHTSLIME, MANYULLYN, PIGIRON, SEARED, SILKY;
	
	public static boolean enableWaila;
	
	public static final String CATEGORY_TWEAKS = "tweaks";

	public static void mainRegistry(Configuration config)
	{
		config.load();
		enableWaila = config.getBoolean("Enable Waila", Configuration.CATEGORY_GENERAL, true, 
				"Set to true to trigger this mod's built-in Waila support");
		initConfigSets(config);
		addConfigKeys();
		
		config.save();
	}

	private static void initConfigSets(Configuration config) 
	{
		ARDITE = new GolemConfigSet(config, "Ardite Golem", 224.0D, 19.0F);
		BROWNSTONE = new GolemConfigSet(config, "Brownstone Golem", 38.0D, 4.5F);
		CGLASS = new GolemConfigSet(config, "Clear Glass Golem", 12.0D, 11.0F);
		COBALT = new GolemConfigSet(config, "Cobalt Golem", 228.0D, 18.0F);
		CONGEAL_SLIME = new GolemConfigSet(config, "Congealed Slime Golem", 30.0D, 3.5F);
		FIREWOOD = new GolemConfigSet(config, "Firewood Golem", 20.0D, 6.0F);
		KNIGHTSLIME = new GolemConfigSet(config, "Knightslime Golem", 228.0D, 18.0F);
		MANYULLYN = new GolemConfigSet(config, "Manyullyn Golem", 300.0D, 22.0F);
		PIGIRON = new GolemConfigSet(config, "Pigiron Golem", 164.0D, 10.5F);
		SEARED = new GolemConfigSet(config, "Seared Golem", 48.0D, 6.0F);
		SILKY = new GolemConfigSet(config, "Silky Golem", 198.0D, 18.0F);
	}
	
	private static void addConfigKeys()
	{
		CONGEAL_SLIME.addKey(EntityCongealSlimeGolem.ALLOW_SPECIAL, true, "Whether this golem can use extra-knockback attack");
		CONGEAL_SLIME.addKey(EntityCongealSlimeGolem.KNOCKBACK_FACTOR, 2.0012F, 0.01F, 10.0F, "Higher values = more powerful knockback");
		FIREWOOD.addKey(EntityFirewoodGolem.ALLOW_SPECIAL, true, "Whether this golem can light creatures on fire");
		PIGIRON.addKey(EntityPigironGolem.BACON_CHANCE, 14, 0, 100, "Percent chance to drop bacon while attacking");
	}
}
