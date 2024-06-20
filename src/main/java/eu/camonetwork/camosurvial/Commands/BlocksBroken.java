package eu.camonetwork.camosurvial.Commands;

import eu.camonetwork.camosurvial.CamoSurvial;
import eu.camonetwork.camosurvial.infrastructure.Configs.DefaultConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BlocksBroken implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("This command can only be run by a player.");
            return false;
        }

        Player player = (Player) sender;
        DefaultConfig defaultConfig = CamoSurvial.defaultConfig;

        Map<UUID, Integer> blocksBrokenMap = defaultConfig.getAllBlocksBroken();

        List<Map.Entry<UUID, Integer>> sortedEntries = blocksBrokenMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toList());

        player.sendMessage("§3Top 10 spelers met de meeste gehakte blokken:");

        for (int i = 0; i < Math.min(10, sortedEntries.size()); i++) {
            Map.Entry<UUID, Integer> entry = sortedEntries.get(i);
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(entry.getKey());
            String playerName = offlinePlayer.getName() != null ? offlinePlayer.getName() : "Unknown";
            player.sendMessage("§a" + (i + 1) + ". " + playerName + " - " + entry.getValue() + " blokken gehakt");
        }

        player.sendMessage("§3Jij hebt " + defaultConfig.getBlocksBroken(player.getUniqueId()) + " blokken gehakt.");

        return true;
    }
}
