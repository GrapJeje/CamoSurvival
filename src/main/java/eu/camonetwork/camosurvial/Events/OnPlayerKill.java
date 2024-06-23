package eu.camonetwork.camosurvial.Events;

import eu.camonetwork.camosurvial.infrastructure.Builders.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerKill implements Listener {

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player killed = event.getEntity();
        Player killer = killed.getKiller();

        if (killer != null) {
            event.getDrops().add(pheadBuilder(killed, killer));
        }
    }

    private static ItemStack pheadBuilder(Player killed, Player killer) {
        ItemBuilder pheadBuilder = ItemBuilder.fromType(Material.PLAYER_HEAD)
                .setSkullPlayer(killed)
                .setDisplayName("§f" + killed.getName() + "§f's head")
                .addLore("§eVermoord door: " + killer.getName());

        return pheadBuilder.toBukkit();
    }
}
