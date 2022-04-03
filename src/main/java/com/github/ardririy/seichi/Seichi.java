package com.github.ardririy.seichi;

import org.bukkit.plugin.java.JavaPlugin;


public final class Seichi extends JavaPlugin {

    @Override
    public void onEnable() {
        new PlayerListener(this);

        //Command
        getCommand("grading").setExecutor(new grading());
    }

}
