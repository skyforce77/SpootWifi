package fr.Skyforce77.SpootWifi.Materials.Basics;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.SpootWifi;
import fr.Skyforce77.SpootWifi.GUI.ChooseGui;
import fr.Skyforce77.SpootWifi.Saves.Channel;
import fr.Skyforce77.SpootWifi.Saves.ItemSave;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class Remote extends GenericCustomItem{

	public Remote(Plugin arg0, String arg1) {
		super(arg0, arg1);
	}
	
	public Remote(Plugin arg0, String arg1, String arg2) {
		super(arg0, arg1, arg2);
	}
	
	public void onClick(SpoutPlayer p, ItemStack is, SpoutBlock block) {
		if(p.isSneaking()){
			if(block != null && block.getState() instanceof InventoryHolder) {
				InventoryHolder chest = (InventoryHolder)block.getState();
				if(chest.getInventory().contains(new SpoutItemStack(SpootWifi.viewer), 1)) {
					p.setItemInHand(ItemSave.setBlock(is, block));
					p.sendNotification("Inventory set", "", new SpoutItemStack(block.getBlockType()), 2000);
				} else {
					p.sendMessage(ChatColor.RED+"Unable to access to that block. Receiver not detected.");
				}
			} else if(ItemSave.getBlock(is) != null && ((SpoutBlock)ItemSave.getBlock(is)).getCustomBlock() != null && ((SpoutBlock)ItemSave.getBlock(is)).getCustomBlock() instanceof Transmitter){
				SpoutBlock sb = (SpoutBlock)ItemSave.getBlock(is);
				Transmitter t = (Transmitter)sb.getCustomBlock();
				p.setSneaking(false);
				t.onBlockInteract(sb.getWorld(), sb.getX(), sb.getY(), sb.getZ(), p);
			} else if(block != null && block.getCustomBlock() != null && block.getCustomBlock() instanceof Transmitter){
				p.setItemInHand(ItemSave.setBlock(p.getItemInHand(), block));
				p.sendNotification("Transmitter set", "", new SpoutItemStack(block.getBlockType()), 2000);
			}
			if(ItemSave.getBlock(p.getItemInHand()) == null) {
				new ChooseGui(this.getName(), p, new SWStorage(is));
			}
		} else {
			if(ItemSave.getBlock(is) == null) {
				SpootWifi.save.getChannel(ItemSave.getChannel(is)).update(p.getWorld(), true);
			} else {
				if(ItemSave.getBlock(p.getItemInHand()) != null && ((SpoutBlock)ItemSave.getBlock(p.getItemInHand())).getCustomBlock() != null && ((SpoutBlock)ItemSave.getBlock(p.getItemInHand())).getCustomBlock() instanceof Transmitter)
				{
					SpoutBlock sb = (SpoutBlock)ItemSave.getBlock(p.getItemInHand());
					Transmitter t = (Transmitter)((SpoutBlock)ItemSave.getBlock(p.getItemInHand())).getCustomBlock();
					t.onPowered(sb, true);
				}
				else if(ItemSave.getBlock(p.getItemInHand()) != null && ItemSave.getBlock(p.getItemInHand()).getState() instanceof InventoryHolder)
				{
					SpoutBlock sb = (SpoutBlock)ItemSave.getBlock(p.getItemInHand());
					InventoryHolder chest = (InventoryHolder)(sb.getState());
					if(chest.getInventory().contains(new SpoutItemStack(SpootWifi.viewer), 1)) {
						p.openInventory(chest.getInventory());
					} else {
						p.sendMessage(ChatColor.RED+"Unable to access to that block. Receiver not detected.");
					}
				}
				else {
					p.sendMessage(ChatColor.RED+"Unable to access to that block. Maybe breaked or replaced.");
					p.setItemInHand(ItemSave.removeBlock(p.getItemInHand()));
					p.sendNotification("Block removed", "", Material.STONE);
				}
			}
		}
	}
	
	public void onUnclick(SpoutPlayer p, ItemStack is) {
		if(!p.isSneaking()) {
			if(ItemSave.getBlock(is) == null) {
				Channel chan = SpootWifi.save.getChannel(ItemSave.getChannel(is));
				if(!chan.isActive()) {
					chan.update(p.getWorld(), false);
				}
			} else {
				if(ItemSave.getBlock(p.getItemInHand()) != null && ((SpoutBlock)ItemSave.getBlock(p.getItemInHand())).getCustomBlock() != null && ((SpoutBlock)ItemSave.getBlock(p.getItemInHand())).getCustomBlock() instanceof Transmitter)
				{
					SpoutBlock sb = (SpoutBlock)ItemSave.getBlock(p.getItemInHand());
					Transmitter t = (Transmitter)((SpoutBlock)ItemSave.getBlock(p.getItemInHand())).getCustomBlock();
					t.onPowered(sb, false);
				}
			}
		}
	}

}
