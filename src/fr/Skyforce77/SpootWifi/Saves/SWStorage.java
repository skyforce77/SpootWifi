package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

import net.minecraft.server.v1_5_R3.NBTBase;
import net.minecraft.server.v1_5_R3.NBTTagByte;
import net.minecraft.server.v1_5_R3.NBTTagByteArray;
import net.minecraft.server.v1_5_R3.NBTTagCompound;
import net.minecraft.server.v1_5_R3.NBTTagDouble;
import net.minecraft.server.v1_5_R3.NBTTagFloat;
import net.minecraft.server.v1_5_R3.NBTTagInt;
import net.minecraft.server.v1_5_R3.NBTTagIntArray;
import net.minecraft.server.v1_5_R3.NBTTagList;
import net.minecraft.server.v1_5_R3.NBTTagLong;
import net.minecraft.server.v1_5_R3.NBTTagString;

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
	public SWStorage() {}
		
	public SWStorage(Block b) {
		Location loc = b.getLocation();
		doubles.put("x", loc.getX());
		doubles.put("y", loc.getY());
		doubles.put("z", loc.getZ());
		strings.put("world", b.getWorld().getName());
		integers.put("StorageType",0);
	};
	
	public SWStorage(ItemStack is) {
		NBTTagCompound nbt = ItemSave.getNBT(is);
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
			} else if(base instanceof NBTTagByteArray) {
				objects.put(s, ((NBTTagByteArray)base));
			} else if(base instanceof NBTTagIntArray) {
				objects.put(s, ((NBTTagIntArray)base));
			} else if(base instanceof NBTTagList) {
				objects.put(s, ((NBTTagList)base));
			} else {
				objects.put(s, base);
			}
		}
		integers.put("StorageType",1);
		
		if(!storages.containsKey("display")) {
			storages.put("display", new SWStorage());
			storages.get("display").addString("Name",ChatColor.RESET+""+ChatColor.GOLD+new SpoutItemStack(is).getMaterial().getName());
		}
	};
	
	public SWStorage(NBTTagCompound nbt) {
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
			} else if(base instanceof NBTTagByteArray) {
				objects.put(s, ((NBTTagByteArray)base));
			} else if(base instanceof NBTTagIntArray) {
				objects.put(s, ((NBTTagIntArray)base));
			} else if(base instanceof NBTTagList) {
				objects.put(s, ((NBTTagList)base));
			} else {
				objects.put(s, base);
			}
		}
		integers.put("StorageType",2);
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
	
	public void addByteArray(String key, byte[] value)
	{
		objects.put(key,new NBTTagByteArray(key, value));
	}
	
	public void addIntArray(String key, int[] value)
	{
		objects.put(key,new NBTTagIntArray(key, value));
	}
	
	public void addList(String key, List<NBTBase> value)
	{
		NBTTagList list = new NBTTagList(key);
		for(NBTBase base : value) {
			list.add(base);
		}
		objects.put(key,list);
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
	
	public byte[] getByteArray(String key)
	{
		if(objects.containsKey(key) && objects.get(key) instanceof NBTTagByteArray)
		{
			return ((NBTTagByteArray)objects.get(key)).data;
		}
		else
		{
			return new byte[0];
		}
	}
	
	public int[] getIntArray(String key)
	{
		if(objects.containsKey(key) && objects.get(key) instanceof NBTTagIntArray)
		{
			return ((NBTTagIntArray)objects.get(key)).data;
		}
		else
		{
			return new int[0];
		}
	}
	
	public NBTTagList getList(String key)
	{
		if(objects.containsKey(key) && objects.get(key) instanceof NBTTagList)
		{
			return ((NBTTagList)objects.get(key));
		}
		else
		{
			return new NBTTagList();
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
		return getInteger("StorageType") == 0;
	}
	
	public boolean isItemStackStorage()
	{
		return getInteger("StorageType") == 1;
	}
	
	public boolean isNBTTagStorage()
	{
		return getInteger("StorageType") == 2;
	}
	
	public Block getBlock()
	{
		Location loc = new Location(Bukkit.getWorld(getString("world")), getDouble("x"), getDouble("y"), getDouble("z"));
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
		this.longs.putAll(maps.get(4));
		this.objects.putAll(maps.get(5));
		this.storages.putAll(maps.get(6));
		this.strings.putAll(maps.get(7));
		
		for(Object s : maps.get(3).keySet())
		{
			if(!((String)s).equals("StorageType") && !((String)s).equals("world") && !((String)s).equals("x") && !((String)s).equals("y") && !((String)s).equals("z"))
			{
				integers.put((String)s, (Integer)maps.get(3).get(s));
			}
		}
	}
	
	public NBTTagCompound toNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		
		if(isItemStackStorage()) {
			ArrayList<NBTBase> lore = new ArrayList<NBTBase>();
			
			if(integers.containsKey("SpootWifiChannel")) {
				lore.add(new NBTTagString("", ChatColor.RESET+"Channel: "+getInteger("SpootWifiChannel")));
			}
			if(integers.containsKey("x") && integers.containsKey("y") && integers.containsKey("z")) {
				lore.add(new NBTTagString("", ChatColor.RESET+"Block: "+getInteger("x")+";"+getInteger("y")+";"+getInteger("z")));
			}
			
			if(doubles.containsKey("x")) {
				doubles.remove("x");
				doubles.remove("y");
				doubles.remove("z");
			}
			
			storages.get("display").addList("Lore", lore);
		}
		
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
		
		if(!storages.containsKey("display")) {
			storages.put("display", new SWStorage());
		} 
		
		return tag;

	}
	
	public void sync(Player p)
	{
		if(isBlockStorage())
		{
			SpoutBlock sb = (SpoutBlock)getBlock();
			if(sb.getCustomBlock() instanceof Receiver || sb.getCustomBlock() instanceof Transmitter) {
				SpootWifi.save.getChannel(getBlock()).getSWBlock(getBlock()).addStorage(this);
			} else {
				SpootWifi.storage.getSWBlock(getBlock()).addStorage(this);
			}
		}
		else if(isItemStackStorage())
		{
			p.setItemInHand(ItemSave.setNBT(p.getItemInHand(), this));
		}
	}
	
	public void removeAll(String key) {
		for(HashMap<?, ?> map : getValues()) {
			if(map.containsKey(key)) {
				map.remove(key);
			}
		}
	}

}
