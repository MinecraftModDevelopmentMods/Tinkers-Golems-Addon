package com.golems_tcon.event.handler;

import com.golems.main.GolemItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.BlockFirewood;

public class TconCommonEventHandler {
	
	@SubscribeEvent
	public static void onBlockPlaced(BlockEvent.PlaceEvent event) {
		// this is required to intercept some behavior
		// with Tinkers Construct, which insists on using
		// BlockState Properties to differentiate VERY different blocks.
		// Basically, we need to make sure the golem's building
		// blocks are all the same metadata before allowing a golem
		// to be built and therefore use up the blocks
		
		if(event.getPlacedBlock().getBlock() == GolemItems.golemHead) {
			// make sure all blocks have same value
			final IBlockState stateBelow1 = event.getWorld().getBlockState(event.getPos().down(1));
			final IBlockState stateBelow2 = event.getWorld().getBlockState(event.getPos().down(2));
			// do this check for TCon's metal block and firewood/lavawood block
			if(stateBelow1.getBlock() == TinkerCommons.blockMetal || stateBelow1.getBlock() == TinkerCommons.blockFirewood) {
				// make sure the states are equal
				if(stateBelow1.equals(stateBelow2)) {
					final boolean flagX = isGolemAligned(event.getWorld(), event.getPos(), EnumFacing.EAST);
					final boolean flagZ = isGolemAligned(event.getWorld(), event.getPos(), EnumFacing.SOUTH);
					System.out.println("flagX = " + flagX + ", flagZ = " + flagZ);
					if(!(flagX || flagZ)) {
						// the blockstates were not equal
						// cancel the attempt
						event.setCanceled(true);
						return;
					}
					// only allow golem made from Firewood variant
					if(stateBelow1.getBlock() == TinkerCommons.blockFirewood 
							&& stateBelow1.getValue(BlockFirewood.TYPE) != BlockFirewood.FirewoodType.FIREWOOD) {
						event.setCanceled(true);
						return;
					}
				}
			}
		}
		
	}
	
	private static boolean isGolemAligned(final World world, final BlockPos headPos, EnumFacing dir) {
		final BlockPos pos1 = headPos.down(1).offset(dir, 1);
		final BlockPos pos2 = headPos.down(1).offset(dir.getOpposite(), 1);
		final IBlockState below = world.getBlockState(headPos.down(1));
		return world.getBlockState(pos1).equals(below)
			&& world.getBlockState(pos2).equals(below);
	}
}
