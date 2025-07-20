package me.TheFr0gsL3gs.critical_life;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.TheFr0gsL3gs.critical_life.command.EventCommand;
import me.TheFr0gsL3gs.critical_life.command.GiftLifeCommand;
import me.TheFr0gsL3gs.critical_life.command.LivesCommand;
import me.TheFr0gsL3gs.critical_life.command.SetLivesCommand;
import me.TheFr0gsL3gs.critical_life.event.GlobalEvent;
import me.TheFr0gsL3gs.critical_life.event.global.BountyEvent;
import me.TheFr0gsL3gs.critical_life.event.global.BreathLifeEvent;
import me.TheFr0gsL3gs.critical_life.event.global.DemureEvent;
import me.TheFr0gsL3gs.critical_life.event.global.LifeSiphonEvent;
import me.TheFr0gsL3gs.critical_life.event.global.ParanoiaEvent;
import me.TheFr0gsL3gs.critical_life.event.global.QuantumLeapEvent;
import me.TheFr0gsL3gs.critical_life.event.global.ReverseBountyEvent;
import me.TheFr0gsL3gs.critical_life.event.global.SoulSwapEvent;
import me.TheFr0gsL3gs.critical_life.event.global.UltraHardcoreEvent;

public class Main extends JavaPlugin implements Listener {
	Team darkGreenTeam;
	Team greenTeam;
	Team yellowTeam;
	Team redTeam;
	Team blackTeam;
	public NamespacedKey livesKey;
	public NamespacedKey bountyKey;
	public NamespacedKey baseHealthKey;
	public NamespacedKey affectedCountKey;
	public Map<Integer, GlobalEvent> globalEvents;
	public List<Material> bountyList;
	public List<Material> wood;
	static double TRIGGER_WOOD = 0.15;
	static double TRIGGER_STONE = 0.002;
	static double TRIGGER_COAL = 0.05;
	static double TRIGGER_COPPER = 0.05;
	static double TRIGGER_IRON = 0.1;
	static double TRIGGER_GOLD = 0.2;
	static double TRIGGER_LAPIS = 1;
	static double TRIGGER_REDSTONE = 0.1;
	static double TRIGGER_DIAMOND = 0.25;
	static double TRIGGER_EMERALD = 0.4;
	static double TRIGGER_QUARTZ = 0.05;
	static double TRIGGER_ANCIENT_DEBRIS = 0.4;

