package me.TheFr0gsL3gs.critical_life.event.global;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import me.TheFr0gsL3gs.critical_life.event.Event;

public class BountyEvent extends Event {

	public BountyEvent() {
		List<Material> bounties = new ArrayList<Material>();
		bounties.add(Material.ENDER_EYE);
		bounties.add(Material.RESPAWN_ANCHOR);
		bounties.add(Material.SALMON_BUCKET);
		bounties.add(Material.CAKE);
		bounties.add(Material.HONEY_BOTTLE);
		bounties.add(Material.FERMENTED_SPIDER_EYE);
		bounties.add(Material.SLIME_BLOCK);
		bounties.add(Material.SUSPICIOUS_STEW);
		bounties.add(Material.PUMPKIN_PIE);
		bounties.add(Material.COBWEB);
		bounties.add(Material.MUSHROOM_STEW);
		bounties.add(Material.POISONOUS_POTATO);
		bounties.add(Material.BLACK_BUNDLE);
		bounties.add(Material.BRUSH);
		bounties.add(Material.BOOKSHELF);
		bounties.add(Material.LEAD);
		bounties.add(Material.FIRE_CHARGE);
		bounties.add(Material.TNT_MINECART);
		bounties.add(Material.SPYGLASS);
		bounties.add(Material.JUKEBOX);
		bounties.add(Material.LEATHER_HELMET);
		bounties.add(Material.LILY_PAD);
		bounties.add(Material.MAGMA_CREAM);
		bounties.add(Material.OBSERVER);
		bounties.add(Material.NAUTILUS_SHELL);
		bounties.add(Material.COMPASS);
		bounties.add(Material.DIRT);
		bounties.add(Material.MAP);
		bounties.add(Material.PAINTING);
		bounties.add(Material.CLOCK);
		bounties.add(Material.CROSSBOW);
		bounties.add(Material.FIREWORK_ROCKET);
		bounties.add(Material.GOLDEN_APPLE);
		bounties.add(Material.COAL_BLOCK);
		bounties.add(Material.NETHERITE_INGOT);
		bounties.add(Material.GLISTERING_MELON_SLICE);
		bounties.add(Material.GILDED_BLACKSTONE);
		bounties.add(Material.EMERALD_BLOCK);
		bounties.add(Material.NETHER_WART_BLOCK);
		bounties.add(Material.DIAMOND_HOE);
		bounties.add(Material.WRITABLE_BOOK);
		bounties.add(Material.SOUL_LANTERN);
		bounties.add(Material.ARMOR_STAND);
		bounties.add(Material.FIREWORK_STAR);
	}

	@Override
	public void onTrigger() {
		// TODO Auto-generated method stub

	}

}
