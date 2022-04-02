package com.github.ardririy.seichi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.*;
import java.util.UUID;

public class EventListener implements Listener{
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) throws SQLException {

        //ブロック破壊時にdbを更新
        final String URL = "jdbc:sqlite:playerDate.db";
        final String sqlSelect = "select * from player where uuid = ?";
        final String sqlUpdate = "update player set digging = ? where uuid = ?";

        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();

        try(Connection conn = DriverManager.getConnection(URL)){
            conn.setAutoCommit(false);

            try(PreparedStatement p = conn.prepareStatement(sqlSelect)){
                p.setString(1, String.valueOf(uuid));

                try (ResultSet rs = p.executeQuery()) {
                    Integer dig_amount = rs.getInt("digging");

                    try(PreparedStatement ps = conn.prepareStatement(sqlUpdate)){
                        ps.setInt(1,dig_amount + 1);
                        ps.setString(2, String.valueOf(uuid));

                        ps.executeUpdate();
                        conn.commit();
                    }
                }
            }
        }
    }
}
