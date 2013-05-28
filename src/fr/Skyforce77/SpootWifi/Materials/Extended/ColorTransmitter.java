package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.ColorChooseGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;
import fr.Skyforce77.SpootWifi.WifiPackets.WoolColorPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;

public class ColorTransmitter extends Transmitter{

	public ColorTransmitter(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin,0,false));
		setBlockDesign(getDesign(plugin,1,false), 1);
		setBlockDesign(getDesign(plugin,2,false), 2);
		setBlockDesign(getDesign(plugin,3,false), 3);
		setBlockDesign(getDesign(plugin,4,false), 4);
		setBlockDesign(getDesign(plugin,5,false), 5);
		setBlockDesign(getDesign(plugin,6,false), 6);
		setBlockDesign(getDesign(plugin,7,false), 7);
		setBlockDesign(getDesign(plugin,8,false), 8);
		setBlockDesign(getDesign(plugin,9,false), 9);
		setBlockDesign(getDesign(plugin,10,false), 10);
		setBlockDesign(getDesign(plugin,11,false), 11);
		setBlockDesign(getDesign(plugin,12,false), 12);
		setBlockDesign(getDesign(plugin,13,false), 13);
		setBlockDesign(getDesign(plugin,14,false), 14);
		setBlockDesign(getDesign(plugin,15,false), 15);
		setBlockDesign(getDesign(plugin,0,true), 16);
		setBlockDesign(getDesign(plugin,1,true), 17);
		setBlockDesign(getDesign(plugin,2,true), 18);
		setBlockDesign(getDesign(plugin,3,true), 19);
		setBlockDesign(getDesign(plugin,4,true), 20);
		setBlockDesign(getDesign(plugin,5,true), 21);
		setBlockDesign(getDesign(plugin,6,true), 22);
		setBlockDesign(getDesign(plugin,7,true), 23);
		setBlockDesign(getDesign(plugin,8,true), 24);
		setBlockDesign(getDesign(plugin,9,true), 25);
		setBlockDesign(getDesign(plugin,10,true), 26);
		setBlockDesign(getDesign(plugin,11,true), 27);
		setBlockDesign(getDesign(plugin,12,true), 28);
		setBlockDesign(getDesign(plugin,13,true), 29);
		setBlockDesign(getDesign(plugin,14,true), 30);
		setBlockDesign(getDesign(plugin,15,true), 31);
		setGui(ColorChooseGui.class);
	}
	
	private static BlockDesign getDesign(Plugin p,int design, boolean powered)
	{
		if(powered)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{design+16,1,1,1,1,design+16});
		}
		else
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{design+16,4,4,4,4,design+16});
		}
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z,
			SpoutPlayer player) {
		Block b = new Location(world,x,y,z).getBlock();
		if(!player.isSneaking() && SpootWifi.canInteract(player, b))
		{
			openGui(player, SpootWifi.save.getChannel(b).getSWBlock(b).getStorage());
			return true;
		}
		return super.onBlockInteract(world, x, y, z, player);
	}
	
	public void onPowered(SpoutBlock b, boolean powered)
	{
		if(powered && b.getCustomBlockData() <= Byte.parseByte("15"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)(b.getCustomBlockData()+16));
			Channel c = SpootWifi.save.getChannel(b);
			new WoolColorPacket(c.getSWBlock(b).getStorage().getByte("WoolColor")).broadcast(c, new PacketOperator(b));
		}
		else if(!powered && b.getCustomBlockData() >= Byte.parseByte("16"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)(b.getCustomBlockData()-16));
		}
		
		SpootWifi.save.getChannel(b).update(b.getWorld());
	}
	
	@Override
	public void onPlaced(SpoutBlock b, Player p, SWStorage storage) {
		DyeColor color = DyeColor.getByWoolData(storage.getByte("WoolColor"));
		if(b.getCustomBlockData() >= 16)
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)(color.getWoolData()+16));
		}
		else
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), color.getWoolData());
		}
	}

}
