package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.chrischurchwell.jukeit.material.items.BurnedDisc;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.MusicChooseGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.WifiPackets.MusicPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;

public class MusicTransmitter extends Transmitter{

	public MusicTransmitter(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin,0));
		setBlockDesign(getDesign(plugin,1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p,int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{0,14,14,14,14,0});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{3,15,15,15,15,3});
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player)
	{
		Block b = new Location(world,x,y,z).getBlock();
		SpootWifi.save.getRawChannel(b);
		SpoutItemStack sis = new SpoutItemStack(player.getItemInHand());
		if(SpootWifi.JukeIt && sis.isCustomItem() && sis.getMaterial() instanceof BurnedDisc)
		{
			String url = BurnedDisc.decodeDisc(player.getItemInHand());
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addString("MusicValue", url);
			player.sendNotification("Music set", "", Material.RECORD_4);
			return true;
		}
		else if(!player.isSneaking() && SpootWifi.canInteract(player, b))
		{
			new MusicChooseGui(player, b);
			return true;
		}
		return super.onBlockInteract(world, x, y, z, player);
	}
	
	public void onPowered(SpoutBlock b, boolean powered)
	{
		Channel c = SpootWifi.save.getChannel(b);
		if(powered && b.getCustomBlockData() == Byte.parseByte("0") && !c.getSWBlock(b).getStorage().getString("MusicValue").equals(""))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
			new MusicPacket(c.getSWBlock(b).getStorage().getString("MusicValue")).broadcast(c, new PacketOperator(b));
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
		
		SpootWifi.save.getChannel(b).update();
	}

}
