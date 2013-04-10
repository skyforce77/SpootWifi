package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class MusicChooseButton extends GenericButton{

	SWStorage storage;
	Player p;
	GenericTextField music;
	
	public MusicChooseButton(Player p, SWStorage storage, GenericTextField music)
	{
		this.p = p;
		this.storage = storage;
		this.music = music;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		if(music.getText().endsWith(".ogg") || music.getText().endsWith(".mp3") || music.getText().endsWith(".wav"))
		{
			storage.addString("MusicValue", music.getText());
			event.getPlayer().sendNotification("Music set", "", Material.RECORD_4);
			
			storage.sync(p);
		}
		else
		{
			event.getPlayer().sendNotification("Music not set", ChatColor.RED+"ERROR", Material.RECORD_4);
		}
		super.onButtonClick(event);
	}

}
