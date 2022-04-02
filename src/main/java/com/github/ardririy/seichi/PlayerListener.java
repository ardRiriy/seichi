package com.github.ardririy.seichi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.UUID;

public class PlayerListener implements Listener {
    public PlayerListener(Seichi plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        //データベースへの登録.Todo:別クラスに分けたい
        Player player = event.getPlayer();

        String name = player.getName();
        UUID uuid = player.getUniqueId();



        final String URL = "jdbc:sqlite:playerDate.db";
        final String sqlSelect = "select uuid from player where uuid = ?;";
        final String sqlInsert = "insert into player(name, uuid, digging) VALUES(?, ?, ?)";


        final String sqlUpdate = "update player set name = ?";

        //db接続
        try(Connection conn = DriverManager.getConnection(URL)){

            conn.setAutoCommit(false);


            try(PreparedStatement p = conn.prepareStatement(sqlSelect)){

                p.setString(1, String.valueOf(uuid));

                try(ResultSet rs = p.executeQuery()){

                    if(rs.isClosed()){
                        //新たに登録をする

                        //player dateをdbに保存
                        try(PreparedStatement ps = conn.prepareStatement(sqlInsert)){
                            ps.setString(1, name);
                            ps.setString(2, String.valueOf(uuid));
                            ps.setInt(3,0);

                            ps.executeUpdate();
                            conn.commit();

                        }   catch (Exception e) {
                            conn.rollback();
                            System.out.println("rollback");
                            throw e;
                        }

                    }else{

                        //名前のみ変更
                        try(PreparedStatement ps = conn.prepareStatement(sqlUpdate)){
                            ps.setString(1, name);

                            ps.executeUpdate();
                            conn.commit();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("処理が完了しました");
        }
    }
}
