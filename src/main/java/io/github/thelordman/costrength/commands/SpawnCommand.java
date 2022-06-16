package io.github.thelordman.costrength.commands;

import io.github.thelordman.costrength.CoStrength;
import io.github.thelordman.costrength.ranks.RankManager;
import io.github.thelordman.costrength.utilities.Methods;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    public final boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof ConsoleCommandSender && args.length == 0 | (!RankManager.hasPermission((OfflinePlayer) sender, (byte) 2)) && args.length > 0) return false;
        if (Methods.inCombat((Player) sender)) {
            sender.sendMessage(Methods.cStr("&cYou are combat tagged and cannot run that command."));
            return true;
        }

        Player target = args.length > 0 ? Bukkit.getPlayer(args[0]) : (Player) sender;
        if (target == null) return false;

        Bukkit.getScheduler().runTaskLater(CoStrength.instance, () -> spawn(target), target == sender ? 100L : 0L);

        return true;
    }

    private void spawn(Player player) {
        Methods.teleportPlayerToSpawn(player, PlayerTeleportEvent.TeleportCause.COMMAND);
        player.sendMessage(Methods.cStr("&6You have been sent to spawn."));
    }
}