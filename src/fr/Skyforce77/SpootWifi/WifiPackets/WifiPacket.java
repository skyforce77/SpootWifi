package fr.Skyforce77.SpootWifi.WifiPackets;

import org.bukkit.Bukkit;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketSendEvent;

public class WifiPacket {

	private SWStorage storage;
	private String type;
	
	public WifiPacket(String type)
	{
		this.storage = new SWStorage(this);
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
	
	public SWStorage getData()
	{
		return storage;
	}
	
	@Deprecated
	public void broadcast(int channel)
	{
		broadcast(SpootWifi.save.getChannel(channel), null);
	}
	
	@Deprecated
	public void broadcast(Channel channel)
	{
		broadcast(channel, null);
	}
	
	public void broadcast(int channel, PacketOperator sender)
	{
		broadcast(SpootWifi.save.getChannel(channel), sender);
	}
	
	public void broadcast(Channel channel, PacketOperator sender)
	{
		PacketSendEvent event = new PacketSendEvent(this, channel, sender);
		Bukkit.getPluginManager().callEvent(event);
		if(!event.isCancelled())
		{
			channel.forcedBroadcastPacket(event);
		}
	}

}
