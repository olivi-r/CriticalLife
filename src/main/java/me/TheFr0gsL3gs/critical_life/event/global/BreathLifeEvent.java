package me.TheFr0gsL3gs.critical_life.event.global;

import org.bukkit.ChatColor;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.command.GiftLifeCommand;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;

public class BreathLifeEvent extends GlobalEvent {
	public BreathLifeEvent(Main plugin) {
		super(plugin, "breath_life", ChatColor.GREEN + "Breath of Life", ChatColor.GREEN
				+ "A divine opportunity beckons—you may grant the gift of life to another (/gift-life). Choose wisely, for each soul can receive this blessing no more than twice. Your act of generosity could alter destinies, forging alliances or mending rivalries. Who will you uplift with this precious gift?");
	}

	@Override
	public void start() {
		plugin.getAlivePlayers().forEach(player -> player.getPersistentDataContainer().set(plugin.affectedCountKey,
				PersistentDataType.INTEGER, 0));
		GiftLifeCommand.enabled = true;
	}

	@Override
	public void end() {
		GiftLifeCommand.enabled = false;
	}

}
