//auteur: Skyforce77
//All Rights Reserved
//site internet: moreblocks.tk

package fr.Skyforce77.SpootWifi.Utils;

import org.bukkit.ChatColor;

public class Colors {

	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";
	
	public static String setColors(String msg) {
		String colorMsg = "";

		for (int i = 0; i < msg.length(); i++) {
			char indexCh = msg.charAt(i);

			if ((indexCh == '\u00A7' || indexCh == '\u0026')
					&& i < msg.length() - 1) {
				char indexNext = msg.charAt(++i);

				switch (indexNext) {
				case '0':
					colorMsg = colorMsg + ChatColor.BLACK;
					break;
				case '1':
					colorMsg = colorMsg + ChatColor.DARK_BLUE;
					break;
				case '2':
					colorMsg = colorMsg + ChatColor.DARK_GREEN;
					break;
				case '3':
					colorMsg = colorMsg + ChatColor.DARK_AQUA;
					break;
				case '4':
					colorMsg = colorMsg + ChatColor.DARK_RED;
					break;
				case '5':
					colorMsg = colorMsg + ChatColor.DARK_PURPLE;
					break;
				case '6':
					colorMsg = colorMsg + ChatColor.GOLD;
					break;
				case '7':
					colorMsg = colorMsg + ChatColor.GRAY;
					break;
				case '8':
					colorMsg = colorMsg + ChatColor.DARK_GRAY;
					break;
				case '9':
					colorMsg = colorMsg + ChatColor.BLUE;
					break;
				case 'a':
					colorMsg = colorMsg + ChatColor.GREEN;
					break;
				case 'b':
					colorMsg = colorMsg + ChatColor.AQUA;
					break;
				case 'c':
					colorMsg = colorMsg + ChatColor.RED;
					break;
				case 'd':
					colorMsg = colorMsg + ChatColor.LIGHT_PURPLE;
					break;
				case 'e':
					colorMsg = colorMsg + ChatColor.YELLOW;
					break;
				case 'f':
					colorMsg = colorMsg + ChatColor.WHITE;
					break;
				case 'k':
					colorMsg = colorMsg + ChatColor.MAGIC;
					break;
				case 'l':
					colorMsg = colorMsg + ChatColor.BOLD;
					break;
				case 'm':
					colorMsg = colorMsg + ChatColor.STRIKETHROUGH;
					break;
				case 'n':
					colorMsg = colorMsg + ChatColor.UNDERLINE;
					break;
				case 'o':
					colorMsg = colorMsg + ChatColor.ITALIC;
					break;
				case 'r':
					colorMsg = colorMsg + ChatColor.RESET;
					break;
				default:
					colorMsg = colorMsg + indexCh + indexNext;
					break;
				}
			} else {
				colorMsg = colorMsg + indexCh;
			}
		}

		return colorMsg;
	}
}
