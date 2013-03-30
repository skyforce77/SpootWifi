package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.SpootWifi;

public class EffectReceiverButton extends GenericButton{

	Block b;
	GenericTextField x;
	GenericTextField y;
	GenericTextField z;
	GenericTextField data;
	
	public EffectReceiverButton(Block b, GenericTextField x, GenericTextField y, GenericTextField z, GenericTextField data)
	{
		this.b = b;
		this.x = x;
		this.y = y;
		this.z = z;
		this.data = data;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		try
		{
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addInteger("x", Integer.parseInt(x.getText()));
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addInteger("y", Integer.parseInt(y.getText()));
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addInteger("z", Integer.parseInt(z.getText()));
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addInteger("data", Integer.parseInt(data.getText()));
			event.getPlayer().sendNotification("Location set", "", Material.REDSTONE);
		}
		catch(Exception e)
		{
			event.getPlayer().sendNotification(ChatColor.RED+"Error", "", Material.REDSTONE);
		}
		super.onButtonClick(event);
	}

}
