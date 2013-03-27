package fr.Skyforce77.SpootWifi.WifiPackets.Events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.block.SpoutBlock;

public class PacketOperator {

	SpoutBlock sb;
	Player p;
	ItemStack item;
	
	public PacketOperator(SpoutBlock sb) {
		this.sb = sb;
	}
	
	public PacketOperator(Player p, ItemStack item) {
		this.p = p;
		this.item = item;
	}
	
	public PacketOperator(Player p) {
		this.p = p;
	}
	
	public boolean hasBlock() {
		return sb != null;
	}

	public boolean hasItem() {
		return item != null;
	}
	
	public boolean hasPlayer() {
		return p != null;
	}
	
	public Block getBlock() {
		return sb;
	}

	public ItemStack getItem() {
		return item;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public Location getLocation() {
		if(p != null) {
			return p.getLocation();
		} else if(sb != null) {
			return sb.getLocation();
		} else {
			return null;
		}
	}
}
