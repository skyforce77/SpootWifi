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

public class ReceiverColoredDiode extends Receiver{
	
	public ReceiverColoredDiode(Plugin plugin, String name) {
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
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{design+16,design+16,design+16,design+16,design+16,design+16});
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
	}
	
	@Override
	public void onPacketReceived(WifiPacket wp, SpoutBlock b) {
		if(wp instanceof WoolColorPacket)
		{
			WoolColorPacket mp = (WoolColorPacket)wp;
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), mp.getColor());
			b.setData(mp.getColor());
		}
		super.onPacketReceived(wp, b);
	}

}
