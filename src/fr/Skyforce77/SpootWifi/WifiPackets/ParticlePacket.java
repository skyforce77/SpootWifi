package fr.Skyforce77.SpootWifi.WifiPackets;

import fr.Skyforce77.SpootWifi.Saves.SWParticle;

public class ParticlePacket extends WifiPacket{

	public ParticlePacket(SWParticle particle) {
		super("ParticlePacket");
		this.getData().addObject("particle", particle);
	}
	
	public SWParticle getParticle() {
		return (SWParticle)this.getData().getObject("particle");
	}

}
