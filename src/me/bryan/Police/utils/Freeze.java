package me.bryan.Police.utils;

import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.bryan.Police.Main;

public class Freeze implements Listener{
	Main main;
	public Freeze(Main main) {
		this.main = main;
	}

	public static boolean isMoving(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();
        boolean anyX = Math.abs(to.getX() - from.getX()) <= 0;
        boolean upY = from.getY() - to.getY() <= 0;
        boolean anyZ = Math.abs(to.getZ() - from.getZ()) <= 0;
        return !anyX || !upY || !anyZ;
    }

}
