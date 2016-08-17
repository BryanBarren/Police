package me.bryan.Police.Items;

import java.util.ArrayList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import me.bryan.Police.Main;
import me.bryan.Police.utils.Freeze;
import net.md_5.bungee.api.ChatColor;

public class HandCuffs implements Listener {
	private static ArrayList<String> cuffed = new ArrayList<String>();

	public static ArrayList<String> getCuffed() {
		return cuffed;
	}

	public static void setCuffed(ArrayList<String> cuffed) {
		HandCuffs.cuffed = cuffed;
	}

	Main main;

	public HandCuffs(Main main) {
		this.main = main;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		// THIS IS THE CRIMINAL
		Entity entity = e.getRightClicked();
		// IN BETWEEN THE 2 COMMENTS
		if (e.getRightClicked() instanceof Player && (p.getItemInHand() != null) && (p.getItemInHand().hasItemMeta())
				&& (p.getItemInHand().getItemMeta().hasDisplayName())) {
			if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase((ChatColor.AQUA + "Handcuffs"))) {
				if (e.getPlayer().hasPermission("policetools.cuffs")) {
					p.setPassenger(entity);
					cuffed.add(entity.getName());
					main.getMenu().showJailInv(p);

				} else {
					e.getPlayer().sendMessage(ChatColor.RED + "Report that you have this object to staff");
				}
			}
		} else {
			p.sendMessage("Not a player.");
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (event.getFrom().getBlockX() != event.getTo().getBlockX()
				|| event.getFrom().getBlockY() != event.getTo().getBlockY()
				|| event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
			if (Freeze.isMoving(event)) {
				if (cuffed.contains(player.getName())) {
					event.setCancelled(true);
				}
			}
		}
	}

}
