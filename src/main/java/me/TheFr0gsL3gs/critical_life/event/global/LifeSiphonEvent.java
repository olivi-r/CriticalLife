package me.TheFr0gsL3gs.critical_life.event.global;

import org.bukkit.ChatColor;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.command.TakeLifeCommand;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;

public class LifeSiphonEvent extends GlobalEvent {

	public LifeSiphonEvent(Main plugin) {
		super(plugin, "life_siphon", ChatColor.RED + "Life Siphon", ChatColor.RED
				+ "The Life Siphon Awakens! A dark power stirs within the realm, granting you the ability to wield the /take-life command. Steal the essence of any Yellow or above player, but beware—the balance of life is fragile, and a soul may only be drained twice. The Reds remain untouched by this curse yet still hold the power to claim life for themselves. Will you seize this chance to rise, or fall victim to another's greed? Choose wisely, for every stolen life carries its price.");
	}

	@Override
	public void start() {
		plugin.getAlivePlayers().forEach(player -> player.getPersistentDataContainer().set(plugin.affectedCountKey,
				PersistentDataType.INTEGER, 0));
		TakeLifeCommand.enabled = true;
	}

	@Override
	public void end() {
		TakeLifeCommand.enabled = false;
	}

}
