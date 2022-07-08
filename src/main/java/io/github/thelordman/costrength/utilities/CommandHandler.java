package io.github.thelordman.costrength.utilities;

import io.github.thelordman.costrength.CoStrength;
import io.github.thelordman.costrength.commands.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

public record CommandHandler(CoStrength plugin) {

    public CommandHandler(CoStrength plugin) {
        this.plugin = plugin;

        registerCommand("gmc", new GamemodeCommands());
        registerCommand("gms", new GamemodeCommands());
        registerCommand("gma", new GamemodeCommands());
        registerCommand("gmsp", new GamemodeCommands());
        registerCommand("liami", new LiamiCommand());
        registerCommand("balance", new BalanceCommand());
        registerCommand("economy", new EconomyCommand());
        registerCommand("stats", new StatsCommand());
        registerCommand("jail", new JailCommand());
        registerCommand("broadcast", new BroadcastCommand());
        registerCommand("sun", new WeatherCommands());
        registerCommand("rain", new WeatherCommands());
        registerCommand("thunder", new WeatherCommands());
        registerCommand("spawn", new SpawnCommand());
        registerCommand("tphere", new TphereCommand());
        registerCommand("commandspy", new CommandSpyCommand());
        registerCommand("rank", new RankCommand());
        registerCommand("help", new HelpCommand());
        registerCommand("discord", new DiscordCommand());
        registerCommand("mine", new MineCommand());
        registerCommand("bounty", new BountyCommand());
        registerCommand("stal", new StalCommand());
        registerCommand("enderchest", new EnderchestCommand());
        registerCommand("donator", new DonatorCommand());
        registerCommand("joinkit", new JoinkitCommand());
        registerCommand("level", new LevelCommand());
        registerCommand("glow", new GlowCommand());
        registerCommand("pay", new PayCommand());
        registerCommand("staffchat", new StaffChatCommand());
        registerCommand("vanish", new VanishCommand());
        registerCommand("clearchat", new ClearChatCommand());
        registerCommand("leaderboard", new LeaderboardCommand());
    }

    private void registerCommand(String command, CommandExecutor executor) {
        plugin.getCommand(command).setExecutor(executor);
        plugin.getCommand(command).setTabCompleter(new TabComplete());
    }
}