package me.TheFr0gsL3gs.critical_life.event.global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;
import net.md_5.bungee.api.ChatColor;

public class SoulSwapEvent extends GlobalEvent {
	public static final boolean perPlayer = false;

	public SoulSwapEvent(Main plugin) {
		super(plugin, "soul_swap", ChatColor.RED + "Soul Swap", ChatColor.RED
				+ "The threads of destiny have been tangled, and your very essence has been exchanged with another. The number of lives you once held is no longer your own—it now resides with another soul, and theirs with you. Who holds your fate now, and what will you do with the lives bestowed upon you? The balance of power has shifted. Embrace the chaos, for the swap is eternal.");
	}

	@Override
	public void start() {
		List<Player> players = plugin.shuffle(plugin.getAlivePlayers());
		List<Integer> lives = new ArrayList<>();
		players.forEach(
				player -> lives.add(player.getPersistentDataContainer().get(plugin.livesKey, PersistentDataType.INTEGER)));
		Collections.rotate(lives, 1);
		for (int i = 0; i < lives.size(); i++)
			players.get(i).getPersistentDataContainer().set(plugin.livesKey, PersistentDataType.INTEGER, lives.get(i));
	}

	@Override
	public void end() {
	}

}
