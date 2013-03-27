package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.MaterialData;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;

public class WirelessLampOn extends Receiver{

	private WirelessLamp lamp;
	
	public WirelessLampOn(Plugin plugin, String name, WirelessLamp lamp) {
		super(plugin, name+" On", MaterialData.glowstoneBlock.getRawId(), getDesign(plugin));
		this.lamp = lamp;
		setName(name);
		setLightLevel(15);
		setItemDrop(new SpoutItemStack(lamp));
	}
	
	private static BlockDesign getDesign(Plugin p)
	{
		return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{47,47,47,47,47,47});
	}
	
	@Override
	public void onPowered(SpoutBlock b, boolean powered) {
		if(!powered)
		{
			SpoutManager.getMaterialManager().overrideBlock(b, lamp);
		}
	}

}
