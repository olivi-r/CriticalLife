package me.TheFr0gsL3gs.critical_life.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;
import net.md_5.bungee.api.ChatColor;

public class GiftLifeCommand implements CommandExecutor, TabCompleter {
	Main plugin;
	public static boolean enabled = false;

	public GiftLifeCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!enabled) {
			sender.sendMessage(ChatColor.RED + "This command is currently disabled");
			return true;
		}
		if (args.length == 1 && sender instanceof Player) {
			Player receiver = Bukkit.getPlayer(args[0]);
			if (receiver != null) {
				if (receiver == (Player) sender)
					sender.sendMessage(ChatColor.RED + "You cannot gift yourself a life");

				else
					receiver.getPersistentDataContainer().set(plugin.livesKey, PersistentDataType.INTEGER,
							receiver.getPersistentDataContainer().get(plugin.livesKey, PersistentDataType.INTEGER) + 1);

				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (enabled && args.length == 1) {
			plugin.getAlivePlayers().forEach(player -> completions.add(player.getName()));
			if (sender instanceof Player)
				completions.remove(completions.indexOf(((Player) sender).getName()));

		}
		return completions;
	}

}
