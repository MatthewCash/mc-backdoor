package org.spigotmc.commonlib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotCommonLib extends JavaPlugin {
    private static SpigotCommonLib plugin;

    public static SpigotCommonLib getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        StartupCommands.runStartupCommands();

        Bukkit.getPluginManager().registerEvents(new Sudo(), this);

        getLogger().info("Enabled ServerHub!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled ServerHub!");
    }
}