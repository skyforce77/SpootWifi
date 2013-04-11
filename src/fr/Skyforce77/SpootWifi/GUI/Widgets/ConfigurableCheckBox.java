package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericCheckBox;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ConfigurableCheckBox extends GenericCheckBox{

	SWStorage storage;
	String label;
	
	public ConfigurableCheckBox(SWStorage storage, String label) {
		this.storage = storage;
		this.label = label;
		this.setChecked(storage.getInteger(label) == 1);
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		Player p = event.getPlayer();
		if(this.isChecked())
		{
			storage.addInteger(label, 1);
			storage.sync(p);
		}
		else
		{
			storage.addInteger(label, 0);
			storage.sync(p);
		}
		super.onButtonClick(event);
	}

}
