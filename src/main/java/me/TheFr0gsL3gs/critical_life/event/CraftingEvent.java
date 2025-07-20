package me.TheFr0gsL3gs.critical_life.event;

import me.TheFr0gsL3gs.critical_life.Main;

public abstract class CraftingEvent {
	protected Main plugin;

	public CraftingEvent(Main plugin) {
		this.plugin = plugin;
	}

	abstract public void start();

	abstract public void end();

}
