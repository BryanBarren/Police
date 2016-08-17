package me.bryan.Police;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.bryan.Police.Commands.Bam;
import me.bryan.Police.Commands.Demote;
import me.bryan.Police.Commands.Duty;
import me.bryan.Police.Commands.Promote;
import me.bryan.Police.Commands.Wanted;
import me.bryan.Police.Items.HandCuffs;
import me.bryan.Police.Items.Tazer;
import me.bryan.Police.Listeners.Death;
import me.bryan.Police.Listeners.Respawn;
import me.bryan.Police.Wanted.WantedSystem;
import me.bryan.Police.utils.Freeze;
import me.bryan.Police.utils.Menus;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public static Main main;
	private Menus menu;
	private static HashMap<String, ItemStack[]> armorContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, ItemStack[]> inventoryContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, Integer> xplevel = new HashMap<String, Integer>();
	private static ArrayList<String> policeMode = new ArrayList<String>();

	public void onEnable() {
		registerEvents();
		registerCommands();
		setMenu(new Menus(this));

	}

	public void onDisable() {
		disableLists();
	}

	public static ArrayList<String> getPoliceMode() {
		return policeMode;
	}

	public static void setPoliceMode(ArrayList<String> policeMode) {
		Main.policeMode = policeMode;
	}

	public static void saveInventory(Player p) {
		armorContents.put(p.getName(), p.getInventory().getArmorContents());
		inventoryContents.put(p.getName(), p.getInventory().getContents());
		xplevel.put(p.getName(), Integer.valueOf(p.getLevel()));
	}

	public static void loadInventory(Player p) {
		p.getInventory().clear();

		p.getInventory().setContents((ItemStack[]) inventoryContents.get(p.getName()));
		p.getInventory().setArmorContents((ItemStack[]) armorContents.get(p.getName()));
		p.setLevel(((Integer) xplevel.get(p.getName())).intValue());

		inventoryContents.remove(p.getName());
		armorContents.remove(p.getName());
		xplevel.remove(p.getName());

	}

	public static void policeItems(Player p) {
		Inventory inv = p.getInventory();
		inv.clear();

		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemStack handcuffs = new ItemStack(Material.DIAMOND_HOE);
		ItemStack tazer = new ItemStack(Material.CHEST);

		ItemMeta compassMeta = compass.getItemMeta();
		ItemMeta handcuffsMeta = handcuffs.getItemMeta();
		ItemMeta tazerMeta = tazer.getItemMeta();

		compassMeta.setDisplayName(ChatColor.GREEN + "Wanted Compass");
		handcuffsMeta.setDisplayName(ChatColor.AQUA + "Handcuffs");
		tazerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Tazer");

		compass.setItemMeta(compassMeta);
		handcuffs.setItemMeta(handcuffsMeta);
		tazer.setItemMeta(tazerMeta);

		inv.addItem(new ItemStack[] { compass });
		inv.addItem(new ItemStack[] { handcuffs });
		inv.addItem(new ItemStack[] { tazer });
	}

	public static void enterPolice(Player p) {
		policeMode.add(p.getName());
		saveInventory(p);
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		p.setExp(0.0F);
		policeItems(p);
		p.sendMessage("§ePolice Mode: §2Enabled!");
		for (Player online : Bukkit.getOnlinePlayers()) {
			Player onlinePolice = online;
			if ((onlinePolice.hasPermission("policetools.mod")) && (policeMode.contains(onlinePolice.getName()))
					&& (!online.hasPermission("policetools.mod"))) {
				online.hidePlayer(onlinePolice);
			}
		}

	}

	public void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new Death(main), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Respawn(main), this);
		Bukkit.getServer().getPluginManager().registerEvents(new HandCuffs(main), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WantedSystem(main), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Tazer(main), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Freeze(main), this);
	}

	public void registerCommands() {
		this.getCommand("duty").setExecutor(new Duty(this));
		this.getCommand("bam").setExecutor(new Bam(this));
		this.getCommand("wanted").setExecutor(new Wanted(this));
		this.getCommand("promote").setExecutor(new Promote(this));
		this.getCommand("demote").setExecutor(new Demote(this));
	}

	public void disableLists() {
		WantedSystem.setWanted(null);
		WantedSystem.setWantedLevel(null);
		setPoliceMode(null);
	}

	public Menus getMenu() {
		return menu;
	}

	public void setMenu(Menus menu) {
		this.menu = menu;
	}

}
