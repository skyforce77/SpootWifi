package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;

public class SWFireworkEffect implements Serializable{

	private static final long serialVersionUID = 3491855910758154434L;
	ArrayList<Integer> colors = new ArrayList<Integer>();
	ArrayList<Integer> fadecolors = new ArrayList<Integer>();
	String type;
	boolean flicker;
	boolean trail;
	
	public SWFireworkEffect(FireworkEffect fm)
	{
		for(Color c : fm.getColors())
		{
			colors.add(c.asRGB());
		}
		for(Color c : fm.getFadeColors())
		{
			fadecolors.add(c.asRGB());
		}
		type = fm.getType().toString();
		flicker = fm.hasFlicker();
		trail = fm.hasTrail();
	}
	
	public FireworkEffect getEffect()
	{
		Builder b = FireworkEffect.builder().flicker(flicker).trail(trail).with(Type.valueOf(type));
		for(Integer i : colors)
		{
			b = b.withColor(Color.fromRGB(i));
		}
		for(Integer i : fadecolors)
		{
			b = b.withFade(Color.fromRGB(i));
		}
		return b.build();
	}

}
