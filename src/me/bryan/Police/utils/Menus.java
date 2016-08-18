package me.bryan.Police.utils;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import me.bryan.Police.Main;
import me.bryan.Police.Items.HandCuffs;

public class Menus implements Listener{
	private Inventory inv;
	private ItemStack m, k, r, g, t, a, d;
	Main main;
	public Menus(Main main) {
		inv = Bukkit.getServer().createInventory(null, 9, "Jail select");
		m = createItem(DyeColor.RED, ChatColor.RED + "Murder");
		k = createItem(DyeColor.GREEN, ChatColor.GOLD + "Bank Robbery");
		r = createItem(DyeColor.LIGHT_BLUE, ChatColor.AQUA + "Robbery");
		g = createItem(DyeColor.GRAY, ChatColor.GRAY + "Unlicenced Guns");
		t = createItem(DyeColor.BROWN, ChatColor.GREEN + "Tresspassing");
		a = createItem(DyeColor.PURPLE, ChatColor.LIGHT_PURPLE + "Assault");
		d = createItem(DyeColor.ORANGE, ChatColor.YELLOW + "Disrupting Peace");
		
		inv.addItem(m);
		inv.addItem(k);
		inv.addItem(r);
		inv.addItem(g);
		inv.addItem(t);
		inv.addItem(a);
		inv.addItem(d);
		this.main = main;
	}
	private ItemStack createItem(DyeColor dc, String name) {
		ItemStack i = new ItemStack(new Wool(dc).toItemStack(1));
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("Jail the person for "+ name.toLowerCase()));
		i.setItemMeta(im);
		return i;
	}
	public void showJailInv(Player p) {
		p.openInventory(inv);
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if(!event.getInventory().equals(inv)) return;
		
		if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Murder")) {
			for(String name : HandCuffs.getCuffed()) {
			Bukkit.dispatchCommand(p, "jail " + name + "15m");
			HandCuffs.setCuffed(null);
			}
			
		} else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Bank Robbery")) {
			for(String name : HandCuffs.getCuffed()) {
				Bukkit.dispatchCommand(p, "jail " + name + "15m");
				HandCuffs.setCuffed(null);
				}
		}else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Robbery")) {
			for(String name : HandCuffs.getCuffed()) {
				Bukkit.dispatchCommand(p, "jail " + name + "15m");
				HandCuffs.setCuffed(null);
				}
		}
		else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Unlicenced Guns")) {
			for(String name : HandCuffs.getCuffed()) {
				Bukkit.dispatchCommand(p, "jail " + name + "5m");
				HandCuffs.setCuffed(null);
				}
		} else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Tresspassing")) {
			for(String name : HandCuffs.getCuffed()) {
				Bukkit.dispatchCommand(p, "jail " + name + "10m");
				HandCuffs.setCuffed(null);
				}
		}else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Assault")) {
			for(String name : HandCuffs.getCuffed()) {
				Bukkit.dispatchCommand(p, "jail " + name + "15m");
				HandCuffs.setCuffed(null);
				}
		} else if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Bank Robbery")) {
			for(String name : HandCuffs.getCuffed()) {
				Bukkit.dispatchCommand(p, "jail " + name + "10m");
				HandCuffs.setCuffed(null);
				}
		}
	}
}


