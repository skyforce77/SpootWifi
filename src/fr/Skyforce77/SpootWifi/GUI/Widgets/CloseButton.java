package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.GenericButton;

public class CloseButton extends GenericButton{

	public CloseButton() {
		this.setHoverColor(new Color(100,50,50));
		this.setText("X");
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		if(event.getButton().equals(this))
		{
			event.getPlayer().getMainScreen().closePopup();
		}
		super.onButtonClick(event);
	}

}
