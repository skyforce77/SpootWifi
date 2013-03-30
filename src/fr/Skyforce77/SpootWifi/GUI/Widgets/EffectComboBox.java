package fr.Skyforce77.SpootWifi.GUI.Widgets;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Saves.Channel;

public class EffectComboBox extends GenericComboBox{

	Block b;
	SpoutPlayer p;
	
	public EffectComboBox(Block b, SpoutPlayer p)
	{
		this.b = b;
		this.p = p;
		
		ArrayList<String> items = new ArrayList<String>();
		for(Effect effect : Effect.values())
		{
			items.add(effect.toString());
		}
		this.setItems(items);
		
		try
		{
			Channel c = SpootWifi.save.getChannel(b);
			setSelection(items.indexOf(c.getSWBlock(b).getStorage().getString("Effect")));
		} catch(Exception e){}
	}
	
	@Override
	public void onSelectionChanged(int i, String text) {
		try
		{
			Effect effect = Effect.valueOf(text);
			if(effect != null)
			{
				SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addString("Effect", effect.toString());
				p.sendNotification("Effect set", text, Material.REDSTONE);
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
