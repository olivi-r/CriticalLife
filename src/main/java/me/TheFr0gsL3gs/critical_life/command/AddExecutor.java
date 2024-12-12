package me.TheFr0gsL3gs.critical_life.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.CriticalLifePlugin;

public class AddExecutor implements CommandExecutor {
	CriticalLifePlugin plugin;

	public AddExecutor(CriticalLifePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			Player p = Bukkit.getPlayer(args[0]);
			if (p != null) {
				p.getPersistentDataContainer().set(plugin.livesKey, PersistentDataType.INTEGER, 6);
				return true;
			}
		}
		return false;
	}

}
