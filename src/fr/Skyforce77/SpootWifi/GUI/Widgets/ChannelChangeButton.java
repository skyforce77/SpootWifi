package fr.Skyforce77.SpootWifi.GUI.Widgets;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericTextField;

public class ChannelChangeButton extends GenericButton{

	private int change;
	GenericTextField field;
	
	public ChannelChangeButton(GenericTextField field, int change) {
		super();
		this.change = change;
		this.field = field;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		try
		{
			Integer i = Integer.parseInt(field.getText());
			field.setText((i+change)+"");
		}
		catch(Exception e)
		{
			 field.setText("0");
		}
	}


}
