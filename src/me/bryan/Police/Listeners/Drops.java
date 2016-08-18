package me.bryan.Police.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.bryan.Police.Main;

public class Drops implements Listener{
	Main main;
	public Drops(Main main) {
		this.main = main;
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(Main.getPoliceMode().contains(player.getName())) {
			event.setCancelled(true);
		}
	}

}
