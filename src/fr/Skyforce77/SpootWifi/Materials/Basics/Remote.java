package fr.Skyforce77.SpootWifi.Materials.Basics;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.Saves.ItemSave;

public class Remote extends GenericCustomItem{

	public Remote(Plugin arg0, String arg1) {
		super(arg0, arg1);
	}
	
	public Remote(Plugin arg0, String arg1, String arg2) {
		super(arg0, arg1, arg2);
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, BlockFace face) {
		if(block != null && block.getCustomBlock() != null && block.getCustomBlock() instanceof Transmitter)
		{
			player.setItemInHand(ItemSave.setBlock(player.getItemInHand(), block));
			player.sendNotification("Block set", "", Material.STONE);
		}
		else if(ItemSave.getBlock(player.getItemInHand()) != null && ((SpoutBlock)ItemSave.getBlock(player.getItemInHand())).getCustomBlock() != null && ((SpoutBlock)ItemSave.getBlock(player.getItemInHand())).getCustomBlock() instanceof Transmitter)
		{
			SpoutBlock sb = (SpoutBlock)ItemSave.getBlock(player.getItemInHand());
			Transmitter t = (Transmitter)((SpoutBlock)ItemSave.getBlock(player.getItemInHand())).getCustomBlock();
			if(player.isSneaking())
			{
				player.setSneaking(false);
				t.onBlockInteract(sb.getWorld(), sb.getX(), sb.getY(), sb.getZ(), player);
			}
			else
			{
				t.onPowered(sb, true);
				t.onPowered(sb, false);
			}
		}
		return super.onItemInteract(player, block, face);
	}

}
