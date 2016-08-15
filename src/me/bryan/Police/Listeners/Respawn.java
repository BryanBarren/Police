package me.bryan.Police.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.bryan.Police.Main;

public class Respawn implements Listener{
	Main main;
	public Respawn(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if(Main.policeMode.contains(player.getName())) {
			Main.loadInventory(player);
			player.sendMessage("Your Police inventory has been restored.");
		}
	}

}
