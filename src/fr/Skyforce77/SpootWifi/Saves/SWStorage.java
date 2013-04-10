package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

import net.minecraft.server.v1_5_R2.NBTBase;
import net.minecraft.server.v1_5_R2.NBTTagByte;
import net.minecraft.server.v1_5_R2.NBTTagCompound;
import net.minecraft.server.v1_5_R2.NBTTagDouble;
import net.minecraft.server.v1_5_R2.NBTTagFloat;
import net.minecraft.server.v1_5_R2.NBTTagInt;
import net.minecraft.server.v1_5_R2.NBTTagLong;
import net.minecraft.server.v1_5_R2.NBTTagString;

public class SWStorage implements Serializable{

	private static final long serialVersionUID = -7775936155165721980L;
	
	private HashMap<String, String> strings = new HashMap<String, String>();
	private HashMap<String, Object> objects = new HashMap<String, Object>();
	private HashMap<String, Integer> integers = new HashMap<String, Integer>();
	private HashMap<String, Long> longs = new HashMap<String, Long>();
	private HashMap<String, Double> doubles = new HashMap<String, Double>();
	private HashMap<String, Float> floats = new HashMap<String, Float>();
	private HashMap<String, Byte> bytes = new HashMap<String, Byte>();
	private HashMap<String, SWStorage> storages = new HashMap<String, SWStorage>();

	public SWStorage(WifiPacket packet) {}
		
	public SWStorage(Block b) {
		Location loc = b.getLocation();
		integers.put("type",0);
		doubles.put("x", loc.getX());
		doubles.put("y", loc.getY());
		doubles.put("z", loc.getZ());
		strings.put("world", b.getWorld().getName());
	};
	
	public SWStorage(NBTTagCompound nbt) {
		integers.put("type",1);
		for(Object o : nbt.c().toArray()) {
			NBTBase base = (NBTBase)o;
			String s = base.getName();
			if(base instanceof NBTTagByte) {
				bytes.put(s, ((NBTTagByte)base).data);
			} else if(base instanceof NBTTagString) {
				strings.put(s, ((NBTTagString)base).data);
			} else if(base instanceof NBTTagCompound) {
				storages.put(s, new SWStorage((NBTTagCompound)base));
			} else if(base instanceof NBTTagInt) {
				integers.put(s, ((NBTTagInt)base).data);
			} else if(base instanceof NBTTagLong) {
				longs.put(s, ((NBTTagLong)base).data);
			} else if(base instanceof NBTTagDouble) {
				doubles.put(s, ((NBTTagDouble)base).data);
			} else if(base instanceof NBTTagFloat) {
				floats.put(s, ((NBTTagFloat)base).data);
			}
		}
	};
	
	public void addObject(String key, Object value)
	{
		objects.put(key,value);
	}
	
	public void addString(String key, String value)
	{
		strings.put(key,value);
	}
	
	public void addInteger(String key, Integer value)
	{
		integers.put(key,value);
	}
	
	public void addLong(String key, Long value)
	{
		longs.put(key,value);
	}
	
	public void addDouble(String key, Double value)
	{
		doubles.put(key,value);
	}
	
	public void addFloat(String key, Float value)
	{
		floats.put(key,value);
	}
	
	public void addByte(String key, Byte value)
	{
		bytes.put(key,value);
	}
	
	public void addSWStorage(String key, SWStorage value)
	{
		storages.put(key,value);
	}
	
	public Object getObject(String key)
	{
		return objects.get(key);
	}
	
	public String getString(String key)
	{
		if(strings.containsKey(key))
		{
			return strings.get(key);
		}
		else
		{
			return "";
		}
	}
	
	public Integer getInteger(String key)
	{
		if(integers.containsKey(key))
		{
			return integers.get(key);
		}
		else
		{
			return 0;
		}
	}
	
	public Long getLong(String key)
	{
		if(longs.containsKey(key))
		{
			return longs.get(key);
		}
		else
		{
			return (long)0;
		}
	}
	
	public Double getDouble(String key)
	{
		if(doubles.containsKey(key))
		{
			return doubles.get(key);
		}
		else
		{
			return (double)0;
		}
	}
	
	public Float getFloat(String key)
	{
		if(floats.containsKey(key))
		{
			return floats.get(key);
		}
		else
		{
			return (float)0;
		}
	}
	
	public Byte getByte(String key)
	{
		if(bytes.containsKey(key))
		{
			return bytes.get(key);
		}
		else
		{
			return (byte)0;
		}
	}
	
	public SWStorage getSWStorage(String key)
	{
		if(storages.containsKey(key))
		{
			return storages.get(key);
		}
		else
		{
			return null;
		}
	}
	
	public boolean isBlockStorage()
	{
		return getInteger("type") == 0;
	}
	
	public Block getBlock()
	{
		Location loc = new Location(Bukkit.getWorld(getString("world")), getDouble("x"), getDouble("y"), getDouble("z"));
		System.out.println(getString("world")); System.out.println(getDouble("x")); System.out.println(getDouble("y")); System.out.println(getDouble("z"));
		return loc.getBlock();
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> getValues()
	{
		ArrayList<HashMap> maps = new ArrayList<HashMap>();
		maps.add(this.bytes);
		maps.add(this.doubles);
		maps.add(this.floats);
		maps.add(this.integers);
		maps.add(this.longs);
		maps.add(this.objects);
		maps.add(this.storages);
		maps.add(this.strings);
		return maps;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void add(SWStorage storage)
	{
		ArrayList<HashMap> maps = storage.getValues();
		this.bytes.putAll(maps.get(0));
		this.doubles.putAll(maps.get(1));
		this.floats.putAll(maps.get(2));
		this.integers.putAll(maps.get(3));
		this.longs.putAll(maps.get(4));
		this.objects.putAll(maps.get(5));
		this.storages.putAll(maps.get(6));
		this.strings.putAll(maps.get(7));
	}
	
	public NBTTagCompound toNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		
		for(String s : this.bytes.keySet()) {
			tag.setByte(s, this.bytes.get(s));
		}
		for(String s : this.doubles.keySet()) {
			tag.setDouble(s, this.doubles.get(s));
		}
		for(String s : this.floats.keySet()) {
			tag.setFloat(s, this.floats.get(s));
		}
		for(String s : this.integers.keySet()) {
			tag.setInt(s, this.integers.get(s));
		}
		for(String s : this.longs.keySet()) {
			tag.setLong(s, this.longs.get(s));
		}
		for(String s : this.objects.keySet()) {
			if(this.objects.get(s) instanceof NBTBase)
			tag.set(s, (NBTBase)this.objects.get(s));
		}
		for(String s : this.storages.keySet()) {
			tag.set(s, this.storages.get(s).toNBT());
		}
		for(String s : this.strings.keySet()) {
			tag.setString(s, this.strings.get(s));
		}
		
		return tag;

	}
	
	public void sync(Player p)
	{
		if(isBlockStorage())
		{
			if(SpootWifi.save.hasChannel(getBlock()))
			{
				SpootWifi.save.getChannel(getBlock()).getSWBlock(getBlock()).addStorage(this);
			}
			else if(SpootWifi.storage.hasBlock(getBlock()))
			{
				SpootWifi.storage.getSWBlock(getBlock()).addStorage(this);
			}
		}
		else
		{
			p.setItemInHand(ItemSave.setNBT(p.getItemInHand(), this));
		}
	}

}
