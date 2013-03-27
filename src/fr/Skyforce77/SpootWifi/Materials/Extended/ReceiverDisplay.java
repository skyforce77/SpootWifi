package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;

public class ReceiverDisplay extends Receiver{

	public ReceiverDisplay(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 0).rotate(90), 1);
		setBlockDesign(getDesign(plugin, 0).rotate(180), 2);
		setBlockDesign(getDesign(plugin, 0).rotate(270), 3);
		setBlockDesign(getDesign(plugin, 1), 4);
		setBlockDesign(getDesign(plugin, 1).rotate(90), 5);
		setBlockDesign(getDesign(plugin, 1).rotate(180), 6);
		setBlockDesign(getDesign(plugin, 1).rotate(270), 7);
		setRotate(true);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{0,2,2,9,2,0});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{3,5,5,8,5,3});
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
		if(powered && b.getCustomBlockData() == Byte.parseByte("0"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)4);
		}
		else if(powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)5);
		}
		else if(powered && b.getCustomBlockData() == Byte.parseByte("2"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)6);
		}
		else if(powered && b.getCustomBlockData() == Byte.parseByte("3"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)7);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("4"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("5"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("6"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)2);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("7"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)3);
		}
	}

}
