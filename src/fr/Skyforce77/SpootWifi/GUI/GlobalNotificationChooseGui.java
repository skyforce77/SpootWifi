package fr.Skyforce77.SpootWifi.GUI;

import org.bukkit.block.Block;
import org.getspout.spoutapi.gui.Color;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericLabel;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.GenericTextField;
import org.getspout.spoutapi.gui.GenericTexture;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.WidgetAnchor;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.Widgets.NotificationChooseButton;

public class GlobalNotificationChooseGui extends GenericPopup {
	
	public GlobalNotificationChooseGui(SpoutPlayer sp, Block b)
	{
		
		GenericContainer container = new GenericContainer();
		container.setLayout(ContainerType.VERTICAL);
		
		GenericLabel label = new GenericLabel();
		if(SpootWifi.getOwner(b).equals("???"))
		{
			SpootWifi.setOwner(b, sp.getName());
		}
		label.setText("Owner: "+SpootWifi.getOwner(b));
		container.addChild(label);

		container.setAnchor(WidgetAnchor.CENTER_CENTER);
		container.setWidth(150);
		container.setHeight(100);
		container.setAlign(WidgetAnchor.CENTER_CENTER);
		container.setPriority(RenderPriority.Lowest);
		container.setX(container.getX()-container.getWidth()/2);
		container.setY(container.getY()-container.getHeight()/2);
		
		GenericTexture texture = new GenericTexture("https://dl.dropbox.com/u/38885163/plugins/moreblocks/plugin/gui.png");
		texture.setAnchor(WidgetAnchor.CENTER_CENTER);
		texture.setWidth(170);
		texture.setHeight(120);
		texture.setPriority(RenderPriority.High);
		texture.setX(-texture.getWidth()/2);
		texture.setY(-texture.getHeight()/2);
		
		GenericTextField title = new GenericTextField();
		title.setTooltip("Title");
		title.setText(SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getString("title"));
		title.setMaximumCharacters(26);
		title.setMaximumLines(1);
		title.setBorderColor(new Color(120,120,120));
		title.setColor(new Color(255,255,255));
		title.setFieldColor(new Color(142,142,142));
		
		GenericTextField text = new GenericTextField();
		text.setTooltip("Text");
		text.setText(SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getString("text"));
		text.setMaximumCharacters(26);
		text.setMaximumLines(1);
		text.setBorderColor(new Color(120,120,120));
		text.setColor(new Color(255,255,255));
		text.setFieldColor(new Color(142,142,142));
		
		container.addChildren(title, text, new NotificationChooseButton(b, title, text).setText("Apply Text and Title").setPriority(RenderPriority.Low));
		
		this.attachWidgets(SpootWifi.plugin, container, texture);
		this.setTransparent(true);
		this.setBgVisible(true);
		
		sp.getMainScreen().attachPopupScreen(this);
		
	}

}
