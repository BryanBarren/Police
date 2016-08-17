package me.bryan.Police.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bryan.Police.Main;
import me.bryan.Police.utils.ChatColor;

public class Duty implements CommandExecutor {
	Main main;

	public Duty(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.color("&4Only a player may use this command"));
			return true;
		} else {
			Player player = (Player) sender;
			if (player.hasPermission("policetools.duty")) {
				if (Main.getPoliceMode().contains(player)) {
					Main.enterPolice(player);
					player.sendMessage("working");
				} else {
					Main.loadInventory(player);
					player.sendMessage("working");
				}
			}
		}
		return false;
	}

}
