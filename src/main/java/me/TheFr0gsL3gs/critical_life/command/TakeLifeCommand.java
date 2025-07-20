package me.TheFr0gsL3gs.critical_life.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;

public class TakeLifeCommand implements CommandExecutor, TabCompleter {
	Main plugin;
	public static boolean enabled = false;

	public TakeLifeCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1 && sender instanceof Player) {
			if (enabled) {
				Player target = Bukkit.getPlayerExact(args[0]);
				Player senderPlayer = (Player) sender;
				PersistentDataContainer targetData = target.getPersistentDataContainer();
				PersistentDataContainer senderData = senderPlayer.getPersistentDataContainer();

				if (target != null && plugin.getAlivePlayers().contains(senderPlayer) && target != senderPlayer) {
					int affectedCount = targetData.get(plugin.affectedCountKey, PersistentDataType.INTEGER);
					int senderLives = senderData.get(plugin.livesKey, PersistentDataType.INTEGER);
					int targetLives = targetData.get(plugin.livesKey, PersistentDataType.INTEGER);

					if (targetLives < 2)
						sender.sendMessage(ChatColor.RED + "Target must be a non-red player");

					else if (affectedCount < 2) {
						targetData.set(plugin.livesKey, PersistentDataType.INTEGER, targetLives - 1);
						targetData.set(plugin.affectedCountKey, PersistentDataType.INTEGER, affectedCount + 1);
					} else if (senderLives > 1) {
						sender.sendMessage(
								ChatColor.RED + "Target has already been drained twice, dealing punishment...");
						senderData.set(plugin.livesKey, PersistentDataType.INTEGER, senderLives - 1);
					} else {
						sender.sendMessage(ChatColor.RED + "Target has already been drained twice");
					}
					plugin.refresh(target);
					plugin.refresh(senderPlayer);
					return true;
				}
			} else
				sender.sendMessage(ChatColor.RED + "Command is disabled");

		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (enabled) {
			plugin.getAlivePlayers().forEach(player -> completions.add(player.getName()));
			if (sender instanceof Player)
				completions.remove(completions.indexOf(((Player) sender).getName()));

		}
		return completions;
	}

}
