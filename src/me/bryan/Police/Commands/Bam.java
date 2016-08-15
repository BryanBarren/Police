package me.bryan.Police.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.bryan.Police.Main;

public class Bam implements CommandExecutor{
	Main main;
	public Bam(Main main){
		this.main =main;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}

}
