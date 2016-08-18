package me.bryan.Police.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bryan.Police.Main;

public class Duty implements CommandExecutor {
	Main main;

	public Duty(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Police.prefix + ChatColor.RED+"Only a player may use this command");
			return true;
		} else {
			Player player = (Player) sender;
			if (player.hasPermission("policetools.duty")) {
				if (main.getConfig().getStringList("Police").contains(player.getName())) {
					if (Main.getPoliceMode().contains(player.getName())) {
						Main.leavePolice(player);
					} else {
						Main.enterPolice(player);
					}
				} else {
					player.sendMessage(Police.prefix + ChatColor.RED + "You're not a police man scrub -Santa");
				}
			} else {
				player.sendMessage(Police.prefix + ChatColor.RED + "No, bad boi "+ChatColor.GOLD +"-Santa");
			}
		}
		return false;
	}

}
