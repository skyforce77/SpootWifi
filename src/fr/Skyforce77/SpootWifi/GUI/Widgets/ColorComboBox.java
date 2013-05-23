package fr.Skyforce77.SpootWifi.GUI.Widgets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ColorComboBox extends GenericComboBox{

	SWStorage storage;
	SpoutPlayer p;
	
	public ColorComboBox(SWStorage storage, SpoutPlayer p)
	{
		this.storage = storage;
		this.p = p;
		
		ArrayList<String> items = new ArrayList<String>();
		for(DyeColor color : DyeColor.values())
		{
			items.add(color.toString());
		}
		this.setItems(items);
		
		try
		{
			setSelection(items.indexOf(DyeColor.getByWoolData(storage.getByte("WoolColor")).toString()));
		} catch(Exception e){}
	}
	
	@Override
	public void onSelectionChanged(int i, String text) {
		try
		{
			DyeColor color = DyeColor.valueOf(text);
			if(color != null)
			{
				storage.addByte("WoolColor", color.getWoolData());
				p.sendNotification("Color set", text, new SpoutItemStack(35, color.getWoolData()), 2000);
				
				if(storage.isBlockStorage())
				{
					SpoutBlock sb = ((SpoutBlock)storage.getBlock());
					if(sb.getCustomBlockData() >= 16)
					{
						SpoutManager.getMaterialManager().overrideBlock(sb, sb.getCustomBlock(), (byte)(color.getWoolData()+16));
					}
					else
					{
						SpoutManager.getMaterialManager().overrideBlock(sb, sb.getCustomBlock(), color.getWoolData());
					}
				}
				
				storage.sync(p);
			}
			else
			{
				p.sendNotification("Color not set", ChatColor.RED+"ERROR", Material.WOOL);
			}
		}
		catch(Exception e)
		{
			p.sendNotification("Color not set", ChatColor.RED+"ERROR", Material.WOOL);
		}
		super.onSelectionChanged(i, text);
	}

}
