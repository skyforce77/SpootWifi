package fr.Skyforce77.SpootWifi.Materials.Basics;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

public class Simple extends GenericCustomBlock {

	public Simple(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, isOpaque, design, rotate, mirroredRotate, fullRotate);
	}

	public Simple(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design, boolean rotate) {
		super(plugin, name, isOpaque, design, rotate);
	}

	public Simple(Plugin plugin, String name, boolean isOpaque,
			BlockDesign design) {
		super(plugin, name, isOpaque, design);
	}

	public Simple(Plugin plugin, String name, boolean isOpaque, boolean rotate,
			boolean mirroredRotate, boolean fullRotate) {
		super(plugin, name, isOpaque, rotate, mirroredRotate, fullRotate);
	}

	public Simple(Plugin plugin, String name, boolean isOpaque, boolean rotate) {
		super(plugin, name, isOpaque, rotate);
	}

	public Simple(Plugin plugin, String name, boolean isOpaque) {
		super(plugin, name, isOpaque);
	}

	public Simple(Plugin plugin, String name, int blockId, BlockDesign design,
			boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		super(plugin, name, blockId, design, rotate, mirroredRotate, fullRotate);
	}

	public Simple(Plugin plugin, String name, int blockId, BlockDesign design,
			boolean rotate) {
		super(plugin, name, blockId, design, rotate);
	}

	public Simple(Plugin plugin, String name, int blockId, BlockDesign design) {
		super(plugin, name, blockId, design);
	}

	public Simple(Plugin plugin, String name, int blockId, boolean rotate,
			boolean mirroredRotate, boolean fullRotate) {
		super(plugin, name, blockId, rotate, mirroredRotate, fullRotate);
	}

	public Simple(Plugin plugin, String name, int blockId, boolean rotate) {
		super(plugin, name, blockId, rotate);
	}

	public Simple(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design, boolean rotate, boolean mirroredRotate,
			boolean fullRotate) {
		super(plugin, name, blockId, metadata, design, rotate, mirroredRotate,
				fullRotate);
	}

	public Simple(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design, boolean rotate) {
		super(plugin, name, blockId, metadata, design, rotate);
	}

	public Simple(Plugin plugin, String name, int blockId, int metadata,
			BlockDesign design) {
		super(plugin, name, blockId, metadata, design);
	}

	public Simple(Plugin arg0, String arg1, int arg2, int arg3, boolean arg4,
			boolean arg5, boolean arg6) {
		super(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	public Simple(Plugin plugin, String name, int blockId, int metadata,
			boolean rotate) {
		super(plugin, name, blockId, metadata, rotate);
	}

	public Simple(Plugin plugin, String name, int blockId, int metadata) {
		super(plugin, name, blockId, metadata);
	}

	public Simple(Plugin plugin, String name, int blockId) {
		super(plugin, name, blockId);
	}

	public Simple(Plugin plugin, String name) {
		super(plugin, name);
	}
	
	public void onPowered(SpoutBlock b, boolean powered) {
	}
	
}
