package fr.Skyforce77.SpootWifi.Saves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;

public class MFItemStack implements Serializable{

	private static final long serialVersionUID = 8999544795592008007L;
	
	private Integer AMOUNT = null;
    private Integer ID = null;
    private Short DURABILITY = null;
    private ArrayList<Integer> ENCHANTS = new ArrayList<Integer>();
    private ArrayList<Integer> ENCHANTS_LEVELS = new ArrayList<Integer>();
    private ArrayList<Integer> ENCHANTS_STORAGE = new ArrayList<Integer>();
    private ArrayList<Integer> ENCHANTS_LEVELS_STORAGE = new ArrayList<Integer>();
    private String META_DISPLAYNAME = null;
    private List<String> META_LORE = null;
    private Byte META_DATA = null;
    private String META_TYPE = "NULL";
    private String AUTHOR = null;
    private String TITLE = null;
    private List<String> PAGES = null;
    private int COLOR_RED = 0;
    private int COLOR_GREEN = 0;
    private int COLOR_BLUE = 0;
    private String SKULL_OWNER = null;
    private Boolean MAP_SCALING = null;
    private Map<String, Object> FIREWORK_EFFECT = null;
    private List<Map<String, Object>> POTION_EFFECTS = new ArrayList<Map<String, Object>>();
	
	public MFItemStack(ItemStack is)
	{
		AMOUNT = is.getAmount();
		ID = is.getTypeId();
		DURABILITY = is.getDurability();
		META_DATA = is.getData().getData();
		setEnchantments(is.getEnchantments(), false);
		
		if(is.getItemMeta() != null)
		{
			if(is.getItemMeta() instanceof BookMeta)
			{
				BookMeta bm = (BookMeta)is.getItemMeta();
				AUTHOR = bm.getAuthor();
				PAGES = bm.getPages();
				TITLE = bm.getTitle();
				META_TYPE = "BOOK";
			}
			else if(is.getItemMeta() instanceof LeatherArmorMeta)
			{
				LeatherArmorMeta bm = (LeatherArmorMeta)is.getItemMeta();
				COLOR_RED = bm.getColor().getRed();
				COLOR_GREEN = bm.getColor().getGreen();
				COLOR_BLUE = bm.getColor().getBlue();
				META_TYPE = "ARMOR";
			}
			else if(is.getItemMeta() instanceof SkullMeta)
			{
				SkullMeta bm = (SkullMeta)is.getItemMeta();
				SKULL_OWNER = bm.getOwner();
				META_TYPE = "SULL";
			}
			else if(is.getItemMeta() instanceof MapMeta)
			{
				MapMeta bm = (MapMeta)is.getItemMeta();
				MAP_SCALING = bm.isScaling();
				META_TYPE = "MAP";
			}
			else if(is.getItemMeta() instanceof EnchantmentStorageMeta)
			{
				EnchantmentStorageMeta bm = (EnchantmentStorageMeta)is.getItemMeta();
				setEnchantments(bm.getStoredEnchants(), true);
				META_TYPE = "ENCH_STORAGE";
			}
			else if(is.getItemMeta() instanceof FireworkEffectMeta)
			{
				FireworkEffectMeta bm = (FireworkEffectMeta)is.getItemMeta();
				FIREWORK_EFFECT = bm.getEffect().serialize();
				META_TYPE = "FIREWORK_EFFECT";
			}
			else if(is.getItemMeta() instanceof PotionMeta)
			{
				PotionMeta bm = (PotionMeta)is.getItemMeta();
				for(PotionEffect pe : bm.getCustomEffects())
				{
					POTION_EFFECTS.add(pe.serialize());
				}
				META_TYPE = "POTION_EFFECTS";
			}
			else
			{
				META_TYPE = "NORMAL";
			}
			
			META_DISPLAYNAME = is.getItemMeta().getDisplayName();
			META_LORE = is.getItemMeta().getLore();
		}

	}
	
