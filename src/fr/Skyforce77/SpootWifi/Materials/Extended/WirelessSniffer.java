package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.ChooseGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.ItemReceiver;
import fr.Skyforce77.SpootWifi.Saves.ItemSave;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;
import fr.Skyforce77.SpootWifi.Utils.RessourceManager;

public class WirelessSniffer extends ItemReceiver{

	public String prefixe;
	public WirelessSniffer(Plugin plugin, String name) {
		super(plugin, name, RessourceManager.getTexture("sniffer.png"));
		prefixe = ChatColor.GOLD+"Sniffer"+ChatColor.GREEN+" > ";
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block,
			BlockFace face) {
		if(player.isSneaking())
		{
			new ChooseGui(this.getName(), player, new SWStorage(player.getItemInHand()));
		}
		else
		{
			Integer i = ItemSave.getChannel(player.getItemInHand());
			if(SpootWifi.save.isChannelEmpty(i))
			{
				player.sendMessage(prefixe+"Channel "+i+" is empty.");
			}
			else
			{
				player.sendMessage(prefixe+"Channel "+i+" is used.");
			}
		}
		return false;
	}

}
