package fr.Skyforce77.SpootWifi.Saves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.block.SpoutBlock;

import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;

public class Save implements Serializable{

	private static final long serialVersionUID = 4640681823774396092L;
	public HashMap<Integer, Channel> channels = new HashMap<Integer, Channel>();

	protected Save() {}

	public HashMap<Integer, Channel> getChannels() {
		return channels;
	}
	
	public boolean changeChannel(Block b, Integer newChannel)
	{
		if(newChannel != null)
		{
			if(hasChannel(b))
			{
				SWBlock block = getChannel(b).getSWBlock(b);
				rmvBlock(b);
				addBlock(newChannel,block);
			}
			else
			{
				rmvBlock(b);
				addBlock(newChannel,b);
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addTransmitter(Integer channel, Block b)
	{
		Channel chan = getChannel(channel);
		chan.addTransmitter(b);
	}
	
	public void addReceiver(Integer channel, Block b)
	{
		Channel chan = getChannel(channel);
		chan.addReceiver(b);
	}
	
	public void addTransmitter(Integer channel, Block b, Player p)
	{
		Channel chan = getChannel(channel);
		SWBlock sb = new SWBlock(b);
		chan.addTransmitter(sb);
		sb.getStorage().addString("OwnerName", p.getName());
		sb.getStorage().addInteger("SpootWifiChannel", channel);
	}
	
	public void addReceiver(Integer channel, Block b, Player p)
	{
		Channel chan = getChannel(channel);
		SWBlock sb = new SWBlock(b);
		chan.addReceiver(sb);
		sb.getStorage().addString("OwnerName", p.getName());
		sb.getStorage().addInteger("SpootWifiChannel", channel);
	}
	
	public void addTransmitter(Integer channel, SWBlock b)
	{
		Channel chan = getChannel(channel);
		chan.addTransmitter(b);
	}
	
	public void addReceiver(Integer channel, SWBlock b)
	{
		Channel chan = getChannel(channel);
		chan.addReceiver(b);
	}
	
	public void rmvTransmitter(Block b)
	{
		Channel chan = getChannel(b);
		if(chan.hasTransmitter(b))
		chan.removeTransmitter(b);
	}
	
	public void rmvReceiver(Block b)
	{
		Channel chan = getChannel(b);
		if(chan.hasReceiver(b))
		chan.removeReceiver(b);
	}
	
	public void rmvBlock(Block b)
	{
		if(((SpoutBlock)b).getCustomBlock() != null)
		{
			if(((SpoutBlock)b).getCustomBlock() instanceof Transmitter)
			{
				rmvTransmitter(b);
			}
			else if(((SpoutBlock)b).getCustomBlock() instanceof Receiver)
			{
				rmvReceiver(b);
			}
		}
	}
	
	public void addBlock(Integer i , Block b)
	{
		if(((SpoutBlock)b).getCustomBlock() != null)
		{
			if(((SpoutBlock)b).getCustomBlock() instanceof Transmitter)
			{
				addTransmitter(i,b);
			}
			else if(((SpoutBlock)b).getCustomBlock() instanceof Receiver)
			{
				addReceiver(i,b);
			}
		}
	}
	
	public void addBlock(Integer i , SWBlock b)
	{
		if(((SpoutBlock)b.getBlock()).getCustomBlock() != null)
		{
			if(((SpoutBlock)b.getBlock()).getCustomBlock() instanceof Transmitter)
			{
				addTransmitter(i,b);
			}
			else if(((SpoutBlock)b.getBlock()).getCustomBlock() instanceof Receiver)
			{
				addReceiver(i,b);
			}
		}
	}
	
	public Integer getRawChannel(Block b)
	{
		Integer channel = 0;
		for(Integer i : channels.keySet())
		{
			Channel c = channels.get(i);
			for(SWBlock sw : c.getTransmitters())
			{
				Block bl = sw.getBlock();
				if(bl.equals(b))
				{
					channel = i;
				}
			}
			for(SWBlock sw : c.getReceivers())
			{
				Block bl = sw.getBlock();
				if(bl.equals(b))
				{
					channel = i;
				}
			}
		}
		return channel;
	}
	
	public boolean hasChannel(Block b)
	{
		boolean channel = false;
		for(Integer i : channels.keySet())
		{
			Channel c = channels.get(i);
			for(SWBlock sw : c.getTransmitters())
			{
				Block bl = sw.getBlock();
				if(bl.equals(b))
				{
					channel = true;
				}
			}
			for(SWBlock sw : c.getReceivers())
			{
				Block bl = sw.getBlock();
				if(bl.equals(b))
				{
					channel = true;
				}
			}
		}
		return channel;
	}
	
	private void createIfNotExist(Integer channel)
	{
		if(!channels.containsKey(channel))
		{
			channels.put(channel, new Channel(channel));
		}
	}
	
	public Channel getChannel(Integer channel)
	{
		createIfNotExist(channel);
		return channels.get(channel);
	}
	
	public Channel getChannel(Block b)
	{
		Integer i = getRawChannel(b);
		createIfNotExist(i);
		return channels.get(i);
	}
	
	public Boolean Serialize(String file)
	{
		try
		{
			new File(file).getParentFile().mkdirs();
			FileOutputStream fichier = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.flush();
			oos.close();
			return true;
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static Save Deserialize(String file)
	{
		try
		{
			FileInputStream fichier = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Save o = (Save)ois.readObject();
			ois.close();
			return o;
		}
		catch (Exception e) 
		{
			return new Save();
		}
	}
	
	public boolean isChannelEmpty(int channel)
	{
		if(!channels.containsKey(channel))
		{
			return true;
		}
		else
		{
			return channels.get(channel).isEmpty();
		}
	}

}
