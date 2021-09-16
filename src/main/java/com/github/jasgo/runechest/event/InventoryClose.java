package com.github.jasgo.runechest.event;

import com.github.jasgo.runechest.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Arrays;

public class InventoryClose implements Listener {
    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        if(event.getView().getTitle().equalsIgnoreCase("가방")) {
            Chest chest = new Chest(event.getPlayer().getUniqueId(), Arrays.asList(event.getInventory().getContents()));
            chest.load((Player) event.getPlayer(), chest);
            chest.save((Player) event.getPlayer());
        }
    }
}
