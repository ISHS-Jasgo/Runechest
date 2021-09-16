package com.github.jasgo.runechest;

import com.github.jasgo.runechest.cmd.ChestCommand;
import com.github.jasgo.runechest.event.InventoryClose;
import com.github.jasgo.runechest.event.PlayerJoin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Runechest extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        this.getCommand("rchest").setExecutor(new ChestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
