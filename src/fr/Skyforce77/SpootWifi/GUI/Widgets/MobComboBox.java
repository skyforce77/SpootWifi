package fr.Skyforce77.SpootWifi.GUI.Widgets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class MobComboBox extends GenericComboBox{

	SWStorage storage;
	SpoutPlayer p;
	
	public MobComboBox(SWStorage storage, SpoutPlayer p)
	{
		this.storage = storage;
		this.p = p;
		
		ArrayList<String> items = new ArrayList<String>();
		for(EntityType type : EntityType.values())
		{
			if(type.isSpawnable() && type.isAlive())
			items.add(type.toString());
		}
		this.setItems(items);
		
		try
		{
			setSelection(items.indexOf(storage.getString("MobType")));
		} catch(Exception e){}
	}
	
	@Override
	public void onSelectionChanged(int i, String text) {
		try
		{
			EntityType type = EntityType.valueOf(text);
			if(type != null)
			{
				storage.addString("MobType", type.toString());
				p.sendNotification("Mob set", text, Material.MOB_SPAWNER);
				
				storage.sync(p);
			}
			else
			{
				p.sendNotification("Mob not set", ChatColor.RED+"ERROR", Material.MOB_SPAWNER);
			}
		}
		catch(Exception e)
		{
			p.sendNotification("Mob not set", ChatColor.RED+"ERROR", Material.MOB_SPAWNER);
		}
		super.onSelectionChanged(i, text);
	}

}
