package eu.camonetwork.camosurvial.infrastructure.Builders;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack item = new ItemStack(Material.AIR);

    public ItemStack build() {
        return item;
    }
    public ItemStack toBukkit() {
        return item;
    }

    public static ItemBuilder fromType(Material type) {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.setType(type);
        return itemBuilder;
    }

    public ItemBuilder setType(Material type) {
        item.setType(type);

        return this;
    }

    public ItemBuilder setSkullPlayer(OfflinePlayer skullPlayer) {
        item.setType(Material.PLAYER_HEAD);

        if (item.getItemMeta() != null) {
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setOwningPlayer(skullPlayer);

            item.setItemMeta(skullMeta);
        }

        return this;
    }

    public ItemBuilder setDisplayName(String displayName) {
        if (item.getItemMeta() == null) {
            return this;
        }

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName((displayName));
        item.setItemMeta(itemMeta);

        return this;
    }

    public List<String> getLores() {

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) {
            return new ArrayList<>();
        }

        return itemMeta.getLore();
    }

    public ItemBuilder setLores(List<String> lores) {
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) {
            return this;
        }

        List<String> coloredLores = new ArrayList<>();
        for (String lore : lores) {
            coloredLores.add((lore));
        }

        itemMeta.setLore(coloredLores);
        item.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder addLore(String lore) {
        List<String> lores = this.getLores();

        if (lores == null) {
            lores = new ArrayList<>();
        }

        lores.add((lore));

        return this.setLores(lores);
    }

}
