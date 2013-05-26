package fr.Skyforce77.SpootWifi.WifiPackets;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.Materials.Basics.ItemReceiver;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.Saves.ItemSave;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketSendEvent;

public class SendingPacket implements Runnable{

	PacketSendEvent event;
	PacketOperator receiver;
	
	public SendingPacket(PacketSendEvent event, PacketOperator receiver) {
		this.receiver = receiver;
		this.event = event;
	}
	
	public Long getSendTime() {
		Location send = event.getSender().getLocation();
		Location rec = receiver.getLocation();
		return ((long)send.distance(rec));
	}

	@Override
	public void run() {
		if(receiver.hasBlock())
		{
			SpoutBlock sb = (SpoutBlock)receiver.getBlock();
			if(sb.getCustomBlock() != null)
			{
				CustomBlock cb = sb.getCustomBlock();
				if(cb instanceof Receiver)
				{
					((Receiver)cb).onPacketReceived(event.getPacket(), sb);
				}
			}
		}
		
		if(receiver.hasPlayer())
		{
			SpoutPlayer sp = (SpoutPlayer)receiver.getPlayer();
			for(ItemStack is : sp.getInventory().getContents())
			{
				if(is != null)
				{
					SpoutItemStack sis = new SpoutItemStack(is);
					if(sis.isCustomItem() && sis.getMaterial() instanceof ItemReceiver && ItemSave.getChannel(is) == event.getChannel().parseInt())
					{
						ItemReceiver ir = (ItemReceiver)sis.getMaterial();
						ir.onPacketReceived(event.getPacket(), is, (SpoutPlayer)sp);
					}
				}
			}
		}
	}

}
