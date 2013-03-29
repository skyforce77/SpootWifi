package fr.Skyforce77.SpootWifi;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

public class RecipesManager {

	public static void createRecipes()
	{
		SpoutShapedRecipe remote = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.remote))
		.shape("A","B")
		.setIngredient('B', MaterialData.ironIngot)
		.setIngredient('A', MaterialData.redstoneTorchOn);
		SpoutManager.getMaterialManager().registerSpoutRecipe(remote);
		
		SpoutShapedRecipe sniffer = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.sniffer))
		.shape("ABA")
		.setIngredient('A', MaterialData.redstoneTorchOn)
		.setIngredient('B', SpootWifi.remote);
		SpoutManager.getMaterialManager().registerSpoutRecipe(sniffer);
		
		SpoutShapedRecipe ironstick = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.ironstick, 4))
		.shape("A","A")
		.setIngredient('A', MaterialData.ironIngot);
		SpoutManager.getMaterialManager().registerSpoutRecipe(ironstick);
		
		SpoutShapedRecipe radio = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.radio))
		.shape("A","B")
		.setIngredient('A', SpootWifi.ironstick)
		.setIngredient('B', MaterialData.ironIngot);
		SpoutManager.getMaterialManager().registerSpoutRecipe(radio);
		
		SpoutShapedRecipe fireplace = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.fireplace))
		.shape("A","B","C")
		.setIngredient('A', MaterialData.netherrack)
		.setIngredient('B', MaterialData.cobblestone)
		.setIngredient('C', MaterialData.redstone);
		SpoutManager.getMaterialManager().registerSpoutRecipe(fireplace);
		
		SpoutShapedRecipe lightningblock = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.lightningblock))
		.shape("A","B")
		.setIngredient('A', SpootWifi.ironstick)
		.setIngredient('B', MaterialData.goldBlock);
		SpoutManager.getMaterialManager().registerSpoutRecipe(lightningblock);
		
		SpoutShapedRecipe transmitter = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.transmitter))
		.shape("A","B")
		.setIngredient('A', MaterialData.redstoneTorchOn)
		.setIngredient('B', MaterialData.ironBlock);
		SpoutManager.getMaterialManager().registerSpoutRecipe(transmitter);
		
		SpoutShapedRecipe receiver = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receiver))
		.shape("A","B")
		.setIngredient('A', SpootWifi.ironstick)
		.setIngredient('B', MaterialData.ironBlock);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receiver);
		
		SpoutShapedRecipe transmitternotification = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.transmitternotification))
		.shape("A","B")
		.setIngredient('A', MaterialData.book)
		.setIngredient('B', SpootWifi.transmitter);
		SpoutManager.getMaterialManager().registerSpoutRecipe(transmitternotification);
		
		SpoutShapedRecipe transmittercolor = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.transmittercolor))
		.shape("A","B")
		.setIngredient('A', MaterialData.getMaterial(351))
		.setIngredient('B', SpootWifi.transmitter);
		SpoutManager.getMaterialManager().registerSpoutRecipe(transmittercolor);
		
		SpoutShapedRecipe transmitterfirework = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.transmitterfirework))
		.shape("A","B")
		.setIngredient('A', MaterialData.fireworkcharge)
		.setIngredient('B', SpootWifi.transmitter);
		SpoutManager.getMaterialManager().registerSpoutRecipe(transmitterfirework);
		
		SpoutShapedRecipe transmitterjukebox = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.transmitterjukebox))
		.shape("A","B")
		.setIngredient('A', MaterialData.noteblock)
		.setIngredient('B', SpootWifi.transmitter);
		SpoutManager.getMaterialManager().registerSpoutRecipe(transmitterjukebox);
		
		SpoutShapedRecipe receiverdiode = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receiverdiode))
		.shape("ABC")
		.setIngredient('A', MaterialData.redWool)
		.setIngredient('B', SpootWifi.receiver)
		.setIngredient('C', MaterialData.greenWool);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receiverdiode);
		
		SpoutShapedRecipe receivercoloreddiode = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receivercoloreddiode))
		.shape("A","B")
		.setIngredient('A', MaterialData.blueWool)
		.setIngredient('B', SpootWifi.receiver);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receivercoloreddiode);
		
		SpoutShapedRecipe receiverdisplay = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receiverdisplay))
		.shape("A","B")
		.setIngredient('A', MaterialData.whiteWool)
		.setIngredient('B', SpootWifi.receiver);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receiverdisplay);
		
		SpoutShapedRecipe receiverpixel = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receiverpixel))
		.shape("A","ABA","A")
		.setIngredient('A', MaterialData.whiteWool)
		.setIngredient('B', SpootWifi.receiver);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receiverpixel);
		
		SpoutShapedRecipe receivercoloredpixel = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receivercoloredpixel))
		.shape("A","BDC", "E")
		.setIngredient('A', MaterialData.redWool)
		.setIngredient('B', MaterialData.greenWool)
		.setIngredient('C', MaterialData.blueWool)
		.setIngredient('E', MaterialData.whiteWool)
		.setIngredient('D', SpootWifi.receiverpixel);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receivercoloredpixel);
		
		SpoutShapedRecipe receiverfirework = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receiverfirework))
		.shape("A","B")
		.setIngredient('A', MaterialData.fireworkcharge)
		.setIngredient('B', SpootWifi.receiver);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receiverfirework);
		
		SpoutShapedRecipe receiverjukebox = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receiverjukebox))
		.shape("A","B")
		.setIngredient('A', MaterialData.jukebox)
		.setIngredient('B', SpootWifi.receiver);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receiverjukebox);
		
		SpoutShapedRecipe receiverjukeboxextended = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.receiverjukeboxextended))
		.shape("A","B")
		.setIngredient('A', MaterialData.noteblock)
		.setIngredient('B', SpootWifi.receiverjukebox);
		SpoutManager.getMaterialManager().registerSpoutRecipe(receiverjukeboxextended);
		
		SpoutShapedRecipe wirelesslamp = new SpoutShapedRecipe(new SpoutItemStack(SpootWifi.wirelesslamp))
		.shape("A","B")
		.setIngredient('A', MaterialData.redstoneLampOff)
		.setIngredient('B', SpootWifi.receiver);
		SpoutManager.getMaterialManager().registerSpoutRecipe(wirelesslamp);
	}

}
