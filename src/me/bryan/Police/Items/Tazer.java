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
    public static List<ItemStack> tazer = new ArrayList<ItemStack>();
	Main pl;
	public static List<ItemStack> guns = new ArrayList<ItemStack>();
	public Tazer(Main pl) {
		this.pl = pl;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (pl.getConfig().getStringList("Police").contains(player.getName())) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            	player.sendMessage("8");
                ItemStack handsy = event.getPlayer().getItemInHand();
                tazer.add(Main.tazer);
                player.sendMessage("5");
                for(ItemStack itemRequired : tazer){
                    if(handsy == itemRequired) {
                        if(cooldown.containsKey(event.getPlayer())){
                        	player.sendMessage("4");
                            if(System.currentTimeMillis() >= cooldown.get(event.getPlayer()) + 2000){
                            	player.sendMessage("1");
                                player.launchProjectile(Arrow.class);
                            	player.sendMessage("2");
                                cooldown.put(event.getPlayer(), System.currentTimeMillis());
                                player.sendMessage("3");
                            }else{
                                long timeLeftMillis = ((cooldown.get(event.getPlayer()) + 2000) - System.currentTimeMillis());
                                player.sendMessage("6");
                                long timeLeftSec = (timeLeftMillis / 1000) % 60 ;
                                player.sendMessage("7");
                                if(timeLeftSec == 1){
                                    event.getPlayer().sendMessage(Police.prefix+ "You cannot use this ability for " + timeLeftSec + " more second.");
                                }else{
                                    event.getPlayer().sendMessage(Police.prefix + "You cannot use this ability for " + timeLeftSec + " more seconds.");
                                }
                            }
                        }else{
                                player.launchProjectile(Arrow.class);
                                cooldown.put(event.getPlayer(), System.currentTimeMillis());
                        }
                    }
                }
            }
        } else {
        	player.sendMessage(Police.prefix + ChatColor.RED + "You're not police BAD");
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
					event.setDamage(13);
				}
			}
		}
	}
}
