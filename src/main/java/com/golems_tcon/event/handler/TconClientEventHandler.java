package com.golems_tcon.event.handler;

import com.golems.events.GolemPaperAddInfoEvent;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TconClientEventHandler 
{
	@SubscribeEvent
	public void onGolemPaperEvent(GolemPaperAddInfoEvent event)
	{
		String header = TextFormatting.WHITE + trans("tooltip.tconstruct") + ":";
		String C = ", ";
		event.infoList.add(header);
		event.infoList.add(
				trans("tile.tconstruct.metal.cobalt.name") + C + trans("tile.tconstruct.metal.ardite.name") + C
				+ trans("tile.tconstruct.metal.manyullyn.name") + C + trans("tile.tconstruct.metal.knightslime.name") + C
				+ trans("tile.tconstruct.metal.pigiron.name") + C + trans("tile.tconstruct.metal.silky_jewel.name") + C
				+ trans("tooltip.congealed_slime") + C + trans("tile.tconstruct.seared.brick.name") + C
				+ trans("tile.tconstruct.firewood.firewood.name") + C + trans("tile.tconstruct.clear_glass.name") + C
				+ trans("tile.tconstruct.brownstone.brick.name"));
	}
	
	private static String trans(String in, Object... strings)
	{
		return I18n.format(in, strings);
	}
}
