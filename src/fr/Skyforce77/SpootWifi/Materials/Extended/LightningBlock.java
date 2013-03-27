package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Simple;

public class LightningBlock extends Simple{

	public LightningBlock(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{45,45,45,45,45,45});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{46,46,46,46,46,46});
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
			b.getWorld().strikeLightningEffect(b.getLocation());
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
	}


}
