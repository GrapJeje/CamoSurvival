package eu.camonetwork.camosurvial.Events;

import eu.camonetwork.camosurvial.CamoSurvial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BLockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        assert player != null;

        int blocks = CamoSurvial.defaultConfig.getBlocksPlaced(player.getUniqueId());

        int newBlocks = blocks + 1;

        CamoSurvial.defaultConfig.setBlocksPlaced(player.getUniqueId(), newBlocks);

    }

}
