package eu.camonetwork.camosurvial.Events;

import eu.camonetwork.camosurvial.CamoSurvial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        assert player != null;

        int blocks = CamoSurvial.defaultConfig.getBlocksBroken(player.getUniqueId());

        int newBlocks = blocks + 1;

        CamoSurvial.defaultConfig.setBlocksBroken(player.getUniqueId(), newBlocks);

    }

}
