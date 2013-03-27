package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.Saves.SWFireworkEffect;
import fr.Skyforce77.SpootWifi.WifiPackets.FireworkPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.WifiPacket;

public class ReceiverFirework extends Receiver {
	
	public ReceiverFirework(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin, 0));
		setBlockDesign(getDesign(plugin, 1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p, int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{0,36,36,36,36,0});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{3,37,37,37,37,3});
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
		if(wp instanceof FireworkPacket)
		{
			FireworkPacket mp = (FireworkPacket)wp;
			try
			{
				Firework f = (Firework)b.getWorld().spawnEntity(b.getLocation().add(0.5,1.5,0.5), EntityType.FIREWORK);
				FireworkMeta  meta = f.getFireworkMeta();
				for(SWFireworkEffect effect : mp.getMeta().getEffect())
				{
					meta.addEffect(effect.getEffect());
				}
				meta.setPower(mp.getMeta().getPower());
				f.setFireworkMeta(meta);
			}
			catch(Exception e)
			{
			}
		}
		super.onPacketReceived(wp, b);
	}

}
