package me.TheFr0gsL3gs.critical_life.event.global;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.persistence.PersistentDataType;

import me.TheFr0gsL3gs.critical_life.Main;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TranslatableComponent;

public class ReverseBountyEvent extends GlobalEvent {

	public ReverseBountyEvent(Main plugin) {
		super(plugin, "reverse_bounty", ChatColor.RED + "Reverse Bounty", ChatColor.RED
				+ "A cryptic challenge has been issued to you. A specific item must be claimed and delivered, or the cost will be dire—your very life hangs in the balance. You will receive a personal message revealing your target item. Once found, hold it close and run /submit to seal your fate. Time is fleeting, and failure is not an option. Will you rise to meet the demand, or let the sands of time slip through your fingers?");
	}

	@Override
	public void start() {
		plugin.getAlivePlayers().forEach(player -> {
			String bounty = plugin.bountyList.get(ThreadLocalRandom.current().nextInt(plugin.bountyList.size()))
					.getTranslationKey();

			player.spigot().sendMessage(new ComponentBuilder().append("Your target is: ").color(ChatColor.RED)
					.append(new TranslatableComponent(bounty)).color(ChatColor.YELLOW).create());
			player.getPersistentDataContainer().set(plugin.bountyKey, PersistentDataType.STRING, bounty);
		});
	}

	@Override
	public void end() {
	}

}
