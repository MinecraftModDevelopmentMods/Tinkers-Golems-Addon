package com.golems_tcon.init;

import com.golems.util.GolemConfigSet;
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

import net.minecraftforge.common.config.Configuration;

public class TGConfig {
	
	public static void mainRegistry(Configuration config) {
		config.load();
		
		GolemLookup.addConfig(EntityArditeGolem.class,
				new GolemConfigSet(config, "Ardite Golem", 224.0D, 19.0F));
		GolemLookup.addConfig(EntityBrownstoneGolem.class,
				new GolemConfigSet(config, "Brownstone Golem", 38.0D, 4.5F));
		GolemLookup.addConfig(EntityCGlassGolem.class,
				new GolemConfigSet(config, "Clear Glass Golem", 12.0D, 11.0F));
		GolemLookup.addConfig(EntityCobaltGolem.class,
				new GolemConfigSet(config, "Cobalt Golem", 228.0D, 18.0F));
		GolemLookup.addConfig(EntityCongealedSlimeGolem.class,
				new GolemConfigSet(config, "Congealed Slime Golem", 30.0D, 3.5F)
				.addKey(EntityCongealedSlimeGolem.ALLOW_SPECIAL, true, "Whether this golem can use extra-knockback attack")
				.addKey(EntityCongealedSlimeGolem.KNOCKBACK_FACTOR, 2.0012F, 0.01F, 10.0F, "Higher values = more powerful knockback"));
		GolemLookup.addConfig(EntityFirewoodGolem.class,
				new GolemConfigSet(config, "Firewood Golem", 20.0D, 6.0F)
				.addKey(EntityFirewoodGolem.ALLOW_SPECIAL, true, "Whether this golem can light creatures on fire"));
		GolemLookup.addConfig(EntityKnightSlimeGolem.class,
				new GolemConfigSet(config, "Knightslime Golem", 228.0D, 18.0F));
		GolemLookup.addConfig(EntityManyullynGolem.class,
				new GolemConfigSet(config, "Manyullyn Golem", 300.0D, 22.0F));
		GolemLookup.addConfig(EntityPigironGolem.class,
				new GolemConfigSet(config, "Pigiron Golem", 164.0D, 10.5F)
				.addKey(EntityPigironGolem.BACON_CHANCE, 14, 0, 100, "Percent chance to drop bacon while attacking"));
		GolemLookup.addConfig(EntitySearedGolem.class,
				new GolemConfigSet(config, "Seared Golem", 48.0D, 6.0F));
		GolemLookup.addConfig(EntitySilkyGolem.class,
				new GolemConfigSet(config, "Silky Golem", 198.0D, 18.0F));
		
		config.save();
	}
}
