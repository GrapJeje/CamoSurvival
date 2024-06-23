package eu.camonetwork.camosurvial;

import eu.camonetwork.camosurvial.Commands.BlocksBroken;
import eu.camonetwork.camosurvial.Events.BlockBreak;
import eu.camonetwork.camosurvial.Events.OnPlayerKill;
import eu.camonetwork.camosurvial.infrastructure.Configs.ConfigManager;
import eu.camonetwork.camosurvial.infrastructure.Configs.DefaultConfig;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class CamoSurvial extends JavaPlugin {

    public static DefaultConfig defaultConfig = new DefaultConfig();
    public static List<ConfigManager> configList = new ArrayList<>();
    public static CamoSurvial instance;

    private void registerEvent(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void registerCommand(String cmdName, CommandExecutor executor, TabCompleter tabCompleter) {
        PluginCommand command = getServer().getPluginCommand(cmdName);
        if (command != null) {
            command.setExecutor(executor);
            if (tabCompleter != null) {
                command.setTabCompleter(tabCompleter);
            }
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        registerEvent(new BlockBreak());
        registerEvent(new OnPlayerKill());
        registerCommand("leaderboard", new BlocksBroken(), null);

        saveDefaultConfig();

        defaultConfig.reload();

        configList.add(defaultConfig);

        configList.forEach(ConfigManager::reload);

    }

}
