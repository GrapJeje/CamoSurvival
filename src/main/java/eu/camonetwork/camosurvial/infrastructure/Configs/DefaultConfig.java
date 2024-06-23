package eu.camonetwork.camosurvial.infrastructure.Configs;

import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

public class DefaultConfig extends ConfigManager {
    public DefaultConfig() {
        this.setFileName("config.yml");
    }

    public int getBlocksBroken(UUID uuid) {
        return getConfig().getInt("players." + uuid.toString() + ".blocksbroken", 0);
    }

    public void setBlocksBroken(UUID uuid, int blocks) {
        getConfig().set("players." + uuid.toString() + ".blocksbroken", blocks);
        this.save();
    }

    public Map<UUID, Integer> getAllBlocksBroken() {
        Map<UUID, Integer> blocksBrokenMap = new HashMap<>();
        ConfigurationSection playersSection = getConfig().getConfigurationSection("players");

        if (playersSection != null) {
            for (String key : playersSection.getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                int blocksBroken = playersSection.getInt(key + ".blocksbroken", 0);
                blocksBrokenMap.put(uuid, blocksBroken);
            }
        }

        return blocksBrokenMap;
    }
}
