package fr.Skyforce77.SpootWifi.WifiPackets.Events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class PacketReceiveEvent extends Event implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	boolean cancelled = false;
	WifiPacket packet;
	Channel channel;
	PacketOperator sender;
	PacketOperator receiver;
	
	public PacketReceiveEvent(PacketSendEvent send, PacketOperator receiver) {
		this.receiver = receiver;
		sender = send.getSender();
		packet = send.getPacket();
		channel = send.getChannel();
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
	
	public PacketOperator getReceiver() {
		return receiver;
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
