package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class SWBlock implements Serializable{

	private static final long serialVersionUID = 8611897040770820161L;
	private UUID world;
	private int x;
	private int y;
	private int z;
	private SWStorage storage = new SWStorage();
	
	public SWBlock(Block b)
	{
		world = b.getWorld().getUID();
		x = b.getX();
		y = b.getY();
		z = b.getZ();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((world == null) ? 0 : world.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SWBlock other = (SWBlock) obj;
		if (world == null) {
			if (other.world != null)
				return false;
		} else if (!world.equals(other.world))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	public Block getBlock()
	{
		if(Bukkit.getWorld(world) != null)
		{
			return new Location(Bukkit.getWorld(world), x,y,z).getBlock();
		}
		else
		{
			return null;
		}
	}
	
	public SWStorage getStorage()
	{
		return this.storage;
	}

}
