package fr.Skyforce77.SpootWifi.WifiPackets.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class PacketSendEvent extends Event implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	boolean cancelled = false;
	WifiPacket packet;
	Channel channel;
	PacketOperator sender;
	
	public PacketSendEvent(WifiPacket packet, Channel channel, PacketOperator sender) {
		this.packet = packet;
		this.channel = channel;
		this.sender = sender;
	}
	
	public WifiPacket getPacket() {
		return packet;
	}
	
	public Channel getChannel(){
		return channel;
	}
	
	public PacketOperator getSender() {
		return sender;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {	
		cancelled = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
