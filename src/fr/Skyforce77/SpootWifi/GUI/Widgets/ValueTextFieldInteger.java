package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ValueTextFieldInteger extends GenericTextField{

	Player p;
	SWStorage storage;
	String value;
	
	public ValueTextFieldInteger(Player p , SWStorage storage, String value) {
		this.p = p;
		this.storage = storage;
		this.value = value;
		setTooltip(value);
		setMaximumCharacters(6);
		setText(storage.getInteger(value)+"");
	}
	
	@Override
	public void onTextFieldChange(TextFieldChangeEvent event) {
		try {
			storage.addInteger(value, Integer.parseInt(this.getText()));
			storage.sync(p);
		} catch (Exception e) {
		}
		super.onTextFieldChange(event);
	}

}
