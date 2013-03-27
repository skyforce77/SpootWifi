package fr.Skyforce77.SpootWifi.WifiPackets;

import org.bukkit.inventory.ItemStack;

public class NotificationPacket extends WifiPacket{

	public NotificationPacket(String title, String text, ItemStack render, int time) {
		super("NotificationPacket");
		this.getData().addString("title", title);
		this.getData().addString("text", text);
		this.getData().addInteger("time", time);
		this.getData().addObject("render", render);
	}
	
	public String getTitle()
	{
		return this.getData().getString("title");
	}
	
	public String getText()
	{
		return this.getData().getString("text");
	}
	
	public int getTime()
	{
		return this.getData().getInteger("time");
	}
	
	public ItemStack getRender()
	{
		return (ItemStack)this.getData().getObject("render");
	}

}
