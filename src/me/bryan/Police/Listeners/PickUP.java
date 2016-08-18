package me.bryan.Police.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.bryan.Police.Main;

public class PickUP implements Listener{
	Main main;
	public PickUP(Main main) {
		this.main = main;
	}
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		if(Main.getPoliceMode().contains(player.getName())) {
			event.setCancelled(true);
		}
	}

}
