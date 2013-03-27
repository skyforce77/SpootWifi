package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.ItemChooseGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.ItemReceiver;
import fr.Skyforce77.SpootWifi.Saves.ItemSave;

public class WirelessSniffer extends ItemReceiver{

	public WirelessSniffer(Plugin plugin, String name) {
		super(plugin, name, "https://dl.dropbox.com/u/38885163/plugins/SpootWifi/sniffer.png");
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block,
			BlockFace face) {
		if(player.isSneaking())
		{
			new ItemChooseGui(this.getName(), player, player.getItemInHand());
		}
		else
		{
			Integer i = ItemSave.getChannel(player.getItemInHand());
			if(SpootWifi.save.isChannelEmpty(i))
			{
				player.sendMessage("Channel "+i+" is empty.");
			}
			else
			{
				player.sendMessage("Channel "+i+" is used.");
			}
		}
		return false;
	}

}
