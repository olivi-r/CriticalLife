package me.TheFr0gsL3gs.critical_life.event;

import org.bukkit.ChatColor;

import me.TheFr0gsL3gs.critical_life.Main;

abstract public class GlobalEvent {
	public String id;
	protected Main plugin;
	String name;
	String message;
	boolean secret;

	public GlobalEvent(Main plugin, String id) {
		this.plugin = plugin;
		this.id = id;
		secret = true;
	}

	public GlobalEvent(Main plugin, String id, String name, String message) {
		this.plugin = plugin;
		this.id = id;
		this.name = name;
		this.message = message;
		secret = false;
	}

	public GlobalEvent(Main plugin, String id, String name, String message, boolean secret) {
		this.plugin = plugin;
		this.id = id;
		this.name = name;
		this.message = message;
		this.secret = secret;
	}

	public void announce() {
		plugin.getAlivePlayers().forEach(player -> {
			if (this.secret) {
				player.sendTitle(ChatColor.YELLOW + "Secret Roll", "", 10, 40, 10);
				player.sendMessage(ChatColor.YELLOW
						+ "Shadows whisper, and the unknown stirs. What fate awaits you? Only time will reveal the truth—or will it? The veil of mystery remains unbroken as this enigmatic event unfolds. Brace yourself... anything could happen.");
			} else {
				player.sendTitle(this.name, "", 10, 40, 10);
				player.sendMessage(message);
			}
		});
	};

	abstract public void start();

	abstract public void end();

}
