package me.TheFr0gsL3gs.critical_life.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;

public class LivesCommand implements CommandExecutor, TabCompleter {
	Main plugin;

	public LivesCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			int lives = player.getPersistentDataContainer().get(plugin.livesKey, PersistentDataType.INTEGER);

			if (lives == 1)
				player.sendMessage("You have 1 life left");

			else
				player.sendMessage(String.format("You have %d lives left", lives));

			return true;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return new ArrayList<>();
	}

}
