package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WoolColorPacket;

public class ReceiverColoredPixel extends Receiver{
	
	public ReceiverColoredPixel(Plugin plugin, String name) {
		super(plugin, name, 35, getDesign(plugin, 15));
		setBlockDesign(getDesign(plugin, 0), 1);
		setBlockDesign(getDesign(plugin, 1), 2);
		setBlockDesign(getDesign(plugin, 2), 3);
		setBlockDesign(getDesign(plugin, 3), 4);
		setBlockDesign(getDesign(plugin, 4), 5);
		setBlockDesign(getDesign(plugin, 5), 6);
		setBlockDesign(getDesign(plugin, 6), 7);
		setBlockDesign(getDesign(plugin, 7), 8);
		setBlockDesign(getDesign(plugin, 8), 9);
		setBlockDesign(getDesign(plugin, 9), 10);
		setBlockDesign(getDesign(plugin, 10), 11);
		setBlockDesign(getDesign(plugin, 11), 12);
		setBlockDesign(getDesign(plugin, 12), 13);
		setBlockDesign(getDesign(plugin, 13), 14);
		setBlockDesign(getDesign(plugin, 14), 15);
		setBlockDesign(getDesign(plugin, 15), 16);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{design+64,design+64,design+64,design+64,design+64,design+64});
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
	}
	
	@Override
	public void onPacketReceived(WifiPacket wp, SpoutBlock b) {
		if(wp instanceof WoolColorPacket)
		{
			WoolColorPacket mp = (WoolColorPacket)wp;
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)(mp.getColor()+1));
			b.setData(mp.getColor());
		}
		super.onPacketReceived(wp, b);
	}

}
