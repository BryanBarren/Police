package me.bryan.Police.Items;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bryan.Police.Main;
import me.bryan.Police.utils.ChatColor;

public class LockPick implements Listener{
	private static Random random = new Random();
	Main main;
	public LockPick(Main main) {
		this.main = main;
	}
	double r = random.nextDouble();
	static ItemStack lockPick = new ItemStack(Material.STICK);
	static ItemMeta lockPickMeta = lockPick.getItemMeta();
	public static void lockPick(Material m) {
	lockPickMeta.setDisplayName(ChatColor.color("&9LockPick"));
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getRightClicked() instanceof Player 
				&& (p.getItemInHand() != null) && (p.getItemInHand().hasItemMeta())
				&& (p.getItemInHand().getItemMeta().hasDisplayName())) {
			if (p.getItemInHand().getItemMeta().getDisplayName()
					.equalsIgnoreCase(ChatColor.color("&9LockPick"))) {
				if (e.getPlayer().hasPermission("policetools.pick")) {
					if(HandCuffs.cuffed.contains(e.getPlayer().getName())) {
						if(r <= 0.3){
						HandCuffs.cuffed.remove(e.getPlayer().getName());
						e.getPlayer().sendMessage(ChatColor.color("&2You've broken out! Run free!"));
						} else if(r <=0.7) {
							e.getPlayer().getInventory().removeItem(lockPick);
							e.getPlayer().updateInventory();
							e.getPlayer().sendMessage("&4You're going to jail mate!");
						}
					}
					
				} else {
					e.getPlayer().sendMessage("Report that you have this object to staff");
				}
			}
		}
	}
	
	

}
