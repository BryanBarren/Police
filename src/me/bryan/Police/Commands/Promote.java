package me.bryan.Police.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bryan.Police.Main;
import net.md_5.bungee.api.ChatColor;

public class Promote implements CommandExecutor{
	Main main;	
	public Promote(Main main){
		this.main = main;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(player.hasPermission("policetools.promote")) {
				if(args.length == 1) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
				Bukkit.dispatchCommand(sender, "manuadd " +target.getName()+ "COP");
				player.sendMessage("working");
				} else {
					player.sendMessage("Please input a player");
				}
			} else {
				player.sendMessage(Police.prefix +ChatColor.RED + "No, bad boi "+ChatColor.GOLD+ "-Santa");
			}
		}
		
		return false;
	}

}
