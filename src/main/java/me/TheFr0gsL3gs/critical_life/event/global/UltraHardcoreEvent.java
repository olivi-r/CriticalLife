package me.TheFr0gsL3gs.critical_life.event.global;

import org.bukkit.Bukkit;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;

public class UltraHardcoreEvent extends GlobalEvent {

	public UltraHardcoreEvent(Main plugin) {
		super(plugin, "uhc");
	}

	@Override
	public void start() {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule naturalRegeneration false");
	}

	@Override
	public void end() {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule naturalRegeneration true");
	}

}
