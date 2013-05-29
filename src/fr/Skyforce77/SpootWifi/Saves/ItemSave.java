package fr.Skyforce77.SpootWifi.Saves;

import net.minecraft.server.v1_5_R3.NBTTagCompound;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_5_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemSave {

	public static Integer getChannel(ItemStack is)
	{
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		
		if(nmsis.tag == null)
		{
			return 0;
		}
		
		if(nmsis.tag.hasKey("SpootWifiChannel"))
		{
			return nmsis.tag.getInt("SpootWifiChannel");
		}
		
		return 0;
	}
	
	public static ItemStack setChannel(ItemStack is, int channel)
	{
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		
		if(nmsis.tag == null)
		{
			nmsis.tag = new NBTTagCompound();
		}
		
		nmsis.tag.setInt("SpootWifiChannel", channel);
		return CraftItemStack.asCraftMirror(nmsis);
	}
	
	public static Integer getOption(ItemStack is, String option)
	{
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		
		if(nmsis.tag == null)
		{
			return 0;
		}
		
		if(nmsis.tag.hasKey(option))
		{
			return nmsis.tag.getInt(option);
		}
		
		return 0;
	}
	
	public static ItemStack setOption(ItemStack is, String option, int num)
	{
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		
		if(nmsis.tag == null)
		{
			nmsis.tag = new NBTTagCompound();
		}
		
		nmsis.tag.setInt(option, num);
		return CraftItemStack.asCraftMirror(nmsis);
	}
	
	public static Block getBlock(ItemStack is)
	{
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		
		if(nmsis.tag == null)
		{
			return null;
		}
		
		if(nmsis.tag.hasKey("x"))
		{
			if(Bukkit.getWorld(nmsis.tag.getString("world")) != null)
			{
				Location loc = new Location(Bukkit.getWorld(nmsis.tag.getString("world")), nmsis.tag.getInt("x"), nmsis.tag.getInt("y"), nmsis.tag.getInt("z"));
				return loc.getBlock();
			}
		}
		
		return null;
	}
	
	public static NBTTagCompound getNBT(ItemStack is)
	{
		
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		
		if(nmsis.tag == null)
		{
			return new NBTTagCompound();
		}
		
		return nmsis.tag;
	}

	public static ItemStack setNBT(ItemStack is, SWStorage storage)
	{
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		nmsis.tag = storage.toNBT();
		return CraftItemStack.asCraftMirror(nmsis);
	}
	
	public static ItemStack setBlock(ItemStack is, Block b)
	{
		net.minecraft.server.v1_5_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
		
		if(nmsis.tag == null)
		{
			nmsis.tag = new NBTTagCompound();
		}

		nmsis.tag.setInt("x", b.getX());
		nmsis.tag.setInt("y", b.getY());
		nmsis.tag.setInt("z", b.getZ());
		nmsis.tag.setString("world", b.getWorld().getName());
		return CraftItemStack.asCraftMirror(nmsis);
	}
	
	public static ItemStack setName(ItemStack is, String name)
	{
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return is;
	}

}
