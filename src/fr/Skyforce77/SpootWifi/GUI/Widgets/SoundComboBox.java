package fr.Skyforce77.SpootWifi.GUI.Widgets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class SoundComboBox extends GenericComboBox{

	SWStorage storage;
	SpoutPlayer p;
	
	public SoundComboBox(SWStorage storage, SpoutPlayer p)
	{
		this.storage = storage;
		this.p = p;
		
		ArrayList<String> items = new ArrayList<String>();
		for(Sound sound : Sound.values())
		{
			items.add(sound.toString());
		}
		this.setItems(items);
		
		try
		{
			setSelection(items.indexOf(storage.getString("Sound")));
		} catch(Exception e){}
	}
	
	@Override
	public void onSelectionChanged(int i, String text) {
		try
		{
			Sound sound = Sound.valueOf(text);
			if(sound != null)
			{
				storage.addString("Sound", sound.toString());
				p.sendNotification("Sound set", text, Material.FEATHER);
				
				storage.sync(p);
			}
			else
			{
				p.sendNotification("Sound not set", ChatColor.RED+"ERROR", Material.FEATHER);
			}
		}
		catch(Exception e)
		{
			p.sendNotification("Sound not set", ChatColor.RED+"ERROR", Material.FEATHER);
		}
		super.onSelectionChanged(i, text);
	}

}
