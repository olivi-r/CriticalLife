package me.TheFr0gsL3gs.critical_life.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.TheFr0gsL3gs.critical_life.Main;

public class SwapLivesCommand implements CommandExecutor, TabCompleter {
	Main plugin;

	public SwapLivesCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 2)
			return new ArrayList<>();

		List<String> completions = new ArrayList<>();
		Bukkit.getOnlinePlayers().forEach(player -> completions.add(player.getName()));
		if (args.length != 2)
			return completions;

		completions.remove(args[1]);
		return completions;
	}

}
