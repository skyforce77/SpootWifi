package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.WifiPackets.EntityTypePacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class ReceiverMob extends Receiver{
	
	public ReceiverMob(Plugin plugin, String name) {
		super(plugin, name, org.bukkit.Material.PISTON_BASE.getId(), getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{8,8,8,8,8,8});
		}
		else
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{9,9,9,9,9,9});
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
		if(wp instanceof EntityTypePacket)
		{
			try
			{
				EntityTypePacket mp = (EntityTypePacket)wp;
				Location loc = b.getLocation().add(0.5,2,0.5);
				b.getWorld().spawnEntity(loc, mp.getEntityType());
			}
			catch(Exception e){}
		}
		super.onPacketReceived(wp, b);
	}

}
