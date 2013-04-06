package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.ChooseGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.Saves.SWFireworkMeta;
import fr.Skyforce77.SpootWifi.WifiPackets.FireworkPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;

public class FireworkTransmitter extends Transmitter{

	public FireworkTransmitter(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin,0));
		setBlockDesign(getDesign(plugin,1), 1);
	}
	
	private static BlockDesign getDesign(Plugin p,int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{0,34,34,34,34,0});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{3,35,35,35,35,3});
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
		if(player.getItemInHand().getType().equals(Material.getMaterial(401)))
		{
			FireworkMeta meta = (FireworkMeta)player.getItemInHand().getItemMeta();
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addObject("FireworkMeta", new SWFireworkMeta(meta));
			player.sendNotification("Firework set", "", Material.FIREWORK);
			return true;
		}
		else if(!player.isSneaking() && SpootWifi.canInteract(player, b))
		{
			new ChooseGui(player, b);
			return true;
		}
		return true;
	}
	
	public void onPowered(SpoutBlock b, boolean powered)
	{
		Channel c = SpootWifi.save.getChannel(b);
		if(powered && b.getCustomBlockData() == Byte.parseByte("0") && c.getSWBlock(b).getStorage().getObject("FireworkMeta") != null)
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
		    new FireworkPacket((SWFireworkMeta)c.getSWBlock(b).getStorage().getObject("FireworkMeta")).broadcast(c, new PacketOperator(b));
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
		
		SpootWifi.save.getChannel(b).update();
	}

}
