package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.SpootWifi;

public class NotificationChooseButton extends GenericButton{

	Block b;
	GenericTextField title;
	GenericTextField text;
	
	public NotificationChooseButton(Block b, GenericTextField title, GenericTextField text)
	{
		this.b = b;
		this.title = title;
		this.text = text;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event)
	{
		SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addString("title", title.getText());
		SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addString("text", text.getText());
		event.getPlayer().sendNotification("Title and Text set", "", Material.PAPER);
		super.onButtonClick(event);
	}

}
