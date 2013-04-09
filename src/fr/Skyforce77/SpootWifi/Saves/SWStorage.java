package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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

	public SWStorage() {};
	
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
			return new SWStorage();
		}
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

}
