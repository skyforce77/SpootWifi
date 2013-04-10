package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.gui.GenericSlider;

import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class MusicSlider extends GenericSlider{

	SWStorage storage;
	Player p;
	
	public MusicSlider(Player p, SWStorage storage)
	{
		this.storage = storage;
	}
	
	@Override
	public void onSliderDrag(SliderDragEvent event) {
		storage.addFloat("MusicPower", event.getNewPosition());
		
		storage.sync(p);
		setText("Volume: "+((int)(getSliderPosition()*100))+"%");
		super.onSliderDrag(event);
	}

}
