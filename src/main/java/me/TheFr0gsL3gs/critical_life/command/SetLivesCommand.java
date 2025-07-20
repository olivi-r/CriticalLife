package me.TheFr0gsL3gs.critical_life.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;

public class SetLivesCommand implements CommandExecutor, TabCompleter {
	Main plugin;

	public SetLivesCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {
			if (args.length == 2) {
				Player player = Bukkit.getPlayer(args[0]);
				Integer lives = Integer.parseInt(args[1]);
				if (player != null && lives != null && lives >= 0) {
					player.getPersistentDataContainer().set(plugin.livesKey, PersistentDataType.INTEGER, lives);
					if (lives > 0)
						player.setGameMode(GameMode.SURVIVAL);

					else
						player.setGameMode(GameMode.SPECTATOR);

					plugin.refresh(player);
					return true;
				}
			}
		} catch (NumberFormatException err) {
		}

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (args.length == 1) {
			Bukkit.getOnlinePlayers().forEach(player -> completions.add(player.getName()));
		}
		return completions;
	}

}
