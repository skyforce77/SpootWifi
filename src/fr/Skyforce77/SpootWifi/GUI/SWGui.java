package fr.Skyforce77.SpootWifi.GUI;

import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.Widgets.ConfigurableCheckBox;
import fr.Skyforce77.SpootWifi.Materials.Basics.Configurable;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class SWGui extends GenericPopup{
	
	public SWGui(String title, SpoutPlayer sp, SWStorage storage) {
		if(storage.isItemStackStorage() && new SpoutItemStack(sp.getItemInHand()).getMaterial() instanceof Configurable) 
		{
			ConfigurableCheckBox check = new ConfigurableCheckBox(storage, "AutoChannel");
			check.setText("Auto Channel");
			check.setAuto(true);
			check.setWidth(check.getWidth()).setHeight(check.getHeight());
			this.attachWidgets(SpootWifi.plugin, check);
		}
	}

}
