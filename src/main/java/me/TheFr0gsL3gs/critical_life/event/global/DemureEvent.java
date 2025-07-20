package me.TheFr0gsL3gs.critical_life.event.global;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;
import net.md_5.bungee.api.ChatColor;

public class DemureEvent extends GlobalEvent {

	public DemureEvent(Main plugin) {
		super(plugin, "demure", ChatColor.GREEN + "Very Demure, Very Mindful",
				ChatColor.GREEN + "The world pauses in quiet contemplation. No chaos, no challenges—simply stillness. Take a moment, breathe deeply, and refresh your spirit. Nothing happens... but sometimes, that's exactly what you need. Enjoy the calm before the storm. :D");
	}

	@Override
	public void start() {
	}

	@Override
	public void end() {
	}

}
