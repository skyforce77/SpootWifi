package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.SpootWifi;

public class MusicChooseButton extends GenericButton{

	Block b;
	GenericTextField music;
	
	public MusicChooseButton(Block b, GenericTextField music)
	{
		this.b = b;
		this.music = music;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		if(music.getText().endsWith(".ogg") || music.getText().endsWith(".mp3") || music.getText().endsWith(".wav"))
		{
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addString("MusicValue", music.getText());
			event.getPlayer().sendNotification("Music set", "", Material.RECORD_4);
		}
		else
		{
			event.getPlayer().sendNotification("Music not set", ChatColor.RED+"ERROR", Material.RECORD_4);
		}
		super.onButtonClick(event);
	}

}
