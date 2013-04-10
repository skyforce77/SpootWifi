package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class NotificationChooseButton extends GenericButton{

	SWStorage storage;
	Player p;
	GenericTextField title;
	GenericTextField text;
	
	public NotificationChooseButton(Player p, SWStorage storage, GenericTextField title, GenericTextField text)
	{
		this.p = p;
		this.storage = storage;
		this.title = title;
		this.text = text;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		storage.addString("title", title.getText());
		storage.addString("text", text.getText());
		
		storage.sync(p);
		event.getPlayer().sendNotification("Title and Text set", "", Material.PAPER);
		super.onButtonClick(event);
	}

}
