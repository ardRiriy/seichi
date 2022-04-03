package com.github.ardririy.seichi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;



public final class Seichi extends JavaPlugin {

    private static Plugin plugin;
    @Override
    public void onEnable() {
        new PlayerListener(this);

        //Event
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(new EventListener(), this);

        //Command
        getCommand("grading").setExecutor(new grading());
    }

}
