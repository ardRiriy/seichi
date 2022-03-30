package com.github.ardririy.seichi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;



public final class Seichi extends JavaPlugin {

    private static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin is enabled");

        new PlayerListener(this);

        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private static Plugin getPlugin(){
        return plugin;
    }
}
