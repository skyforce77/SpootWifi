package fr.Skyforce77.SpootWifi.Materials.Extended;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spout.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.GlobalNotificationChooseGui;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.Saves.MFItemStack;

public class GlobalNotificationTransmitter extends NotificationTransmitter{

	public GlobalNotificationTransmitter(Plugin plugin, String name) {
		super(plugin, name);
		setBlockDesign(getDesign(plugin,0), 0);
		setBlockDesign(getDesign(plugin,1), 1);
		setGui(GlobalNotificationChooseGui.class);
	}
	
	private static BlockDesign getDesign(Plugin p,int design)
	{
		if(design == 1)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{45,49,49,49,49,32});
		}
		else if(design == 0)
		{
			return new GenericCubeBlockDesign(p, SpootWifi.texture, new int[]{46,50,50,50,50,33});
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
			for(SpoutPlayer sp : Spout.getInstance().getOnlinePlayers())
			{
				ItemStack render = ((MFItemStack)c.getSWBlock(b).getStorage().getObject("ItemStack")).toItemStack();
				String title = Radio.getString(c.getSWBlock(b).getStorage().getString("title"), sp, render);
				String text = Radio.getString(c.getSWBlock(b).getStorage().getString("text"), sp, render);
				sp.sendNotification(title, text, render, 5000);
			}
		}
		else if(!powered && b.getCustomBlockData() == Byte.parseByte("1"))
		{
			SpoutManager.getMaterialManager().overrideBlock(b, b.getCustomBlock(), (byte)0);
		}
		
		SpootWifi.save.getChannel(b).update();
	}

}
