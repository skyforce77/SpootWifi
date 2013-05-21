package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.gui.GenericSlider;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class ValueSlider extends GenericSlider{

	SWStorage storage;
	Player p;
	String label;
	
	public ValueSlider(Player p, SWStorage storage, String label)
	{
		this.storage = storage;
		this.label = label;
		setSliderPosition(storage.getFloat(label));
		setText(label+": "+((int)(getSliderPosition()*100))+"%");
	}
	
	@Override
	public void onSliderDrag(SliderDragEvent event) {
		storage.addFloat(label, event.getNewPosition());
		
		storage.sync(p);
		setText(label+": "+((int)(getSliderPosition()*100))+"%");
		super.onSliderDrag(event);
	}

}
