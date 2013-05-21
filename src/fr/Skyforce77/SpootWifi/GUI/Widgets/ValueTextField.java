package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.gui.GenericTextField;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ValueTextField extends GenericTextField{

	Player p;
	SWStorage storage;
	String value;
	
	public ValueTextField(Player p , SWStorage storage, String value) {
		this.p = p;
		this.storage = storage;
		this.value = value;
		setText(storage.getString(value));
	}
	
	@Override
	public void onTextFieldChange(TextFieldChangeEvent event) {
		storage.addString(value, this.getText());
		storage.sync(p);
		super.onTextFieldChange(event);
	}

}
