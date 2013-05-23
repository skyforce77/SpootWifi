package fr.Skyforce77.SpootWifi.Materials.Basics;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

import fr.Skyforce77.SpootWifi.GUI.ChooseGui;
import fr.Skyforce77.SpootWifi.Saves.ItemSave;
import fr.Skyforce77.SpootWifi.Saves.SWStorage;

public class Configurable extends GenericCustomBlock{
	
	private boolean hasgui = true;
	private Class<?> gui = ChooseGui.class;

	public Configurable(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, isOpaque, design, rotate, mirroredRotate, fullRotate);
	}

	public Configurable(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design, boolean rotate) {
		super(plugin, name, isOpaque, design, rotate);
	}

	public Configurable(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design) {
		super(plugin, name, isOpaque, design);
	}

	public Configurable(Plugin plugin, String name, boolean isOpaque,
			boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		super(plugin, name, isOpaque, rotate, mirroredRotate, fullRotate);
	}

	public Configurable(Plugin plugin, String name, boolean isOpaque,
			boolean rotate) {
		super(plugin, name, isOpaque, rotate);
	}

	public Configurable(Plugin plugin, String name, boolean isOpaque) {
		super(plugin, name, isOpaque);
	}

	public Configurable(Plugin plugin, String name, int blockId,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, blockId, design, rotate, mirroredRotate, fullRotate);
	}

	public Configurable(Plugin plugin, String name, int blockId,
			BlockDesign design, boolean rotate) {
		super(plugin, name, blockId, design, rotate);
	}

	public Configurable(Plugin plugin, String name, int blockId,
			BlockDesign design) {
		super(plugin, name, blockId, design);
	}

	public Configurable(Plugin plugin, String name, int blockId,
			boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		super(plugin, name, blockId, rotate, mirroredRotate, fullRotate);
	}

	public Configurable(Plugin plugin, String name, int blockId, boolean rotate) {
		super(plugin, name, blockId, rotate);
	}

	public Configurable(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, blockId, metadata, design, rotate, mirroredRotate,
				fullRotate);
	}

	public Configurable(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design, boolean rotate) {
		super(plugin, name, blockId, metadata, design, rotate);
	}

	public Configurable(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design) {
		super(plugin, name, blockId, metadata, design);
	}

	public Configurable(Plugin arg0, String arg1, int arg2, int arg3,
			boolean arg4, boolean arg5, boolean arg6) {
		super(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	public Configurable(Plugin plugin, String name, int blockId, int metadata,
			boolean rotate) {
		super(plugin, name, blockId, metadata, rotate);
	}

	public Configurable(Plugin plugin, String name, int blockId, int metadata) {
		super(plugin, name, blockId, metadata);
	}

	public Configurable(Plugin plugin, String name, int blockId) {
		super(plugin, name, blockId);
	}

	public Configurable(Plugin plugin, String name) {
		super(plugin, name);
	}

	public void onRightClick(Player player, ItemStack item) {
		if(hasgui)
		{
			try {
				gui.getConstructor(String.class, SpoutPlayer.class, SWStorage.class).newInstance(this.getName(), (SpoutPlayer)player, new SWStorage(ItemSave.getNBT(item)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onPlaced(SpoutBlock b, Player p, SWStorage storage) {}
	public void onBreaked(SpoutBlock b, Player p, SWStorage storage) {}
	
	public Boolean hasGui() {
		return hasgui;
	}
	
	public void setGui(Boolean gui) {
		this.hasgui = gui;
	}
	
	public void setGui(Class<?> gui) {
		this.gui = gui;
	}
	
	public void openGui(SpoutPlayer p, SWStorage storage)
	{
		if(hasgui)
		{
			try {
				gui.getConstructor(String.class, SpoutPlayer.class, SWStorage.class).newInstance(this.getName(), p, storage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
