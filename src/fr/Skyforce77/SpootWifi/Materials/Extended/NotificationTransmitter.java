package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.NotificationChooseGui;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.Saves.MFItemStack;
import fr.Skyforce77.SpootWifi.WifiPackets.NotificationPacket;
import fr.Skyforce77.SpootWifi.WifiPackets.Events.PacketOperator;

public class NotificationTransmitter extends Transmitter{

	public NotificationTransmitter(Plugin plugin, String name) {
		super(plugin, name, 42, getDesign(plugin,0));
		setBlockDesign(getDesign(plugin,1), 1);
		setGui(NotificationChooseGui.class);
	}
	
	private static BlockDesign getDesign(Plugin p,int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{43,1,1,1,1,43});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{44,4,4,4,4,44});
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
		if(!player.isSneaking() && player.getItemInHand().getTypeId() != 0)
		{
			ItemStack is = player.getItemInHand();
			player.sendNotification("Item set", "", is, 2000);
			SpootWifi.save.getChannel(b).getSWBlock(b).getStorage().addObject("ItemStack", new MFItemStack(is));
			return true;
		}
		else if(!player.isSneaking() && SpootWifi.canInteract(player, b))
		{
			openGui(player, SpootWifi.save.getChannel(b).getSWBlock(b).getStorage());
			return true;
		}
		return true;
	}
	
	public void onPowered(SpoutBlock b, boolean powered)
	{
		Channel c = SpootWifi.save.getChannel(b);
		if(powered && b.getCustomBlockData() == Byte.parseByte("0") && c.getSWBlock(b).getStorage().getObject("ItemStack") != null)
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)1);
			new NotificationPacket(c.getSWBlock(b).getStorage().getString("title"),c.getSWBlock(b).getStorage().getString("text"),((MFItemStack)c.getSWBlock(b).getStorage().getObject("ItemStack")).toItemStack(), 5000).broadcast(c, new PacketOperator(b));
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
		
		SpootWifi.save.getChannel(b).update(b.getWorld());
	}

}
