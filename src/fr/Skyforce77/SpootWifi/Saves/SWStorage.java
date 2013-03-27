package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.HashMap;

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

}
