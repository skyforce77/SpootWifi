package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.ItemReceiver;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.WifiPackets.SendingPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketReceiveEvent;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketSendEvent;

public class Channel implements Serializable{

	private static final long serialVersionUID = 5191934158608013884L;
	private Integer channel;
	private ArrayList<SWBlock> receivers = new ArrayList<SWBlock>();
	private ArrayList<SWBlock> transmitters = new ArrayList<SWBlock>();
	
	public Channel(Integer channel){this.channel = channel;}

	public Boolean isEmpty()
	{
		return receivers.isEmpty() && transmitters.isEmpty();
	}
	
	public Boolean hasReceiver(Block b)
	{
		boolean has = false;
		for(SWBlock sw : getReceivers())
		{
			Block bl = sw.getBlock();
			if(bl.equals(b))
			{
				has = true;
			}
		}
		return has;
	}
	
	public Boolean hasTransmitter(Block b)
	{
		boolean has = false;
		for(SWBlock sw : getTransmitters())
		{
			Block bl = sw.getBlock();
			if(bl.equals(b))
			{
				has = true;
			}
		}
		return has;
	}
	
	public Boolean hasBlock(Block b)
	{
		boolean has = false;
		for(SWBlock sw : getBlocks())
		{
			Block bl = sw.getBlock();
			if(bl.equals(b))
			{
				has = true;
			}
		}
		return has;
	}
	
	public SWBlock getSWBlock(Block b)
	{
		for(SWBlock sw : getBlocks())
		{
			Block bl = sw.getBlock();
			if(bl.equals(b))
			{
				return sw;
			}
		}
		return null;
	}
	
	public void addReceiver(Block b)
	{
		if(!hasReceiver(b))
		{
			receivers.add(new SWBlock(b));
			update();
		}
	}
	
	public void addTransmitter(Block b)
	{
		if(!hasTransmitter(b))
		{
			transmitters.add(new SWBlock(b));
			update();
		}
	}
	
	public void addReceiver(SWBlock b)
	{
		if(!hasReceiver(b.getBlock()))
		{
			receivers.add(b);
			update();
		}
	}
	
	public void addTransmitter(SWBlock b)
	{
		if(!hasTransmitter(b.getBlock()))
		{
			transmitters.add(b);
			update();
		}
	}
	
	public void removeReceiver(Block b)
	{
		if(hasReceiver(b))
		{
			receivers.remove(new SWBlock(b));
			update();
		}
	}
	
	public void removeTransmitter(Block b)
	{
		if(hasTransmitter(b))
		{
			transmitters.remove(new SWBlock(b));
			update();
		}
	}
	
	public ArrayList<SWBlock> getReceivers()
	{
		return receivers;
	}
	
	public ArrayList<SWBlock> getTransmitters()
	{
		return transmitters;
	}
	
	public ArrayList<SWBlock> getBlocks()
	{
		ArrayList<SWBlock> blocks = new ArrayList<SWBlock>();
		blocks.addAll(getReceivers());
		blocks.addAll(getTransmitters());
		return blocks;
	}
	
	public void update()
	{
		boolean active = isActive();
		for(SWBlock b : receivers)
		{
			SpootWifi.setPoweredBlock((SpoutBlock)b.getBlock(), active);
		}
		
		for(Player sp : Bukkit.getServer().getOnlinePlayers())
		{
			for(ItemStack is : sp.getInventory().getContents())
			{
				if(is != null)
				{
					SpoutItemStack sis = new SpoutItemStack(is);
					if(sis.isCustomItem() && sis.getMaterial() instanceof ItemReceiver && ItemSave.getChannel(is).equals(this.channel))
					{
						ItemReceiver ir = (ItemReceiver)sis.getMaterial();
						ir.onPowered((SpoutPlayer)sp, is, active);
					}
				}
			}
		}
	}
	
	public void forcedBroadcastPacket(PacketSendEvent event)
	{
		for(SWBlock b : receivers)
		{
			SpoutBlock sb = (SpoutBlock)b.getBlock();
			if(sb.getCustomBlock() != null)
			{
				CustomBlock cb = sb.getCustomBlock();
				if(cb instanceof Receiver)
				{
					PacketReceiveEvent receive = new PacketReceiveEvent(event, new PacketOperator(sb));
					Bukkit.getPluginManager().callEvent(receive);
					if(!receive.isCancelled())
					{
						if(SpootWifi.plugin.getConfig().getBoolean("timedpackets"))
						{
							SendingPacket sending = new SendingPacket(event, new PacketOperator(sb));
							Bukkit.getScheduler().runTaskLater(SpootWifi.plugin, sending, sending.getSendTime());
						}
						else
						{
							((Receiver)cb).onPacketReceived(event.getPacket(), sb);
						}
					}
				}
			}
		}
		
		for(Player sp : Bukkit.getServer().getOnlinePlayers())
		{
			boolean send =  false;
			for(ItemStack is : sp.getInventory().getContents())
			{
				if(is != null)
				{
					SpoutItemStack sis = new SpoutItemStack(is);
					if(sis.isCustomItem() && sis.getMaterial() instanceof ItemReceiver && ItemSave.getChannel(is).equals(this.channel))
					{
						if(SpootWifi.plugin.getConfig().getBoolean("timedpackets"))
						{
							send = true;
						}
						else
						{
							((ItemReceiver)sis.getMaterial()).onPacketReceived(event.getPacket(), is, (SpoutPlayer)sp);
						}
					}
				}
			}
			
			if(send)
			{
				PacketReceiveEvent receive = new PacketReceiveEvent(event, new PacketOperator(sp));
				Bukkit.getPluginManager().callEvent(receive);
				if(!receive.isCancelled())
				{
					SendingPacket sending = new SendingPacket(event, new PacketOperator(sp));
					Bukkit.getScheduler().runTaskLater(SpootWifi.plugin, sending, sending.getSendTime());
				}
			}
		}
	}
	
	public boolean isActive()
	{
		boolean active = false;
		for(SWBlock b : transmitters)
		{
			if(SpootWifi.isPowered(b.getBlock()))
			{
				active = true;
			}
		}
		return active;
	}
	
	public Integer parseInt()
	{
		return channel;
	}

}
