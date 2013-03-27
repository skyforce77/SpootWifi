package fr.Skyforce77.SpootWifi.Saves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.block.Block;

public class Storage implements Serializable{

	private static final long serialVersionUID = -8918955800194752863L;
	private ArrayList<SWBlock> storages = new ArrayList<SWBlock>();

	public Storage(){}
	
	public Boolean hasBlock(Block b)
	{
		boolean has = false;
		for(SWBlock sw : storages)
		{
			Block bl = sw.getBlock();
			if(bl.equals(b))
			{
				has = true;
			}
		}
		return has;
	}
	
	public int getBlock(Block b)
	{
		int i = 0;
		for(SWBlock sw : storages)
		{
			Block bl = sw.getBlock();
			if(bl.equals(b))
			{
				i = storages.indexOf(sw);
			}
		}
		return i;
	}
	
	public SWBlock getSWBlock(Block b)
	{
		for(SWBlock sw : storages)
		{
			Block bl = sw.getBlock();
			if(bl.equals(b))
			{
				return sw;
			}
		}
		return null;
	}
	
	public void addBlock(Block b)
	{
		if(!hasBlock(b))
		{
			storages.add(new SWBlock(b));
		}
	}
	
	public void removeBlock(Block b)
	{
		if(hasBlock(b))
		{
			storages.remove(getBlock(b));
		}
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
	
	public static Storage Deserialize(String file)
	{
		try
		{
			FileInputStream fichier = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Storage o = (Storage)ois.readObject();
			ois.close();
			return o;
		}
		catch (Exception e) 
		{
			return new Storage();
		}
	}

}
