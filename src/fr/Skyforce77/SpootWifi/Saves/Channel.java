package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
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
			update(b.getWorld());
		}
	}
	
	public void addTransmitter(Block b)
	{
		if(!hasTransmitter(b))
		{
			transmitters.add(new SWBlock(b));
			update(b.getWorld());
		}
	}
	
	public void addReceiver(SWBlock b)
	{
		if(!hasReceiver(b.getBlock()))
		{
			receivers.add(b);
			update(b.getBlock().getWorld());
		}
	}
	
	public void addTransmitter(SWBlock b)
	{
		if(!hasTransmitter(b.getBlock()))
		{
			transmitters.add(b);
			update(b.getBlock().getWorld());
		}
	}
	
	public void removeReceiver(Block b)
	{
		if(hasReceiver(b))
		{
			receivers.remove(new SWBlock(b));
			update(b.getWorld());
		}
	}
	
	public void removeTransmitter(Block b)
	{
		if(hasTransmitter(b))
		{
			transmitters.remove(new SWBlock(b));
			update(b.getWorld());
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
	
	public void update(World w)
	{
		if(!SpootWifi.plugin.getConfig().getBoolean("wifi_through_worlds")) {
			updateWorld(w);
			return;
		}
		
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
	
	public void updateWorld(World w)
	{
		boolean active = isActive(w);
		for(SWBlock b : receivers)
		{
			if(b.getBlock().getLocation().getWorld().getName().equals(w.getName()))
			{
				SpootWifi.setPoweredBlock((SpoutBlock)b.getBlock(), active);
			}
		}
		
		for(Player sp : Bukkit.getServer().getOnlinePlayers())
		{
			if(sp.getLocation().getWorld().getName().equals(w.getName()))
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
	}	
	
	public void forcedBroadcastPacket(PacketSendEvent event)
	{
		if(!SpootWifi.plugin.getConfig().getBoolean("wifi_through_worlds")) {
			forcedBroadcastPacketWorld(event);
			return;
		}
		
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
			boolean send = false;
			for(ItemStack is : sp.getInventory().getContents())
			{
				if(is != null)
				{
					SpoutItemStack sis = new SpoutItemStack(is);
					if(sis.isCustomItem() && sis.getMaterial() instanceof ItemReceiver && ItemSave.getChannel(is) == this.channel)
					{
						send = true;
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
					if(SpootWifi.plugin.getConfig().getBoolean("timedpackets"))
					{
						Bukkit.getScheduler().runTaskLater(SpootWifi.plugin, sending, sending.getSendTime());
					}
					else
					{
						sending.run();
					}
				}
			}
		}
	}
	
	public void forcedBroadcastPacketWorld(PacketSendEvent event)
	{
		for(SWBlock b : receivers)
		{
			SpoutBlock sb = (SpoutBlock)b.getBlock();
			
			if(sb.getCustomBlock() != null && sb.getLocation().getWorld().getName().equals(event.getSender().getLocation().getWorld().getName()))
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
			boolean send = false;
			for(ItemStack is : sp.getInventory().getContents())
			{
				if(is != null)
				{
					SpoutItemStack sis = new SpoutItemStack(is);
					if(sis.isCustomItem() && sis.getMaterial() instanceof ItemReceiver && ItemSave.getChannel(is) == this.channel)
					{
						send = true;
					}
				}
			}
			
			if(!sp.getLocation().getWorld().getName().equals(event.getSender().getLocation().getWorld().getName())) {
				send = false;
			}
			
			if(send)
			{
				PacketReceiveEvent receive = new PacketReceiveEvent(event, new PacketOperator(sp));
				Bukkit.getPluginManager().callEvent(receive);
				if(!receive.isCancelled())
				{
					SendingPacket sending = new SendingPacket(event, new PacketOperator(sp));
					if(SpootWifi.plugin.getConfig().getBoolean("timedpackets"))
					{
						Bukkit.getScheduler().runTaskLater(SpootWifi.plugin, sending, sending.getSendTime());
					}
					else
					{
						sending.run();
					}
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
	
	public boolean isActive(World w)
	{
		boolean active = false;
		for(SWBlock b : transmitters)
		{
			if(SpootWifi.isPowered(b.getBlock()) && b.getBlock().getWorld().getName().equals(w.getName()))
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
