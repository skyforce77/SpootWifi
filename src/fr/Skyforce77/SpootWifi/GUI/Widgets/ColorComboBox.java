package fr.Skyforce77.SpootWifi.GUI.Widgets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Extended.ColorTransmitter;
import fr.Skyforce77.SpootWifi.Saves.Channel;

public class ColorComboBox extends GenericComboBox{

	Block b;
	SpoutPlayer p;
	
	public ColorComboBox(Block b, SpoutPlayer p)
	{
		this.b = b;
		this.p = p;
		
		ArrayList<String> items = new ArrayList<String>();
		for(DyeColor color : DyeColor.values())
		{
			items.add(color.toString());
		}
		this.setItems(items);
		
		try
		{
			Channel c = SpootWifi.save.getChannel(b);
			setSelection(items.indexOf(DyeColor.getByDyeData(c.getSWBlock(b).getStorage().getByte("WoolColor")).toString()));
		} catch(Exception e){}
	}
	
	@Override
	public void onSelectionChanged(int i, String text) {
		try
		{
			DyeColor color = DyeColor.valueOf(text);
			if(color != null)
			{
				SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addByte("WoolColor", color.getDyeData());
				p.sendNotification("Color set", text, new SpoutItemStack(35, color.getDyeData()), 2000);
				
				if(((SpoutBlock)b).getCustomBlock() instanceof ColorTransmitter)
				{
					if(((SpoutBlock)b).getCustomBlockData() >= 16)
					{
						((SpoutBlock)b).setCustomBlockData((byte)(color.getDyeData()+16));
					}
					else
					{
						((SpoutBlock)b).setCustomBlockData(color.getDyeData());
					}
				}
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
