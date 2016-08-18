package me.bryan.Police.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bryan.Police.Main;

public class Police implements CommandExecutor{
	Main main;
	public Police(Main main) {
		this.main =main;
	}
	
	public static String prefix =ChatColor.BLACK + "["+ChatColor.GOLD + "Mc"+ChatColor.AQUA +"Cities"+ ChatColor.BLACK + "]"+ " ";
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(player.hasPermission("policetools.policeadd")) {
				if(main.getConfig().getStringList("PoliceChief").contains(player.getName())) {
				if(args.length ==0) {
					player.sendMessage("Please specify a player");
				} else if(args.length==1) {
					Player target= Bukkit.getPlayer(args[0]);
					if(Bukkit.getServer().getPlayer(target.getName())!=null) {
						Main.getPoliceList().add(target.getName());
						main.getConfig().set("Police", Main.getPoliceList());
						player.sendMessage(prefix +ChatColor.GOLD + target.getName() + ChatColor.GREEN + "is now a policeman");
						target.sendMessage(prefix + ChatColor.AQUA + "You're now police!");
					} else {
						player.sendMessage(prefix + ChatColor.RED+ "Player does not exist");
					}
				}
			} else {
				player.sendMessage(prefix + ChatColor.RED + "You're no cheif, disgrace you!");
			}
			} else {
				player.sendMessage(prefix + ChatColor.RED +"No, bad boi -Santa");
			}
		}
		
		return true;
	}

}
