package com.github.ardririy.seichi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerListener implements Listener {
    public PlayerListener(Seichi plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        ConnectDB cdb = new ConnectDB();
        cdb.atBlockBrake(String.valueOf(uuid));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        String name = player.getName();
        UUID uuid = player.getUniqueId();


        ConnectDB cdb = new ConnectDB();
        cdb.atPlayerJoin(name, String.valueOf(uuid));
    }
}
