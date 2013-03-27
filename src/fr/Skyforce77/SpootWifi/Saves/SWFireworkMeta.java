package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.meta.FireworkMeta;

public class SWFireworkMeta implements Serializable{

	private static final long serialVersionUID = 9194108632477754888L;
	ArrayList<SWFireworkEffect> map = new ArrayList<SWFireworkEffect>();
	Integer force;
	
	public SWFireworkMeta(FireworkMeta fm)
	{
		for(FireworkEffect effect : fm.getEffects())
		{
			map.add(new SWFireworkEffect(effect));
		}
		force = fm.getPower();
	}
	
	public ArrayList<SWFireworkEffect> getEffect()
	{
		return map;
	}
	
	public Integer getPower()
	{
		return force;
	}

}
