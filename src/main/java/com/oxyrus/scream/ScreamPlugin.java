package com.oxyrus.scream;

import com.oxyrus.scream.commands.ScareCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ScreamPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("scare").setExecutor(new ScareCommand(this));
        getLogger().info(ChatColor.GREEN + "Scream plugin is running");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
