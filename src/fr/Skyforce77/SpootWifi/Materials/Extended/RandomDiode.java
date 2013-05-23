package fr.Skyforce77.SpootWifi.Materials.Extended;

import java.util.Random;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Simple;

public class RandomDiode extends Simple{
	
	public RandomDiode(Plugin plugin, String name) {
		super(plugin, name, 35, getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 1), 1);
		setBlockDesign(getDesign(plugin, 2), 2);
		setBlockDesign(getDesign(plugin, 3), 3);
		setBlockDesign(getDesign(plugin, 4), 4);
		setBlockDesign(getDesign(plugin, 5), 5);
		setBlockDesign(getDesign(plugin, 6), 6);
		setBlockDesign(getDesign(plugin, 7), 7);
		setBlockDesign(getDesign(plugin, 8), 8);
		setBlockDesign(getDesign(plugin, 9), 9);
		setBlockDesign(getDesign(plugin, 10), 10);
		setBlockDesign(getDesign(plugin, 11), 11);
		setBlockDesign(getDesign(plugin, 12), 12);
		setBlockDesign(getDesign(plugin, 13), 13);
		setBlockDesign(getDesign(plugin, 14), 14);
		setBlockDesign(getDesign(plugin, 15), 15);
		setBlockDesign(getDesign(plugin, 0), 16);
		setBlockDesign(getDesign(plugin, 1), 17);
		setBlockDesign(getDesign(plugin, 2), 18);
		setBlockDesign(getDesign(plugin, 3), 19);
		setBlockDesign(getDesign(plugin, 4), 20);
		setBlockDesign(getDesign(plugin, 5), 21);
		setBlockDesign(getDesign(plugin, 6), 22);
		setBlockDesign(getDesign(plugin, 7), 23);
		setBlockDesign(getDesign(plugin, 8), 24);
		setBlockDesign(getDesign(plugin, 9), 25);
		setBlockDesign(getDesign(plugin, 10), 26);
		setBlockDesign(getDesign(plugin, 11), 27);
		setBlockDesign(getDesign(plugin, 12), 28);
		setBlockDesign(getDesign(plugin, 13), 29);
		setBlockDesign(getDesign(plugin, 14), 30);
		setBlockDesign(getDesign(plugin, 15), 31);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{design+16,design+16,design+16,design+16,design+16,design+16});
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
		if(powered && b.getCustomBlockData() <= Byte.parseByte("15"))
		{
			int rand = new Random().nextInt(15);
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)(rand+16));
			b.setData((byte)rand);
		}
		else if(!powered && b.getCustomBlockData() > Byte.parseByte("15"))
		{
			int rand = new Random().nextInt(15);
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)(rand));
			b.setData((byte)rand);
		}
	}

}
