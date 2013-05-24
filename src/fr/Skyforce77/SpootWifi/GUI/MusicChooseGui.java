package fr.Skyforce77.SpootWifi.GUI;

import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.Widgets.ChannelChangeButton;
import fr.Skyforce77.SpootWifi.GUI.Widgets.ChannelChooseButton;
import fr.Skyforce77.SpootWifi.GUI.Widgets.MusicChooseButton;
import fr.Skyforce77.SpootWifi.GUI.Widgets.MusicSlider;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class MusicChooseGui extends SWGui {
	
	public MusicChooseGui(String title, SpoutPlayer sp, SWStorage storage)
	{
		super(title,sp,storage);
		GenericContainer container = new GenericContainer();
		container.setLayout(ContainerType.VERTICAL);
		
		GenericLabel label = new GenericLabel();
		if(SpootWifi.getOwner(storage).equals("???"))
		{
			SpootWifi.setOwner(storage, sp.getName());
		}
		label.setText("Owner: "+SpootWifi.getOwner(storage));
		container.addChild(label);
		
		GenericContainer fieldc = new GenericContainer();
		fieldc.setLayout(ContainerType.HORIZONTAL);
		
		GenericContainer applyc = new GenericContainer();
		applyc.setLayout(ContainerType.HORIZONTAL);
		
		GenericTextField field = new GenericTextField();
		field.setTooltip("Channel");
		field.setText(""+storage.getInteger("SpootWifiChannel"));
		field.setMaximumCharacters(9);
		field.setPlaceholder("0123456789");
		field.setBorderColor(new Color(120,120,120));
		field.setColor(new Color(255,255,255));
		field.setFieldColor(new Color(142,142,142));
		
		ChannelChangeButton mdix = new ChannelChangeButton(field, -10);
		mdix.setText("<");
		ChannelChangeButton pdix = new ChannelChangeButton(field, 10);
		pdix.setText(">");
		ChannelChangeButton mcent = new ChannelChangeButton(field, -100);
		mcent.setText("<<");
		ChannelChangeButton pcent = new ChannelChangeButton(field, 100);
		pcent.setText(">>");
		
		fieldc.addChildren(mdix,field,pdix);
		container.addChild(fieldc);
		
		applyc.addChildren(mcent, new ChannelChooseButton(storage, field).setText("Apply"), pcent);
		container.addChild(applyc);

		container.setAnchor(WidgetAnchor.CENTER_CENTER);
		container.setWidth(150);
		container.setHeight(150);
		container.setAlign(WidgetAnchor.CENTER_CENTER);
		container.setPriority(RenderPriority.Lowest);
		container.setX(container.getX()-container.getWidth()/2);
		container.setY(container.getY()-container.getHeight()/2);
		
		GenericTexture texture = new GenericTexture("http://dl.dropbox.com/u/38885163/plugins/moreblocks/plugin/gui.png");
		texture.setAnchor(WidgetAnchor.CENTER_CENTER);
		texture.setWidth(170);
		texture.setHeight(170);
		texture.setPriority(RenderPriority.High);
		texture.setX(-texture.getWidth()/2);
		texture.setY(-texture.getHeight()/2);
		
		GenericTextField music = new GenericTextField();
		music.setTooltip("Music url");
		music.setText(storage.getString("MusicValue"));
		music.setMaximumCharacters(900);
		music.setMaximumLines(900);
		music.setBorderColor(new Color(120,120,120));
		music.setColor(new Color(255,255,255));
		music.setFieldColor(new Color(142,142,142));
		
		MusicSlider slider = new MusicSlider(sp, storage);
		slider.setSliderPosition(storage.getFloat("MusicPower"));
		slider.setText("Volume: "+((int)(slider.getSliderPosition()*100))+"%");
		slider.setPriority(RenderPriority.Low);
		
		container.addChildren(music, slider,new MusicChooseButton(sp, storage, music).setText("Apply Music").setPriority(RenderPriority.Low));
		
		this.attachWidgets(SpootWifi.plugin, container, texture);
		this.setTransparent(true);
		this.setBgVisible(true);
		
		sp.getMainScreen().attachPopupScreen(this);
		
	}

}
