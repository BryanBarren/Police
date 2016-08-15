package me.bryan.Police.Wanted;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.bryan.Police.Main;

public class WantedSystem implements Listener {
	Main main;
	public static HashMap<String, Integer> wanted = new HashMap<String, Integer>();
	public static HashMap<String, Integer> wantedLevel = new HashMap<String, Integer>();
	public WantedSystem(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerkill(PlayerDeathEvent event) {
		Player killer = event.getEntity().getKiller();
		Player killed = event.getEntity();
		if (!(killer instanceof Player)) {
			return;
		}
		if (!(killed instanceof Player)) {
			return;
		}
		if (!wanted.containsKey(killer.getName())) {
			wanted.put(killer.getName(), 0);
		}
		if (wanted.get(killer.getName()) >= 1) {
			killer.sendMessage("§4You're currently wanted, if there are any police on you're being hunted");
			Bukkit.broadcastMessage(killer.getName() + "Is now wanted: Level 1.");
			wantedLevel.put(killer.getName(), 1);
		} else if (wanted.get(killer.getName()) >= 3) {
			killer.sendMessage("§4You're wanted level just raised.");
			Bukkit.broadcastMessage(killer.getName() + "Is now wanted: Level 2");
			wantedLevel.put(killer.getName(), 2);
		} else if (wanted.get(killer.getName()) >= 6) {
			killer.sendMessage("§4You're now max priority");
			Bukkit.broadcastMessage(killer.getName() + "Is now wanted: Level 3 TOP PRIORITY");
			wantedLevel.put(killer.getName(), 3);
		}
		wanted.put(killer.getName(), wanted.get(killer.getName() + 1));

	}
}
