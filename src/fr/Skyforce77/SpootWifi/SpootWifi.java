package fr.Skyforce77.SpootWifi;

import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.Texture;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.WidgetType;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.mcstats.Metrics;

import fr.Skyforce77.SpootWifi.Entity.SWEntityItem;
import fr.Skyforce77.SpootWifi.Materials.Basics.Configurable;
import fr.Skyforce77.SpootWifi.Materials.Basics.Receiver;
import fr.Skyforce77.SpootWifi.Materials.Basics.Remote;
import fr.Skyforce77.SpootWifi.Materials.Basics.Simple;
import fr.Skyforce77.SpootWifi.Materials.Basics.Transmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.ColorTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.EffectTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.Fireplace;
import fr.Skyforce77.SpootWifi.Materials.Extended.FireworkTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.GlobalNotificationTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.LightningBlock;
import fr.Skyforce77.SpootWifi.Materials.Extended.MobTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.MusicTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.NotificationTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.ParticleTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.Radio;
import fr.Skyforce77.SpootWifi.Materials.Extended.RandomDiode;
import fr.Skyforce77.SpootWifi.Materials.Extended.RandomPixel;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverColoredDiode;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverColoredPixel;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverDiode;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverDisplay;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverEffect;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverFirework;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverJukeBox;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverMob;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverParticle;
import fr.Skyforce77.SpootWifi.Materials.Extended.ReceiverPixel;
import fr.Skyforce77.SpootWifi.Materials.Extended.SoundTransmitter;
import fr.Skyforce77.SpootWifi.Materials.Extended.WirelessLamp;
import fr.Skyforce77.SpootWifi.Materials.Extended.WirelessSniffer;
import fr.Skyforce77.SpootWifi.Saves.ItemSave;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;
import fr.Skyforce77.SpootWifi.Saves.Save;
import fr.Skyforce77.SpootWifi.Saves.Storage;
import fr.Skyforce77.SpootWifi.Utils.Colors;
import fr.Skyforce77.SpootWifi.Utils.RessourceManager;

public class SpootWifi extends JavaPlugin implements Listener{
	
	public static Save save = null;
	public static Storage storage = null;
	public static CustomBlock transmitter = null;
	public static CustomBlock receiver = null;
	public static CustomBlock receiverdiode = null;
	public static CustomBlock receiverdisplay = null;
	public static CustomBlock receiverjukebox = null;
	public static CustomBlock receiverjukeboxextended = null;
	public static CustomBlock transmitterjukebox = null;
	public static CustomBlock receivercoloreddiode = null;
	public static CustomBlock receivercoloredpixel = null;
	public static CustomBlock receiverpixel = null;
	public static CustomBlock transmittercolor = null;
	public static CustomBlock receiverfirework = null;
	public static CustomBlock transmitterfirework = null;
	public static CustomBlock transmitternotification = null;
	public static CustomBlock globaltransmitternotification = null;
	public static CustomBlock receivermob = null;
	public static CustomBlock transmittermob = null;
	public static CustomBlock receiverparticle = null;
	public static CustomBlock transmitterparticle = null;
	public static CustomBlock receivereffect = null;
	public static CustomBlock transmittereffect = null;
	public static CustomBlock fireplace = null;
	public static CustomBlock lightningblock = null;
	public static CustomBlock wirelesslamp = null;
	public static CustomBlock randomcolordiode = null;
	public static CustomBlock randomcolorpixel = null;
	public static CustomItem sniffer = null;
	public static CustomItem radio = null;
	public static CustomItem ironstick = null;
	public static HashMap<Byte,CustomItem> remote = new HashMap<Byte,CustomItem>();
	public static CustomItem viewer = null;
	public static Texture texture = null;
	public static Plugin plugin;
	public static boolean JukeIt = false;
	public static PluginManager pm;

	public void onDisable()
    {
		save.Serialize(this.getDataFolder()+"/Ressources/Storage/Save.SpootWifi");
		storage.Serialize(this.getDataFolder()+"/Ressources/Storage/BlockStorage.SpootWifi");
    }

