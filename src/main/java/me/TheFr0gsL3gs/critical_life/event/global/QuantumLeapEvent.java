package me.TheFr0gsL3gs.critical_life.event.global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;

public class QuantumLeapEvent extends GlobalEvent {
	public static final boolean perPlayer = false;
	static boolean stop = false;

	public QuantumLeapEvent(Main plugin) {
		super(plugin, "quantum_leap");
	}

	@Override
	public void start() {
		List<Player> players = plugin.getAlivePlayers();
		Collections.shuffle(players);
		List<Location> locations = new ArrayList<>();
		players.forEach(player -> locations.add(player.getLocation()));
		Collections.rotate(locations, 1);
		for (int i = 0; i < players.size(); i++)
			players.get(i).teleport(locations.get(i));

	}

	@Override
	public void end() {
	}

}
