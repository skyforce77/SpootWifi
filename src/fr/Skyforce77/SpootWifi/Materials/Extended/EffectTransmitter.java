package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.EffectChooseGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.WifiPackets.EffectPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;

public class EffectTransmitter extends Transmitter{

	public EffectTransmitter(Plugin plugin, String name) {
		super(plugin, name, 41, getDesign(plugin,0));
		setBlockDesign(getDesign(plugin,1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p,int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{45,49,49,49,49,53});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{46,50,50,50,50,54});
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z,
			SpoutPlayer player) {
		Block b = new Location(world,x,y,z).getBlock();
		SpootWifi.save.getRawChannel(b);
		if(!player.isSneaking() && SpootWifi.canInteract(player, b))
		{
			new EffectChooseGui(player, b);
			return true;
		}
		return super.onBlockInteract(world, x, y, z, player);
	}
	
	public void onPowered(SpoutBlock b, boolean powered)
	{
		if(powered && b.getCustomBlockData() == Byte.parseByte("0"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
			Channel c = SpootWifi.save.getChannel(b);
			new EffectPacket(c.getSWBlock(b).getStorage().getString("Effect")).broadcast(c, new PacketOperator(b));
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
		
		SpootWifi.save.getChannel(b).update();
	}

}
