package com.golems_tcon.integration;

import java.util.LinkedList;
import java.util.List;

import com.golems.entity.GolemBase;
import com.golems.integration.GolemDescriptionManager;
import com.golems_tcon.entity.EntityCongealSlimeGolem;
import com.golems_tcon.entity.EntityFirewoodGolem;
import com.golems_tcon.entity.EntityPigironGolem;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

public class TconGolemDescriptionManager extends GolemDescriptionManager
{
	public TconGolemDescriptionManager()
	{
		super();
	}
	
	public List<String> getEntityDescription(GolemBase golem)
	{
		List<String> list = new LinkedList();
		if(this.showSpecial)
		{
			if(golem.getClass() == EntityFirewoodGolem.class)
			{
				String sBurn = TextFormatting.RED + trans("entitytip.lights_mobs");
				list.add(sBurn);
			}
			else if(golem.getClass() == EntityPigironGolem.class)
			{
				String sTasty = TextFormatting.LIGHT_PURPLE + trans("modifier.baconlicious.name");
				list.add(sTasty);
			}
			else if(golem.getClass() == EntityCongealSlimeGolem.class)
			{
				TextFormatting format = EntityCongealSlimeGolem.EnumSlimeType.get(((EntityCongealSlimeGolem)golem).getTextureNum()).getColor();
				String sKnockback = format + trans("entitytip.has_knockback");
				list.add(sKnockback);
			}
		}

		return list;
	}
	
	protected String trans(String in, Object... strings)
	{
		return I18n.format(in, strings);
	}
}
