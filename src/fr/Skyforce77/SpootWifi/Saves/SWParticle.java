package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.particle.Particle;

public class SWParticle implements Serializable{

	private static final long serialVersionUID = -1453160536550433512L;
	
	private String name;
	private int maxAge;
	private float scale;
	private float gravity = 0F;
	private float particleRed = -1F;
	private float particleBlue = -1F;
	private float particleGreen = -1F;
	private int amount = 10;

	public SWParticle(Particle part)
	{
		name = part.getName();
		maxAge = part.getMaxAge();
		scale = part.getScale();
		gravity = part.getGravity();
		particleRed = part.getParticleRed();
		particleBlue = part.getParticleBlue();
		particleGreen = part.getParticleGreen();
		amount = part.getAmount();
	}
	
	public void spawn(Location loc)
	{
		Particle part = new Particle(name, loc, new Vector(0,0,0));
		part.setMaxAge(maxAge);
		part.setScale(scale);
		part.setGravity(gravity);
		part.setParticleBlue(particleBlue);
		part.setParticleGreen(particleGreen);
		part.setParticleRed(particleRed);
		part.setAmount(amount);
		part.setRange(100.0D);
		part.spawn();
	}

}
