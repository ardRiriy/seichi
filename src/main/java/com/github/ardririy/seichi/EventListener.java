package com.github.ardririy.seichi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class EventListener implements Listener{

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){

        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        ConnectDB cdb = new ConnectDB();
        cdb.atBlockBrake(String.valueOf(uuid));
    }
}
