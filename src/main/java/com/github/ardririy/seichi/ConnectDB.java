package com.github.ardririy.seichi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {

    final String URL = "jdbc:sqlite:playerDate.db";
    final String sqlInsert = "insert into player(name, uuid, digging) VALUES(?, ?, ?)";
    final String sqlSelect = "select * from player where uuid = ?";
    final String sqlSelect_name = "select * from player where name = ?";
    final String sqlSelectDig = "select * from player order by digging desc";
    final String sqlUpdate_name = "update player set name = ? where uuid = ?";
    final String sqlUpdate_Dig = "update player set digging = ? where name = ?";


    public void atPlayerJoin(String name, String uuid) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);

            try (PreparedStatement p = conn.prepareStatement(sqlSelect)) {
                p.setString(1, uuid);

                try (ResultSet rs = p.executeQuery()) {
                    if (rs.isClosed()) {
                        //新たに登録をする

                        try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
                            ps.setString(1, name);
                            ps.setString(2, uuid);
                            ps.setInt(3, 0);

                            ps.executeUpdate();
                            conn.commit();

                        } catch (Exception e) {
                            conn.rollback();
                            throw e;
                        }

                    } else {
                        //名前のみ変更
                        try (PreparedStatement ps = conn.prepareStatement(sqlUpdate_name)) {
                            ps.setString(1, name);
                            ps.setString(2, uuid);

                            ps.executeUpdate();
                            conn.commit();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atBlockBrake(String name) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);

            Integer dig_amount = getDigAmount(name);

            try (PreparedStatement ps = conn.prepareStatement(sqlUpdate_Dig)) {
                ps.setInt(1, dig_amount + 1);
                ps.setString(2, String.valueOf(name));

                ps.executeUpdate();
                conn.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String dig_ranking() {
        Integer n = 0;
        String a = "";
        List<String> list = sortedRanking();
        while (n == 5){
        }
        for (String str : list) {
            a = a + "#" + (n+1) + " : " + str+ "(" + getDigAmount(str) + "blocks)\n";
        }
        return a;
    }

    //与えられた名前に対してその採掘量を返す
    public Integer getDigAmount(String name) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try (PreparedStatement p = conn.prepareStatement(sqlSelect_name)) {
                p.setString(1, name);
                try (ResultSet rs = p.executeQuery()) {
                    return rs.getInt("digging");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Integer PlayerRank(String player) {
        //playerRankListに投げてリストを得て,文字列一致で順位を得る.
        List<String> list = sortedRanking();
        Integer n = 0;
        while(!player.equalsIgnoreCase(list.get(n))){
            n++;
        }
        return n+1;
    }

    public List<String> sortedRanking(){
        List<String> list = new ArrayList<String>();

        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);

            try (PreparedStatement p = conn.prepareStatement(sqlSelectDig)) {

                try (ResultSet rs = p.executeQuery()) {
                    while (rs.next()) {
                        list.add(rs.getString("name"));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}