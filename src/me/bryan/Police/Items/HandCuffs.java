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

public class HandCuffs implements Listener{
	
	static ArrayList<String> cuffed = new ArrayList<String>();
	Main main;
	public HandCuffs(Main main) {
		this.main = main;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity entity = e.getRightClicked();
		if (e.getRightClicked() instanceof Player 
				&& (p.getItemInHand() != null) && (p.getItemInHand().hasItemMeta())
				&& (p.getItemInHand().getItemMeta().hasDisplayName())) {
			if (p.getItemInHand().getItemMeta().getDisplayName()
					.equalsIgnoreCase(("§aHandcuffs"))) {
				if (e.getPlayer().hasPermission("policetools.cuffs")) {
					p.setPassenger(entity);
					cuffed.add(entity.getName());
					//Give the police option to what crime is committed.
				} else {
					e.getPlayer().sendMessage("Report that you have this object to staff");
				}
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(Freeze.isMoving(event)) {
			if(cuffed.contains(player.getName())) {
			event.setCancelled(true);
			}
		}
	}
	
}
