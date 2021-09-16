package com.github.jasgo.runechest;

import com.github.jasgo.runechest.event.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Chest {

    private final UUID owner;
    private final List<ItemStack> contents;
    public static HashMap<Player, Chest> chestHashMap = new HashMap<>();

    public Chest(UUID owner, List<ItemStack> contents) {
        this.owner = owner;
        this.contents = contents;
    }

    public void open() {
        Inventory inv = Bukkit.createInventory(null, 54, "가방");
        inv.setContents(contents.toArray(new ItemStack[contents.size()]));
        Bukkit.getPlayer(owner).openInventory(inv);
    }

    public void save(Player player) {
        File file = new File(Runechest.getPlugin(Runechest.class).getDataFolder() + "/" + player.getUniqueId().toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Chest chest = getChest(player);
        config.set("owner", chest.getOwner().toString());
        config.set("contents", chest.getContents());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UUID getOwner() {
        return owner;
    }

    public List<ItemStack> getContents() {
        return contents;
    }
    public static Chest getChest(Player player) {
        return chestHashMap.get(player);
    }
    public void load(Player player, Chest chest) {
        chestHashMap.put(player, chest);
    }
}
