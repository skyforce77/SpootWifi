package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.WifiPackets.MusicPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class ReceiverJukeBox extends Receiver{

	private int range;
	
	public ReceiverJukeBox(Plugin plugin, String name, int range, int textureoff, int textureon) {
		super(plugin, name, 5, getDesign(plugin, 0, textureoff));
		setBlockDesign(getDesign(plugin, 1, textureon), 1);
		this.range = range;
	}
	
	private static BlockDesign getDesign(Plugin p, int design, int texture)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{0,texture,texture,texture,texture,0});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{3,texture,texture,texture,texture,3});
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
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
	}
	
	@Override
	public void onPacketReceived(WifiPacket wp, SpoutBlock b) {
		if(wp instanceof MusicPacket)
		{
			MusicPacket mp = (MusicPacket)wp;
			SpoutManager.getSoundManager().playGlobalCustomMusic(SpootWifi.plugin, mp.getMusic(), true, b.getLocation(), range, (int)(mp.getPower()*100));
		}
		super.onPacketReceived(wp, b);
	}

}
