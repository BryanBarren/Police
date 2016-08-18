package me.bryan.Police.Items;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.bryan.Police.Main;
import me.bryan.Police.Commands.Police;

public class Tazer implements Listener {
	protected HashMap<Player, Long> cooldown = new HashMap<Player, Long>();
	Main pl;

	public Tazer(Main pl) {
		this.pl = pl;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getRightClicked() instanceof Player && (p.getItemInHand() != null) && (p.getItemInHand().hasItemMeta())
				&& (p.getItemInHand().equals(Main.tazer))) {
			if (p.getItemInHand().equals(Main.tazer)) {
				if (e.getPlayer().hasPermission("policetools.tazer")) {
					e.getPlayer().sendMessage("w");
					if (cooldown.containsKey(p.getName())) {
						if (System.currentTimeMillis() >= cooldown.get(e.getPlayer()) + 2000) {
							p.launchProjectile(Arrow.class);
							cooldown.put(e.getPlayer(), System.currentTimeMillis());
						} else {
							int timeLeftMillis = (int) ((cooldown.get(e.getPlayer()) + 2000)
									- System.currentTimeMillis());
							int timeLeftSec = (int) (timeLeftMillis / 1000) % 60;
							if (timeLeftSec == 1) {
								e.getPlayer().sendMessage(
										Police.prefix +ChatColor.RED + "You cannot use this ability for " + ChatColor.GOLD + timeLeftSec +ChatColor.RED +  " more second.");
						} else {
							e.getPlayer().sendMessage(Police.prefix +"You cannot use this ability for " + timeLeftSec + " more seconds."); 
							}
						}
					} else {
						e.getPlayer().launchProjectile(Arrow.class);
						cooldown.put(p, System.currentTimeMillis());
					}
				} else {
					e.getPlayer().sendMessage(Police.prefix +ChatColor.RED + "Why da hell you have dis? Give this to "+ChatColor.GOLD + "Santa"+ ChatColor.RED + "ASAP");
				}
			}
		}
	}	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Arrow) {
			Arrow a = (Arrow) event.getDamager();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player)a.getShooter();
				if(shooter.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Tazer")) {
					event.setDamage(23921398);
				}
			}
		}
	}
}
