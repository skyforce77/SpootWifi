package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ParticleTransmitterButton extends GenericButton{

	SWStorage storage;
	GenericTextField amount;
	GenericTextField data;
	Player p;
	
	public ParticleTransmitterButton(Player p, SWStorage storage, GenericTextField amount, GenericTextField data)
	{
		this.storage = storage;
		this.amount = amount;
		this.data = data;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		try
		{
			storage.addInteger("ParticleAmount", Integer.parseInt(amount.getText()));
			storage.addInteger("ParticleData", Integer.parseInt(data.getText()));
			
			storage.sync(p);
			event.getPlayer().sendNotification("Particle values set", "", Material.REDSTONE);
		}
		catch(Exception e)
		{
			event.getPlayer().sendNotification(ChatColor.RED+"Error", "", Material.REDSTONE);
		}
		super.onButtonClick(event);
	}

}
