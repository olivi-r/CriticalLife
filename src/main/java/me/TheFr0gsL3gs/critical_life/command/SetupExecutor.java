package me.TheFr0gsL3gs.critical_life.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.CriticalLifePlugin;

public class SetupExecutor implements CommandExecutor {
	CriticalLifePlugin plugin;

	public SetupExecutor(CriticalLifePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Bukkit.getOnlinePlayers().forEach(p -> {
			p.getPersistentDataContainer().set(plugin.livesKey, PersistentDataType.INTEGER, 6);
		});
		return true;
	}

}
