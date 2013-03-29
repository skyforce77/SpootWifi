package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.gui.GenericSlider;

import fr.Skyforce77.SpootWifi.SpootWifi;

public class MusicSlider extends GenericSlider{

	SpoutBlock sb;
	
	public MusicSlider(SpoutBlock sb)
	{
		this.sb = sb;
	}
	
	@Override
	public void onSliderDrag(SliderDragEvent event) {
		SpootWifi.save.getChannel(sb).getSWBlock(sb).getStorage().addFloat("MusicPower", event.getNewPosition());
		setText(getSliderPosition()+"");
		super.onSliderDrag(event);
	}

}
