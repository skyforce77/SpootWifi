package fr.Skyforce77.SpootWifi.Materials.Basics;

import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.GUI.ItemChooseGui;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class ItemReceiver extends GenericCustomItem{

	public ItemReceiver(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}

	public ItemReceiver(Plugin arg0, String arg1) {
		super(arg0, arg1);
	}

	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block,
			BlockFace face) {
		if(player.isSneaking())
		{
			new ItemChooseGui(this.getName(), player, player.getItemInHand());
		}
		return super.onItemInteract(player, block, face);
	}
	
	public void onPowered(SpoutPlayer p, ItemStack is, boolean powered) {}
	
	public void onPacketReceived(WifiPacket wp, ItemStack is, SpoutPlayer p) {}

}
