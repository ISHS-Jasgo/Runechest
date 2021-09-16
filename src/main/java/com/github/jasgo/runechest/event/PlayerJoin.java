package com.github.jasgo.runechest.event;

import com.github.jasgo.runechest.Chest;
import com.github.jasgo.runechest.Runechest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        File file = new File(Runechest.getPlugin(Runechest.class).getDataFolder() + "/" + event.getPlayer().getUniqueId().toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(!file.exists()) {
            config.set("owner", event.getPlayer().getUniqueId().toString());
            config.set("contents", new ArrayList<>());
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Chest chest = new Chest(event.getPlayer().getUniqueId(), new ArrayList<>());
            chest.load(event.getPlayer(), chest);
        } else {
            UUID uuid = UUID.fromString(config.getString("owner"));
            List<ItemStack> contents = (List<ItemStack>) config.getList("contents");
            Chest chest = new Chest(uuid, contents);
            chest.load(event.getPlayer(), chest);
        }
    }
}
