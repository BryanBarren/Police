package me.bryan.Police.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bryan.Police.Main;
import net.md_5.bungee.api.ChatColor;

public class Demote implements CommandExecutor {
	Main main;

	public Demote(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("policetools.demote")) {
				if (args.length == 1) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					Bukkit.dispatchCommand(sender, "jobs promote " + target.getName());
					player.sendMessage("working");
				} else {
					player.sendMessage("Please input a player");
				}
			} else {
				player.sendMessage(ChatColor.RED + "You don't have enough permissions.");
			}
		}

		return false;
	}

}
