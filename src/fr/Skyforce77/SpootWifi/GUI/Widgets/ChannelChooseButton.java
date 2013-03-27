package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Saves.ItemSave;

public class ChannelChooseButton extends GenericButton{

	Block b = null;
	GenericTextField field;
	
	public ChannelChooseButton(Block b, GenericTextField field)
	{
		this.b = b;
		this.field = field;
	}
	
	public ChannelChooseButton(GenericTextField field)
	{
		this.field = field;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		try
		{
			Integer i = Integer.parseInt(field.getText());
			if(b != null)
			{
				SpootWifi.save.changeChannel(b, i);
				event.getPlayer().sendNotification("Channel set", ""+i, Material.COMPASS);
			}
			else
			{
				event.getPlayer().setItemInHand(ItemSave.setChannel(event.getPlayer().getItemInHand(), i));
				event.getPlayer().sendNotification("Channel set", ""+i, Material.COMPASS);
			}
		}
		catch(Exception e)
		{
			event.getPlayer().sendNotification("Channel not set", ChatColor.RED+"ERROR", Material.COMPASS);
		}
		super.onButtonClick(event);
	}

}