	public ItemStack toItemStack()
	{
		ItemStack is = new ItemStack(ID, AMOUNT);
		is.setDurability(DURABILITY);
		is.setData(new MaterialData(ID, META_DATA));
		ItemMeta im = is.getItemMeta();
		
		if(!META_TYPE.equals("NULL"))
		{
			if(META_TYPE.equals("BOOK"))
			{
				BookMeta bm = (BookMeta)new ItemStack(ID).getItemMeta();
				bm.setAuthor(AUTHOR);
				bm.setTitle(TITLE);
				bm.setPages(PAGES);
				im = bm;
			}
			else if(META_TYPE.equals("ARMOR"))
			{
				LeatherArmorMeta bm = (LeatherArmorMeta)new ItemStack(ID).getItemMeta();
				bm.setColor(Color.fromRGB(COLOR_RED, COLOR_GREEN, COLOR_BLUE));
				im = bm;
			}
			else if(META_TYPE.equals("SKULL"))
			{
				SkullMeta bm = (SkullMeta)new ItemStack(ID).getItemMeta();
				bm.setOwner(SKULL_OWNER);
				im = bm;
			}
			else if(META_TYPE.equals("MAP"))
			{
				MapMeta bm = (MapMeta)new ItemStack(ID).getItemMeta();
				bm.setScaling(MAP_SCALING);
				im = bm;
			}
			else if(META_TYPE.equals("ENCH_STORAGE"))
			{
				EnchantmentStorageMeta bm = (EnchantmentStorageMeta)new ItemStack(ID).getItemMeta();
				im = addEnchantments(bm);
			}
			else if(META_TYPE.equals("FIREWORK_EFFECT"))
			{
				FireworkEffectMeta bm = (FireworkEffectMeta)new ItemStack(ID).getItemMeta();
				bm.setEffect((FireworkEffect) FireworkEffect.deserialize(FIREWORK_EFFECT));
				im = bm;
			}
			else if(META_TYPE.equals("POTION_EFFECTS"))
			{
				PotionMeta bm = (PotionMeta)new ItemStack(ID).getItemMeta();
				for(Map<String, Object> pe : POTION_EFFECTS)
				{
					bm.addCustomEffect(new PotionEffect(pe), true);
				}
				im = bm;
			}

			im.setDisplayName(META_DISPLAYNAME);
			im.setLore(META_LORE);
			is.setItemMeta(im);
		}
		
		is.addUnsafeEnchantments(getEnchantments(false));
		
		return is;
	}
	
	private Map<Enchantment, Integer> getEnchantments(Boolean storage)
	{
		Map<Enchantment, Integer> enchants = new HashMap<Enchantment, Integer>();
		if(!storage)
		{
			for(Integer en : ENCHANTS)
			{
				enchants.put(Enchantment.getById(en), ENCHANTS_LEVELS.get(ENCHANTS.indexOf(en)));
			}
		}
		else
		{
			for(Integer en : ENCHANTS_STORAGE)
			{
				enchants.put(Enchantment.getById(en), ENCHANTS_LEVELS_STORAGE.get(ENCHANTS_STORAGE.indexOf(en)));
			}
		}
		return enchants;
	}
	
	private void setEnchantments(Map<Enchantment, Integer> enchants, boolean storage)
	{
		if(!storage)
		{
			for(Enchantment en : enchants.keySet())
			{
				ENCHANTS.add(en.getId());
				ENCHANTS_LEVELS.add(enchants.get(en));
			}
		}
		else
		{
			for(Enchantment en : enchants.keySet())
			{
				ENCHANTS_STORAGE.add(en.getId());
				ENCHANTS_LEVELS_STORAGE.add(enchants.get(en));
			}
		}
	}
	
	private EnchantmentStorageMeta addEnchantments(EnchantmentStorageMeta esm)
	{
		for(Integer en : ENCHANTS_STORAGE)
		{
			esm.addStoredEnchant(Enchantment.getById(en), ENCHANTS_LEVELS_STORAGE.get(ENCHANTS_STORAGE.indexOf(en)), true);
		}
		return esm;
	}
	
	public int getTypeId()
	{
		return ID;
	}
	
	public Material getType()
	{
		return Material.getMaterial(ID);
	}
	
	public byte getData()
	{
		return META_DATA;
	}
	
	public int getAmount()
	{
		return AMOUNT;
	}
	
	public short getDurability()
	{
		return DURABILITY;
	}

}
