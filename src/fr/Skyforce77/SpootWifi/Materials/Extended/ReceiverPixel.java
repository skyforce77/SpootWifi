package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;

public class ReceiverPixel extends Receiver{
	
	public ReceiverPixel(Plugin plugin, String name) {
		super(plugin, name, 35, getDesign(plugin, 15));
		setBlockDesign(getDesign(plugin, 0), 1);
	}
	
	private static BlockDesign getDesign(Plugin p, int design) {
		return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{design+64,design+64,design+64,design+64,design+64,design+64});
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
		if(powered && b.getCustomBlockData() == Byte.parseByte("0"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
	}

}
