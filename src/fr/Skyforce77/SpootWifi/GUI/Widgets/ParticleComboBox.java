package fr.Skyforce77.SpootWifi.GUI.Widgets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.Effects.ParticleType;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ParticleComboBox extends GenericComboBox{

	SWStorage storage;
	SpoutPlayer p;
	
	public ParticleComboBox(SWStorage storage, SpoutPlayer p)
	{
		this.storage = storage;
		this.p = p;
		setText("Particle Type");
		
		ArrayList<String> items = new ArrayList<String>();
		for(ParticleType type : ParticleType.values())
		{
			items.add(type.toString());
		}
		this.setItems(items);
		
		try
		{
			setSelection(items.indexOf(storage.getString("ParticleType")));
		} catch(Exception e){}
	}
	
	@Override
	public void onSelectionChanged(int i, String text) {
		try
		{
			ParticleType type = ParticleType.valueOf(text);
			if(type != null)
			{
				storage.addString("ParticleType", type.toString());
				p.sendNotification("ParticleType set", text, Material.REDSTONE);
				
				storage.sync(p);
			}
			else
			{
				p.sendNotification("ParticleType not set", ChatColor.RED+"ERROR", Material.REDSTONE);
			}
		}
		catch(Exception e)
		{
			p.sendNotification("ParticleType not set", ChatColor.RED+"ERROR", Material.REDSTONE);
		}
		super.onSelectionChanged(i, text);
	}

}
