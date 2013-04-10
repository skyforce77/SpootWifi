package fr.Skyforce77.SpootWifi.GUI.Widgets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class EffectComboBox extends GenericComboBox{

	SWStorage storage;
	SpoutPlayer p;
	
	public EffectComboBox(SWStorage storage, SpoutPlayer p)
	{
		this.storage = storage;
		this.p = p;
		
		ArrayList<String> items = new ArrayList<String>();
		for(Effect effect : Effect.values())
		{
			items.add(effect.toString());
		}
		this.setItems(items);
		
		try
		{
			setSelection(items.indexOf(storage.getString("Effect")));
		} catch(Exception e){}
	}
	
	@Override
	public void onSelectionChanged(int i, String text) {
		try
		{
			Effect effect = Effect.valueOf(text);
			if(effect != null)
			{
				storage.addString("Effect", effect.toString());
				p.sendNotification("Effect set", text, Material.REDSTONE);
				
				storage.sync(p);
			}
			else
			{
				p.sendNotification("Effect not set", ChatColor.RED+"ERROR", Material.REDSTONE);
			}
		}
		catch(Exception e)
		{
			p.sendNotification("Effect not set", ChatColor.RED+"ERROR", Material.REDSTONE);
		}
		super.onSelectionChanged(i, text);
	}

}
