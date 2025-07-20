package me.TheFr0gsL3gs.critical_life.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;

public class EventCommand implements CommandExecutor, TabCompleter {
	Main plugin;

	public EventCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			List<String> ids = new ArrayList<>();
			List<GlobalEvent> events = new ArrayList<>();
			plugin.globalEvents.forEach((roll, event) -> {
				ids.add(event.id);
				events.add(event);
			});
			int index = ids.indexOf(args[0]);
			if (index != -1) {
				events.get(index).announce();
				events.get(index).start();
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (args.length == 1) {
			plugin.globalEvents.forEach((roll, event) -> completions.add(event.id));
		}
		return completions;
	}

}
