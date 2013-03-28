package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericCheckBox;

import fr.Skyforce77.SpootWifi.Saves.ItemSave;

public class ConfigurableCheckBox extends GenericCheckBox{

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		Player p = event.getPlayer();
		if(this.isChecked())
		{
			p.setItemInHand(ItemSave.setOption(p.getItemInHand(), "AutoChannel", 1));
		}
		else
		{
			p.setItemInHand(ItemSave.setOption(p.getItemInHand(), "AutoChannel", 0));
		}
		super.onButtonClick(event);
	}

}
