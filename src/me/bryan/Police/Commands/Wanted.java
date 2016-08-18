package me.bryan.Police.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bryan.Police.Main;
import me.bryan.Police.Wanted.WantedSystem;

public class Wanted implements CommandExecutor {
	Main main;

	public Wanted(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (main.getConfig().getStringList("Police").contains(player.getName())) {
				if (player.hasPermission("policetools.wanted")) {
					if (args.length == 0) {
						player.sendMessage("/wanted list: Gives out all players that is wanted");
						player.sendMessage("/wanted set <player name>: Sets a player as wanted stage 1 default");
						return true;
					}
					if (args[0].equalsIgnoreCase("list")) {
						player.sendMessage(ChatColor.RED + "[WANTED]");
						player.sendMessage(ChatColor.GOLD + "" + WantedSystem.getWanted().keySet());
					} else if (args[0].equalsIgnoreCase("set")) {
						if (args.length == 1) {
							player.sendMessage(ChatColor.RED + "You must type a player name");
							return true;
						}
						String message = "";
						for (int i = 2; i < args.length; i++) {
							message += args[i] + " ";
						}
						message.trim();
						if (Bukkit.getServer().getPlayer(message) != null) {
							WantedSystem.getWanted().put(message, 1);
							player.sendMessage("Worked");
						} else {
							player.sendMessage(ChatColor.RED + "Player null");
							return true;
						}
						WantedSystem.getWanted().put(player.getName(), 1);
					} else {
						player.sendMessage(ChatColor.RED + "Unknown argument!");
					}

				} else {
					player.sendMessage("You must be a policeman, or be on duty in order to use this command");
				}
			}
		}

		return true;
	}

}
