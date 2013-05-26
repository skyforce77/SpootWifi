package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import fr.Skyforce77.SpootWifi.Effects.CustomEffect;
import fr.Skyforce77.SpootWifi.Effects.ParticleType;

public class SWParticle implements Serializable{

	private static final long serialVersionUID = -1453160536550433512L;
	
	private String particle;
	private float speed;
	private int data;
	private float offset;
	private int amount = 10;

	public SWParticle(ParticleType type, int amount, float speed, float offset, int data)
	{
		this.particle = type.toString();
		this.amount = amount;
		this.speed = speed;
		this.data = data;
		this.offset = offset;
	}
	
	public void spawn(Location loc)
	{
		CustomEffect.createEffect(ParticleType.valueOf(particle), loc, new Vector(offset,offset,offset), speed, amount, data);
	}

}
