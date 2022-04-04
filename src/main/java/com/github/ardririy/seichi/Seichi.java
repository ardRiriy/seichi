package com.github.ardririy.seichi;

import com.github.ardririy.seichi.commands.ranking;
import com.github.ardririy.seichi.commands.status;
import com.github.ardririy.seichi.commands.tool;
import com.github.ardririy.seichi.commands.trash;
import org.bukkit.plugin.java.JavaPlugin;


public final class Seichi extends JavaPlugin {

    @Override
    public void onEnable() {
        new PlayerListener(this);

        //Command
        getCommand("trash").setExecutor(new trash());
        getCommand("tool").setExecutor(new tool());
        getCommand("ranking").setExecutor(new ranking());
        getCommand("status").setExecutor(new status());
    }

}
