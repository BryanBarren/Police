package me.bryan.Police.Items;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.bryan.Police.Main;


public class Tazer implements Listener{
	Main pl;
	public Tazer(Main pl) {
		this.pl = pl;
	}
	public String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getRightClicked() instanceof Player 
				&& (p.getItemInHand() != null) && (p.getItemInHand().hasItemMeta())
				&& (p.getItemInHand().getItemMeta().hasDisplayName())) {
			if (p.getItemInHand().getItemMeta().getDisplayName()
					.equalsIgnoreCase(color("&9Tazer"))) {
				if (e.getPlayer().hasPermission("policetools.mod")) {
					
				} else {
					e.getPlayer().sendMessage("Report that you have this object to staff");
				}
			}
		}
	}
	}
	

