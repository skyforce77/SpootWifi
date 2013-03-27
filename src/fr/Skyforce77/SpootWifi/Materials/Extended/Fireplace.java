package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Simple;

public class Fireplace extends Simple{

	public Fireplace(Plugin plugin, String name) {
		super(plugin, name, Material.NETHERRACK.getId(), getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{42,38,38,38,38,40});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{42,39,39,39,39,41});
		}
		else
		{
			return null;
		}
	}
	
	public void onPowered(SpoutBlock b, boolean powered)
	{
		if(powered && b.getCustomBlockData() == Byte.parseByte("0"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
			if(b.getRelative(BlockFace.UP).getTypeId() == 0)
			{
				b.getRelative(BlockFace.UP).setType(Material.FIRE);
			}
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
			if(b.getRelative(BlockFace.UP).getType().equals(Material.FIRE))
			{
				b.getRelative(BlockFace.UP).setType(Material.AIR);
			}
		}
	}


}
