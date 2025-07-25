package me.TheFr0gsL3gs.critical_life.event.global;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TranslatableComponent;

public class BountyEvent extends GlobalEvent {

	public BountyEvent(Main plugin) {
		super(plugin, "bounty", ChatColor.GREEN + "Bounty", ChatColor.GREEN
				+ "A challenge has been set, and fate calls upon you. A coveted item has been requested, and if you can claim it before time runs out, a life will be yours to keep. You will receive a personal message revealing your target. Once in your grasp, hold it close and run /submit to seal your victory. Will you rise to the challenge, or let the opportunity slip through your fingers? The clock is ticking...");
	}

	@Override
	public void start() {
		plugin.getAlivePlayers().forEach(player -> {
			String bounty = plugin.bountyList.get(ThreadLocalRandom.current().nextInt(plugin.bountyList.size()))
					.getTranslationKey();

			player.spigot().sendMessage(new ComponentBuilder().append("Your target is: ").color(ChatColor.GREEN)
					.append(new TranslatableComponent(bounty)).color(ChatColor.YELLOW).create());
			player.getPersistentDataContainer().set(plugin.bountyKey, PersistentDataType.STRING, bounty);
		});
	}

	@Override
	public void end() {
	}

}