	public void onEnable()
	{
		plugin = this;
		pm = Bukkit.getPluginManager();
		RessourceManager.LoadConfig();
		save = Save.Deserialize(this.getDataFolder().getPath()+"/Ressources/Storage/Save.SpootWifi");
		storage = Storage.Deserialize(this.getDataFolder().getPath()+"/Ressources/Storage/BlockStorage.SpootWifi");
		SpoutManager.getFileManager().addToPreLoginCache(this, "http://dl.dropbox.com/u/38885163/plugins/moreblocks/plugin/gui.png");
		
		texture = new Texture(this, RessourceManager.getTexture("terrain.png"), 256,256,16);
		
		transmitter = new Transmitter(this, "Wireless Transmitter");
		receiver = new Receiver(this, "Wireless Receiver");
		receiverdiode = new ReceiverDiode(this, "Wireless Diode");
		receiverdisplay = new ReceiverDisplay(this, "Wireless Display");
		receiverjukebox = new ReceiverJukeBox(this, "Wireless JukeBox", 5, 11, 10);
		receiverjukeboxextended = new ReceiverJukeBox(this, "Wireless JukeBox Extended", 50, 13, 12);
		transmitterjukebox = new MusicTransmitter(this, "Wireless Music Transmitter");
		receivercoloreddiode = new ReceiverColoredDiode(this, "Wireless Colored Diode");
		receivercoloredpixel = new ReceiverColoredPixel(this, "Wireless Colored Pixel");
		receiverpixel = new ReceiverPixel(this, "Wireless Pixel");
		transmittercolor = new ColorTransmitter(this, "Wireless Color Transmitter");
		receiverfirework = new ReceiverFirework(this, "Wireless Firework Launcher");
		transmitterfirework = new FireworkTransmitter(this, "Wireless Firework Transmitter");
		transmitternotification = new NotificationTransmitter(this, "Wireless Notification Transmitter");
		globaltransmitternotification = new GlobalNotificationTransmitter(this, "Global Notification Transmitter");
		transmittermob = new MobTransmitter(this, "Wireless Mob Transmitter");
		receivermob = new ReceiverMob(this, "Wireless Mob Receiver");
		fireplace = new Fireplace(this, "Redstone Fireplace");
		lightningblock = new LightningBlock(this, "Lightning Block");
		wirelesslamp = new WirelessLamp(this, "Wireless Redstone Lamp");
		sniffer = new WirelessSniffer(this, "Wireless Sniffer");
		radio = new Radio(this, "Radio");
		ironstick = new GenericCustomItem(this, "Iron Stick", RessourceManager.getTexture("ironstick.png"));
		receivereffect = new ReceiverEffect(this, "Wireless Effect Receiver");
		transmittereffect = new EffectTransmitter(this, "Wireless Effect Transmitter");
		randomcolordiode = new RandomDiode(this, "Random Color Diode");
		randomcolorpixel = new RandomPixel(this, "Random Color Pixel");
		transmitterparticle = new ParticleTransmitter(this, "Wireless Particle Transmitter");
		receiverparticle = new ReceiverParticle(this, "Wireless Particle Receiver");
		new SoundTransmitter(this, "Wireless Sound Transmitter");
		viewer = new GenericCustomItem(this, "Wireless Inventory Viewer", RessourceManager.getTexture("invremote.png"));
		
		remote.put(DyeColor.WHITE.getDyeData(), new Remote(this, "Wireless Remote", RessourceManager.getTexture("remote.png")));
		remote.put(DyeColor.BLACK.getDyeData(), new Remote(this, "Black Wireless Remote", RessourceManager.getTexture("blackremote.png")));
		remote.put(DyeColor.BLUE.getDyeData(), new Remote(this, "Blue Wireless Remote", RessourceManager.getTexture("blueremote.png")));
		remote.put(DyeColor.BROWN.getDyeData(), new Remote(this, "Brown Wireless Remote", RessourceManager.getTexture("brownremote.png")));
		remote.put(DyeColor.CYAN.getDyeData(), new Remote(this, "Cyan Wireless Remote", RessourceManager.getTexture("cyanremote.png")));
		remote.put(DyeColor.GRAY.getDyeData(), new Remote(this, "Gray Wireless Remote", RessourceManager.getTexture("grayremote.png")));
		remote.put(DyeColor.GREEN.getDyeData(), new Remote(this, "Green Wireless Remote", RessourceManager.getTexture("greenremote.png")));
		remote.put(DyeColor.LIGHT_BLUE.getDyeData(), new Remote(this, "Light Blue Wireless Remote", RessourceManager.getTexture("lightblueremote.png")));
		remote.put(DyeColor.SILVER.getDyeData(), new Remote(this, "Light Gray Wireless Remote", RessourceManager.getTexture("lightgrayremote.png")));
		remote.put(DyeColor.LIME.getDyeData(), new Remote(this, "Lime Wireless Remote", RessourceManager.getTexture("limeremote.png")));
		remote.put(DyeColor.MAGENTA.getDyeData(), new Remote(this, "Magenta Wireless Remote", RessourceManager.getTexture("magentaremote.png")));
		remote.put(DyeColor.ORANGE.getDyeData(), new Remote(this, "Orange Wireless Remote", RessourceManager.getTexture("orangeremote.png")));
		remote.put(DyeColor.PINK.getDyeData(), new Remote(this, "Pink Wireless Remote", RessourceManager.getTexture("pinkremote.png")));
		remote.put(DyeColor.PURPLE.getDyeData(), new Remote(this, "Purple Wireless Remote", RessourceManager.getTexture("purpleremote.png")));
		remote.put(DyeColor.RED.getDyeData(), new Remote(this, "Red Wireless Remote", RessourceManager.getTexture("redremote.png")));
		remote.put(DyeColor.YELLOW.getDyeData(), new Remote(this, "Yellow Wireless Remote", RessourceManager.getTexture("yellowremote.png")));
		
		getServer().getPluginManager().registerEvents(this, this);
		RecipesManager.createRecipes();
		
		if ((pm.isPluginEnabled("JukeIt"))) {
			JukeIt = true;
			Bukkit.getLogger().info(Colors.GREEN+"[SpootWifi] "+"added compatibility with JukeIt"+Colors.RESET);
		}
		
		try
        {
          Metrics metrics = new Metrics(this);
          metrics.start();
          Bukkit.getLogger().info(Colors.GREEN+"[SpootWifi] "+"Metrics enabled"+Colors.RESET);
        } catch (IOException e) {
          Bukkit.getLogger().info(Colors.RED+"[SpootWifi] "+"Couldn't up Metrics"+Colors.RESET);
        }
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		SpoutBlock block = (SpoutBlock)e.getBlock();
		
		if(block.getCustomBlock() != null && !e.isCancelled())
		{
			if(block.getCustomBlock() instanceof Configurable)
			{
				if(block.getCustomBlock() instanceof Transmitter)
				{
					save.addTransmitter(ItemSave.getChannel(e.getPlayer().getItemInHand()), block, e.getPlayer());
					save.getChannel(block).getSWBlock(block).getStorage().add(new SWStorage(e.getPlayer().getItemInHand()));
				}
				else if(block.getCustomBlock() instanceof Receiver)
				{
					save.addReceiver(ItemSave.getChannel(e.getPlayer().getItemInHand()), block, e.getPlayer());
					save.getChannel(block).getSWBlock(block).getStorage().add(new SWStorage(e.getPlayer().getItemInHand()));
				}
				else
				{
					storage.addBlock(block);
					storage.getSWBlock(block).getStorage().add(new SWStorage(e.getPlayer().getItemInHand()));
				}
				
				((Configurable)block.getCustomBlock()).onPlaced(block, e.getPlayer(), new SWStorage(e.getPlayer().getItemInHand()));
				
				if(ItemSave.getOption(e.getPlayer().getItemInHand(), "AutoChannel") == 1)
				{
					e.getPlayer().setItemInHand(ItemSave.setChannel(e.getPlayer().getItemInHand(), ItemSave.getChannel(e.getPlayer().getItemInHand())+1));
				}
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		SpoutBlock block = (SpoutBlock)e.getBlock();
		
		if(block.getCustomBlock() != null)
		{
			if(!canInteract(e.getPlayer(), block))
			{
				e.setCancelled(true);
				return;
			}
			
			if(block.getCustomBlock() instanceof Configurable) {
				SWStorage sws = new SWStorage();
				if(block.getCustomBlock() instanceof Transmitter)
				{
					sws = save.getChannel(block).getSWBlock(block).getStorage();
					((Configurable)block.getCustomBlock()).onBreaked(block, e.getPlayer(), save.getChannel(block).getSWBlock(block).getStorage());
					save.rmvTransmitter(block);
				}
				else if(block.getCustomBlock() instanceof  Receiver)
				{
					sws = save.getChannel(block).getSWBlock(block).getStorage();
					((Configurable)block.getCustomBlock()).onBreaked(block, e.getPlayer(), save.getChannel(block).getSWBlock(block).getStorage());
					save.rmvReceiver(block);
				}
				else
				{
					sws = storage.getSWBlock(block).getStorage();
					((Configurable)block.getCustomBlock()).onBreaked(block, e.getPlayer(), storage.getSWBlock(block).getStorage());
					storage.removeBlock(block);
				}
				
				if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					Item i = (Item)new SWEntityItem(e.getBlock().getWorld(), e.getBlock().getLocation().add(0.5,0.5,0.5), new SpoutItemStack(block.getCustomBlock())).getBukkitEntity();
					e.setCancelled(true);
					e.getBlock().breakNaturally(new ItemStack(0));
					SpoutManager.getMaterialManager().removeBlockOverride(block);
					SWStorage itemstorage = new SWStorage(i.getItemStack());
					itemstorage.add(sws);
					i.setItemStack(ItemSave.setNBT(i.getItemStack(), itemstorage));
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPiston(BlockPistonExtendEvent e)
	{
		for(Block b : e.getBlocks())
		{
			SpoutBlock block = (SpoutBlock)b;
		
			if(block.getCustomBlock() != null)
			{
				if(block.getCustomBlock() instanceof Configurable || block.getCustomBlock() instanceof Simple)
				{
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPiston(BlockPistonRetractEvent e)
	{
		SpoutBlock block = (SpoutBlock)e.getBlock().getRelative(e.getDirection(), 2);
		
		if(block.getCustomBlock() != null)
		{
			if(block.getCustomBlock() instanceof Configurable || block.getCustomBlock() instanceof Simple)
			{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent e)
	{
		checkPower(e.getBlock());
	}
	
	public void checkPower(Block b)
	{
		SpoutBlock block = (SpoutBlock)b;
		
		if(block.getCustomBlock() != null)
		{
			if(block.getCustomBlock() instanceof Transmitter || block.getCustomBlock() instanceof Simple)
			{
				if(isPowered(block))
				{
					setPoweredBlock(block, true);
				}
				else
				{
					setPoweredBlock(block, false);
				}
			}
		}	
	}
	
	public static void setPoweredBlock(SpoutBlock b, boolean powered)
	{
		if(b.getCustomBlock() != null)
		{
			if(b.getCustomBlock() instanceof Transmitter)
			{
				((Transmitter)b.getCustomBlock()).onPowered(b, powered);
			}
			if(b.getCustomBlock() instanceof Receiver)
			{
				((Receiver)b.getCustomBlock()).onPowered(b, powered);
			}
			if(b.getCustomBlock() instanceof Simple)
			{
				((Simple)b.getCustomBlock()).onPowered(b, powered);
			}
		}
	}
	
	public static boolean isPowered(Block b)
	{
		return (b.isBlockIndirectlyPowered()
				|| b.isBlockPowered()
				|| b.isBlockFaceIndirectlyPowered(BlockFace.NORTH)
				|| b.isBlockFaceIndirectlyPowered(BlockFace.SOUTH)
				|| b.isBlockFaceIndirectlyPowered(BlockFace.EAST)
				|| b.isBlockFaceIndirectlyPowered(BlockFace.WEST)
				|| b.isBlockFaceIndirectlyPowered(BlockFace.DOWN)
				|| b.isBlockFacePowered(BlockFace.UP)
				|| b.isBlockFacePowered(BlockFace.DOWN)
				|| b.isBlockFacePowered(BlockFace.NORTH)
				|| b.isBlockFacePowered(BlockFace.SOUTH)
				|| b.isBlockFacePowered(BlockFace.EAST)
				|| b.isBlockFacePowered(BlockFace.WEST));
	}
	
	public static boolean canInteract(Player p, Block b)
	{
		if(save.hasChannel(b))
		{
			String owner = save.getChannel(b).getSWBlock(b).getStorage().getString("OwnerName");
			if(p.isOp() || owner.equals("") || owner.equals(p.getName()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	
	public static String getOwner(Block b)
	{
		if(save.hasChannel(b) && !save.getChannel(b).getSWBlock(b).getStorage().getString("OwnerName").equals(""))
		{
			String owner = save.getChannel(b).getSWBlock(b).getStorage().getString("OwnerName");
			return owner;
		}
		else
		{
			return "???";
		}
	}
	
	public static void setOwner(Block b, String name)
	{
		if(save.hasChannel(b))
		{
			save.getChannel(b).getSWBlock(b).getStorage().addString("OwnerName", name);
		}
		else
		{
			save.addBlock(0, b);
			save.getChannel(b).getSWBlock(b).getStorage().addString("OwnerName", name);
		}
	}
	
	public static String getOwner(SWStorage storage)
	{
		if(!storage.getString("OwnerName").equals(""))
		{
			String owner = storage.getString("OwnerName");
			return owner;
		}
		else
		{
			return "???";
		}
	}
	
	public static void setOwner(SWStorage storage, String name)
	{
		storage.addString("OwnerName", name);
	}
	
	@EventHandler
	public void onConfigurableInteract(PlayerInteractEvent e)
	{
		if(e.getItem() != null)
		{
			SpoutItemStack sis = new SpoutItemStack(e.getItem());
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR) && sis.getMaterial() instanceof Configurable)
			{
				((Configurable)sis.getMaterial()).onRightClick(e.getPlayer(), e.getItem());
			}
		}
	}
	
	@EventHandler
	public void onRemoteClick(PlayerInteractEvent e)
	{
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			ItemStack is = e.getPlayer().getItemInHand();
			if(is != null) {
				SpoutItemStack sis = new SpoutItemStack(is);
				if(is != null && sis.getMaterial() instanceof Remote) {
					((Remote)sis.getMaterial()).onClick((SpoutPlayer)e.getPlayer(), is, (SpoutBlock)e.getClickedBlock());
				}
			}
		}
	}
	
	@EventHandler
	public void onRemoteUnclick(KeyReleasedEvent e)
	{
		if(e.getKey().equals(Keyboard.MOUSE_RIGHT)) {
			ItemStack is = e.getPlayer().getItemInHand();
			if(is != null) {
				SpoutItemStack sis = new SpoutItemStack(is);
				if(sis.getMaterial() instanceof Remote) {
					((Remote)sis.getMaterial()).onUnclick((SpoutPlayer)e.getPlayer(), is);
				}
			}
		}
	}
	
	@EventHandler
	public void onItemDropped(KeyPressedEvent e)
	{
		if(e.getKey().equals(e.getPlayer().getDropItemKey()) && e.getPlayer().getItemInHand() != null && e.getPlayer().getCurrentScreen().getType().equals(WidgetType.InGameScreen)) {
			ItemStack is = e.getPlayer().getItemInHand();
			SpoutItemStack sis = new SpoutItemStack(is);
			if(sis.getMaterial() instanceof CustomItem) {
				if(((CustomItem)sis.getMaterial()).getPlugin().equals(SpootWifi.plugin)) {
					Item i = (Item)new SWEntityItem(e.getPlayer().getWorld(), e.getPlayer().getEyeLocation(), e.getPlayer().getItemInHand()).getBukkitEntity();
					e.getPlayer().setItemInHand(new ItemStack(0));
					i.setPickupDelay(20);
					i.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(0.5));
				}
			}
			if(sis.getMaterial() instanceof CustomBlock) {
				if(((CustomBlock)sis.getMaterial()).getPlugin().equals(SpootWifi.plugin)) {
					Item i = (Item)new SWEntityItem(e.getPlayer().getWorld(), e.getPlayer().getEyeLocation(), e.getPlayer().getItemInHand()).getBukkitEntity();
					e.getPlayer().setItemInHand(new ItemStack(0));
					i.setPickupDelay(20);
					i.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(0.5));
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerItemDropped(PlayerDropItemEvent e)
	{
		ItemStack is = e.getItemDrop().getItemStack();
		SpoutItemStack sis = new SpoutItemStack(is);
		if(sis.getMaterial() instanceof CustomItem) {
			if(((CustomItem)sis.getMaterial()).getPlugin().equals(SpootWifi.plugin)) {
				e.setCancelled(true);
			}
		}
		if(sis.getMaterial() instanceof CustomBlock) {
			if(((CustomBlock)sis.getMaterial()).getPlugin().equals(SpootWifi.plugin)) {
				e.setCancelled(true);
			}
		}
	}

}
