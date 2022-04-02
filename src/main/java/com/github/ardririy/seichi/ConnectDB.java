package com.github.ardririy.seichi;


import java.sql.*;

public class ConnectDB {

    final String URL = "jdbc:sqlite:playerDate.db";
    final String sqlInsert = "insert into player(name, uuid, digging) VALUES(?, ?, ?)";
    final String sqlSelect = "select * from player where uuid = ?";
    final String sqlUpdate_name = "update player set name = ? where uuid = ?";
    final String sqlUpdate_Dig = "update player set digging = ? where uuid = ?";


    public void atPlayerJoin(String name, String uuid){
        try(Connection conn = DriverManager.getConnection(URL)){
            conn.setAutoCommit(false);

            try(PreparedStatement p = conn.prepareStatement(sqlSelect)){
                p.setString(1, uuid);

                try(ResultSet rs = p.executeQuery()){
                    if(rs.isClosed()){
                        //新たに登録をする

                        try(PreparedStatement ps = conn.prepareStatement(sqlInsert)){
                            ps.setString(1, name);
                            ps.setString(2, uuid);
                            ps.setInt(3,0);

                            ps.executeUpdate();
                            conn.commit();

                        }catch(Exception e){
                            conn.rollback();
                            throw e;
                        }

                    }else{
                        //名前のみ変更
                        try(PreparedStatement ps = conn.prepareStatement(sqlUpdate_name)){
                            ps.setString(1, name);
                            ps.setString(2, uuid);

                            ps.executeUpdate();
                            conn.commit();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void atBlockBrake(String uuid){
        try(Connection conn = DriverManager.getConnection(URL)){
            conn.setAutoCommit(false);

            try(PreparedStatement p = conn.prepareStatement(sqlSelect)){
                p.setString(1, String.valueOf(uuid));

                try (ResultSet rs = p.executeQuery()) {
                    Integer dig_amount = rs.getInt("digging");

                    try(PreparedStatement ps = conn.prepareStatement(sqlUpdate_Dig)){
                        ps.setInt(1,dig_amount + 1);
                        ps.setString(2, String.valueOf(uuid));

                        ps.executeUpdate();
                        conn.commit();
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
