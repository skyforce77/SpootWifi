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
import fr.Skyforce77.SpootWifi.GUI.Widgets.ChannelChangeButton;
import fr.Skyforce77.SpootWifi.GUI.Widgets.ChannelChooseButton;
import fr.Skyforce77.SpootWifi.GUI.Widgets.EffectReceiverButton;

public class EffectReceiverGui extends GenericPopup {
	
	public EffectReceiverGui(SpoutPlayer sp, Block b)
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
		
		GenericContainer fieldc = new GenericContainer();
		fieldc.setLayout(ContainerType.HORIZONTAL);
		
		GenericContainer applyc = new GenericContainer();
		applyc.setLayout(ContainerType.HORIZONTAL);
		
		GenericTextField field = new GenericTextField();
		field.setTooltip("Channel");
		field.setText(""+SpootWifi.save.getRawChannel(b));
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
		
		applyc.addChildren(mcent, new ChannelChooseButton(b, field).setText("Apply"), pcent);
		container.addChild(applyc);

		container.setAnchor(WidgetAnchor.CENTER_CENTER);
		container.setWidth(150);
		container.setHeight(180);
		container.setAlign(WidgetAnchor.CENTER_CENTER);
		container.setPriority(RenderPriority.Lowest);
		container.setX(container.getX()-container.getWidth()/2);
		container.setY(container.getY()-container.getHeight()/2);
		
		GenericTexture texture = new GenericTexture("https://dl.dropbox.com/u/38885163/plugins/moreblocks/plugin/gui.png");
		texture.setAnchor(WidgetAnchor.CENTER_CENTER);
		texture.setWidth(170);
		texture.setHeight(200);
		texture.setPriority(RenderPriority.High);
		texture.setX(-texture.getWidth()/2);
		texture.setY(-texture.getHeight()/2);
		
		GenericTextField x = new GenericTextField();
		x.setTooltip("X");
		x.setText(SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getInteger("x")+"");
		x.setMaximumCharacters(26);
		x.setMaximumLines(1);
		x.setBorderColor(new Color(120,120,120));
		x.setColor(new Color(255,255,255));
		x.setFieldColor(new Color(142,142,142));
		
		GenericTextField y = new GenericTextField();
		y.setTooltip("Y");
		y.setText(SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getInteger("y")+"");
		y.setMaximumCharacters(26);
		y.setMaximumLines(1);
		y.setBorderColor(new Color(120,120,120));
		y.setColor(new Color(255,255,255));
		y.setFieldColor(new Color(142,142,142));
		
		GenericTextField z = new GenericTextField();
		z.setTooltip("Z");
		z.setText(SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getInteger("z")+"");
		z.setMaximumCharacters(26);
		z.setMaximumLines(1);
		z.setBorderColor(new Color(120,120,120));
		z.setColor(new Color(255,255,255));
		z.setFieldColor(new Color(142,142,142));
		
		GenericTextField data = new GenericTextField();
		data.setTooltip("Data");
		data.setText(SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().getInteger("data")+"");
		data.setMaximumCharacters(26);
		data.setMaximumLines(1);
		data.setBorderColor(new Color(120,120,120));
		data.setColor(new Color(255,255,255));
		data.setFieldColor(new Color(142,142,142));
		
		container.addChildren(x,y,z,data, new EffectReceiverButton(b, x,y,z,data).setText("Apply Location").setPriority(RenderPriority.Low));
		
		this.attachWidgets(SpootWifi.plugin, container, texture);
		this.setTransparent(true);
		this.setBgVisible(true);
		
		sp.getMainScreen().attachPopupScreen(this);
		
	}

}