	public void refresh(Player player) {
		Integer lives = player.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
		if (lives != null) {
			for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams())
				if (team.hasEntry(player.getName()))
					team.removeEntry(player.getName());

			if (lives > 3)
				darkGreenTeam.addEntry(player.getName());

			else if (lives == 3)
				greenTeam.addEntry(player.getName());

			else if (lives == 2)
				yellowTeam.addEntry(player.getName());

			else if (lives == 1)
				redTeam.addEntry(player.getName());

			else
				blackTeam.addEntry(player.getName());
		}
	}

	public List<Player> getAlivePlayers() {
		List<Player> alive = new ArrayList<>();
		Bukkit.getOnlinePlayers().forEach(player -> {
			if (player.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER) > 0)
				alive.add(player);
		});
		return alive;
	}

	public <T> List<T> shuffle(Collection<T> items) {
		List<T> itemsList = new ArrayList<>(items);
		List<T> result = new ArrayList<>();
		for (int i = 0; i < items.size(); i++)
			result.add(itemsList.remove(ThreadLocalRandom.current().nextInt(itemsList.size())));

		return result;
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);

		livesKey = new NamespacedKey(this, "lives");
		bountyKey = new NamespacedKey(this, "bounty");
		affectedCountKey = new NamespacedKey(this, "affectedCount");

		// setup list of bounties
		bountyList = new ArrayList<>();
		bountyList.add(Material.ENDER_EYE);
		bountyList.add(Material.RESPAWN_ANCHOR);
		bountyList.add(Material.SALMON_BUCKET);
		bountyList.add(Material.CAKE);
		bountyList.add(Material.HONEY_BOTTLE);
		bountyList.add(Material.FERMENTED_SPIDER_EYE);
		bountyList.add(Material.SLIME_BLOCK);
		bountyList.add(Material.SUSPICIOUS_STEW);
		bountyList.add(Material.PUMPKIN_PIE);
		bountyList.add(Material.COBWEB);
		bountyList.add(Material.MUSHROOM_STEW);
		bountyList.add(Material.POISONOUS_POTATO);
		bountyList.add(Material.BLACK_BUNDLE);
		bountyList.add(Material.BRUSH);
		bountyList.add(Material.BOOKSHELF);
		bountyList.add(Material.LEAD);
		bountyList.add(Material.FIRE_CHARGE);
		bountyList.add(Material.TNT_MINECART);
		bountyList.add(Material.SPYGLASS);
		bountyList.add(Material.JUKEBOX);
		bountyList.add(Material.LEATHER_HELMET);
		bountyList.add(Material.LILY_PAD);
		bountyList.add(Material.MAGMA_CREAM);
		bountyList.add(Material.OBSERVER);
		bountyList.add(Material.NAUTILUS_SHELL);
		bountyList.add(Material.COMPASS);
		bountyList.add(Material.DIRT);
		bountyList.add(Material.MAP);
		bountyList.add(Material.PAINTING);
		bountyList.add(Material.CLOCK);
		bountyList.add(Material.CROSSBOW);
		bountyList.add(Material.FIREWORK_ROCKET);
		bountyList.add(Material.GOLDEN_APPLE);
		bountyList.add(Material.COAL_BLOCK);
		bountyList.add(Material.NETHERITE_INGOT);
		bountyList.add(Material.GLISTERING_MELON_SLICE);
		bountyList.add(Material.GILDED_BLACKSTONE);
		bountyList.add(Material.EMERALD_BLOCK);
		bountyList.add(Material.NETHER_WART_BLOCK);
		bountyList.add(Material.DIAMOND_HOE);
		bountyList.add(Material.WRITABLE_BOOK);
		bountyList.add(Material.SOUL_LANTERN);
		bountyList.add(Material.ARMOR_STAND);
		bountyList.add(Material.FIREWORK_STAR);

		// setup list of wood types
		wood = new ArrayList<>();
		wood.add(Material.ACACIA_LOG);
		wood.add(Material.ACACIA_WOOD);
		wood.add(Material.STRIPPED_ACACIA_LOG);
		wood.add(Material.STRIPPED_ACACIA_WOOD);
		wood.add(Material.BIRCH_LOG);
		wood.add(Material.BIRCH_WOOD);
		wood.add(Material.STRIPPED_BIRCH_LOG);
		wood.add(Material.STRIPPED_BIRCH_WOOD);
		wood.add(Material.CHERRY_LOG);
		wood.add(Material.CHERRY_WOOD);
		wood.add(Material.STRIPPED_CHERRY_LOG);
		wood.add(Material.STRIPPED_CHERRY_WOOD);
		wood.add(Material.DARK_OAK_LOG);
		wood.add(Material.DARK_OAK_WOOD);
		wood.add(Material.STRIPPED_DARK_OAK_LOG);
		wood.add(Material.STRIPPED_DARK_OAK_WOOD);
		wood.add(Material.JUNGLE_LOG);
		wood.add(Material.JUNGLE_WOOD);
		wood.add(Material.STRIPPED_JUNGLE_LOG);
		wood.add(Material.STRIPPED_JUNGLE_WOOD);
		wood.add(Material.MANGROVE_LOG);
		wood.add(Material.MANGROVE_WOOD);
		wood.add(Material.STRIPPED_MANGROVE_LOG);
		wood.add(Material.STRIPPED_MANGROVE_WOOD);
		wood.add(Material.OAK_LOG);
		wood.add(Material.OAK_WOOD);
		wood.add(Material.STRIPPED_OAK_LOG);
		wood.add(Material.STRIPPED_OAK_WOOD);
		wood.add(Material.PALE_OAK_LOG);
		wood.add(Material.PALE_OAK_WOOD);
		wood.add(Material.STRIPPED_PALE_OAK_LOG);
		wood.add(Material.STRIPPED_PALE_OAK_WOOD);
		wood.add(Material.SPRUCE_LOG);
		wood.add(Material.SPRUCE_WOOD);
		wood.add(Material.STRIPPED_SPRUCE_LOG);
		wood.add(Material.STRIPPED_SPRUCE_WOOD);

		// setup teams
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();
		darkGreenTeam = board.getTeam("darkGreen");
		greenTeam = board.getTeam("green");
		yellowTeam = board.getTeam("yellow");
		redTeam = board.getTeam("red");
		blackTeam = board.getTeam("black");

		if (darkGreenTeam == null) {
			darkGreenTeam = board.registerNewTeam("darkGreen");
			darkGreenTeam.setColor(ChatColor.DARK_GREEN);
		}

		if (greenTeam == null) {
			greenTeam = board.registerNewTeam("green");
			greenTeam.setColor(ChatColor.GREEN);
		}

		if (yellowTeam == null) {
			yellowTeam = board.registerNewTeam("yellow");
			yellowTeam.setColor(ChatColor.YELLOW);
		}

		if (redTeam == null) {
			redTeam = board.registerNewTeam("red");
			redTeam.setColor(ChatColor.RED);
		}

		if (blackTeam == null) {
			blackTeam = board.registerNewTeam("black");
			blackTeam.setColor(ChatColor.BLACK);
		}

		// setup commands
		LivesCommand livesCommand = new LivesCommand(this);
		getCommand("lives").setExecutor(livesCommand);
		getCommand("lives").setTabCompleter(livesCommand);

		SetLivesCommand setLivesCommand = new SetLivesCommand(this);
		getCommand("set-lives").setExecutor(setLivesCommand);
		getCommand("set-lives").setTabCompleter(setLivesCommand);

		GiftLifeCommand giftLifeCommand = new GiftLifeCommand(this);
		getCommand("gift-life").setExecutor(giftLifeCommand);
		getCommand("gift-life").setTabCompleter(giftLifeCommand);

		EventCommand eventCommand = new EventCommand(this);
		getCommand("event").setExecutor(eventCommand);
		getCommand("event").setTabCompleter(eventCommand);

		// setup events
		globalEvents = new HashMap<>();

		globalEvents.put(1, new LifeSiphonEvent(this));
		globalEvents.put(2, new ReverseBountyEvent(this));
		globalEvents.put(3, new SoulSwapEvent(this));
		globalEvents.put(4, new UltraHardcoreEvent(this));
		globalEvents.put(5, new QuantumLeapEvent(this));
		globalEvents.put(8, new ParanoiaEvent(this));
		globalEvents.put(12, new DemureEvent(this));
		globalEvents.put(19, new BountyEvent(this));
		globalEvents.put(20, new BreathLifeEvent(this));
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPlayedBefore()) {
			PersistentDataContainer container = player.getPersistentDataContainer();
			container.set(livesKey, PersistentDataType.INTEGER, 6);
			container.set(baseHealthKey, PersistentDataType.INTEGER, 20);
			container.set(affectedCountKey, PersistentDataType.INTEGER, 0);
		}
		refresh(player);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		int lives = player.getPersistentDataContainer().get(livesKey, PersistentDataType.INTEGER);
		if (lives > 1) {
			player.getPersistentDataContainer().set(livesKey, PersistentDataType.INTEGER, lives - 1);
		} else {
			player.getPersistentDataContainer().set(livesKey, PersistentDataType.INTEGER, 0);
			player.setGameMode(GameMode.SPECTATOR);
		}
		refresh(player);
	}

	boolean miningTriggers(Material type, double chance) {
		if (chance < TRIGGER_WOOD && wood.contains(type)
				|| chance < TRIGGER_STONE
						&& (type == Material.STONE || type == Material.DEEPSLATE || type == Material.NETHERRACK)
				|| chance < TRIGGER_COAL && (type == Material.COAL_ORE || type == Material.DEEPSLATE_COAL_ORE)
				|| chance < TRIGGER_COPPER && (type == Material.COPPER_ORE || type == Material.DEEPSLATE_COPPER_ORE)
				|| chance < TRIGGER_IRON && (type == Material.IRON_ORE || type == Material.DEEPSLATE_IRON_ORE)
				|| chance < TRIGGER_GOLD && (type == Material.GOLD_ORE || type == Material.DEEPSLATE_GOLD_ORE
						|| type == Material.NETHER_GOLD_ORE)
				|| chance < TRIGGER_REDSTONE
						&& (type == Material.REDSTONE_ORE || type == Material.DEEPSLATE_REDSTONE_ORE)
				|| chance < TRIGGER_LAPIS && (type == Material.LAPIS_ORE || type == Material.DEEPSLATE_LAPIS_ORE)
				|| chance < TRIGGER_DIAMOND && (type == Material.DIAMOND_ORE || type == Material.DEEPSLATE_DIAMOND_ORE)
				|| chance < TRIGGER_EMERALD && (type == Material.EMERALD_ORE || type == Material.DEEPSLATE_EMERALD_ORE)
				|| chance < TRIGGER_QUARTZ && type == Material.NETHER_QUARTZ_ORE
				|| chance < TRIGGER_ANCIENT_DEBRIS && type == Material.ANCIENT_DEBRIS)
			return true;

		return false;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		Block block = event.getBlock();
		Location location = block.getLocation();
		Material type = block.getType();

		ThreadLocalRandom random = ThreadLocalRandom.current();
		double chance = random.nextDouble();
		int outcome = random.nextInt(20);

		if (!miningTriggers(type, chance)) {
			player.sendMessage("no event");
			return;
		}

		player.sendMessage(String.format("%d", outcome + 1));

		if (outcome == 0) {
			for (int i = 0; i < 5; i++)
				world.spawnEntity(location, EntityType.SILVERFISH);

		} else if (outcome == 19) {

		} else if (outcome < 7) {

		} else if (outcome > 11) {

		}
	}

}
