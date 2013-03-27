package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.material.MaterialData;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;

public class WirelessLamp extends Receiver{

	WirelessLampOn lamp;
	
	public WirelessLamp(Plugin plugin, String name) {
		super(plugin, name, MaterialData.redstoneLampOff.getRawId(), getDesign(plugin));
		lamp = new WirelessLampOn(plugin, name, this);
	}
	
	private static BlockDesign getDesign(Plugin p)
	{
		return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{48,48,48,48,48,48});
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
		if(powered)
		{
			SpoutManager.getMaterialManager().overrideBlock(b, lamp);
		}
	}

}
