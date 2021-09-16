package com.github.jasgo.runechest.cmd;

import com.github.jasgo.runechest.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("rchest")) {
            Chest chest = Chest.getChest((Player) sender);
            chest.open();
        }
        return false;
    }
}
