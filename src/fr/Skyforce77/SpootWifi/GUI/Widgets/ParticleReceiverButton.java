package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ParticleReceiverButton extends GenericButton{

	SWStorage storage;
	GenericTextField x;
	GenericTextField y;
	GenericTextField z;
	Player p;
	
	public ParticleReceiverButton(Player p, SWStorage storage, GenericTextField x, GenericTextField y, GenericTextField z)
	{
		this.storage = storage;
		this.x = x;
		this.y = y;
		this.z = z;
		this.p = p;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		try
		{
			storage.addInteger("x", Integer.parseInt(x.getText()));
			storage.addInteger("y", Integer.parseInt(y.getText()));
			storage.addInteger("z", Integer.parseInt(z.getText()));
			
			storage.sync(p);
			event.getPlayer().sendNotification("Location set", "", Material.REDSTONE);
		}
		catch(Exception e)
		{
			event.getPlayer().sendNotification(ChatColor.RED+"Error", "", Material.REDSTONE);
		}
		super.onButtonClick(event);
	}

}
