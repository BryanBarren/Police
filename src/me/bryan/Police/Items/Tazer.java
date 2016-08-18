package me.bryan.Police.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.bryan.Police.Main;
import me.bryan.Police.Commands.Police;

public class Tazer implements Listener {
	protected HashMap<Player, Long> cooldown = new HashMap<Player, Long>();
	Main pl;
	public static List<ItemStack> guns = new ArrayList<ItemStack>();
	public Tazer(Main pl) {
		this.pl = pl;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (guns.contains(p.getItemInHand().equals(Main.tazer))&& e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			p.sendMessage("working");
			if (System.currentTimeMillis() >= cooldown.get(e.getPlayer()) + 2000) {
				p.sendMessage("1");
				p.launchProjectile(Arrow.class);
				p.sendMessage("2");
				cooldown.put(e.getPlayer(), System.currentTimeMillis());
				p.sendMessage("3");
			} else {
				int timeLeftMillis =  (int) ((cooldown.get(e.getPlayer()) + 2000) - System.currentTimeMillis());
				int timeLeftSec = (int) (timeLeftMillis / 1000) % 60;
				if (timeLeftSec == 1) {
					p.sendMessage(Police.prefix + ChatColor.RED + "You cannot use this ability for " + ChatColor.GOLD
							+ timeLeftSec + ChatColor.RED + " more second.");
				} else {
					p.sendMessage(Police.prefix + "You cannot use this ability for " + timeLeftSec + " more seconds.");
				}
			}
			}
		
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Arrow) {
			Arrow a = (Arrow) event.getDamager();
			if (a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
				if (shooter.getItemInHand().getItemMeta().getDisplayName()
						.equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Tazer")) {
					event.setDamage(23921398);
				}
			}
		}
	}
}
