package org.jbashiri.utils;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.Player;
import org.jbashiri.model.classes.Class;

import java.sql.*;

public class DataBase {
    private static final String dbLink = "jdbc:sqlite::resource:heroes.db";
    private static Connection connection;

    public static void connect() throws CustomException {
        Connection connect;
        try {
            connect = DriverManager.getConnection(dbLink);
        } catch (SQLException e) {
            throw new CustomException("connect to database failed: " + e);
        }
        connection = connect;
    }

    public static void disconnect() throws CustomException {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new CustomException("disconnect to database failed: " + e);
        }
        connection = null;
    }


    private static Connection getConnect() throws CustomException {
        if (connection == null)
            connect();
        return connection;
    }

    public static int addNewHero(Player player, Class heroClass) {
                            //player:
        String sqlQuery = "INSERT INTO heroes(playerName, level, experience, score, healthBanks, " +
                //class
                "className, hp, maxHp, atk, maxAtk, def, maxDef, luck, maxLuck, " +
                //artifacts
                "artWeaponName, artWeaponBonus, artChestName, artChestBonus, artHeadName, artHeadBonus) " +
                //values [?]
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int id = 0;
        try (PreparedStatement pstmt = getConnect().prepareStatement(sqlQuery)) {
            //player
            pstmt.setString(1, player.getName());
            pstmt.setInt(2, player.getLevel());
            pstmt.setInt(3, player.getExperience());
            pstmt.setInt(4, player.getScore());
            pstmt.setInt(5, player.getCountHealthBanks());
            //class
            Class temp = player.getHeroClass();
            pstmt.setString(6, temp.getName());
            pstmt.setInt(7, temp.hp);
            pstmt.setInt(8, temp.getMaxHp());
            pstmt.setInt(9, temp.atk);
            pstmt.setInt(10, temp.getMaxAtk());
            pstmt.setInt(11, temp.def);
            pstmt.setInt(12, temp.getMaxDef());
            pstmt.setInt(13, temp.luck);
            pstmt.setInt(14, temp.getMaxLuck());
            //artifacts
            pstmt.setString(15, player.getArtifactWeapon().getName());
            pstmt.setInt(16, player.getArtifactWeapon().getBonus());
            pstmt.setString(17, player.getArtifactChest().getName());
            pstmt.setInt(18, player.getArtifactChest().getBonus());
            pstmt.setString(19, player.getArtifactHead().getName());
            pstmt.setInt(20, player.getArtifactHead().getBonus());
            pstmt.executeUpdate();

            Statement stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT seq FROM sqlite_sequence WHERE name=\"heroes\"");
            if (rs.next())
                id = rs.getInt("seq");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
