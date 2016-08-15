package me.bryan.Police.utils;

import me.bryan.Police.Main;

public class ChatColor {
	Main main;
	public ChatColor(Main main) {
		this.main = main;
	}
	public static String color(String msg) {
		return org.bukkit.ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static String heading(String msg) {
		return "";
	}

}
