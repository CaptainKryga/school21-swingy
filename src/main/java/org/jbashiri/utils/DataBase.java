package org.jbashiri.utils;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.Player;
import org.jbashiri.model.classes.Class;

import java.sql.*;

public class DataBase {
    private static final String dbLink = "jdbc:sqlite:resource:heroes.db";
    private static Connection connection;

    public static void connect() throws CustomException {
        Connection connect;
        try {
            java.lang.Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite::resource:heroes.db");
        } catch (SQLException e) {
            throw new CustomException("connect to database failed: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public static int addNewHero(Player player) {
                            //player:
        String sqlQuery = "INSERT INTO heroes(playerName, level, experience, score, healthBanks, " +
                //class
                "className, hp, maxHp, atk, maxAtk, def, maxDef, luck, maxLuck, " +
                //artifacts
                "artWeaponName, artWeaponBonus, artChestName, artChestBonus, artHeadName, artHeadBonus) " +
                //values [?]
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int id = 0;
        try (PreparedStatement prepare = getConnect().prepareStatement(sqlQuery)) {
            //player
            prepare.setString(1, player.getName());
            prepare.setInt(2, player.getLevel());
            prepare.setInt(3, player.getExperience());
            prepare.setInt(4, player.getScore());
            prepare.setInt(5, player.getCountHealthBanks());
            //class
            Class temp = player.getHeroClass();
            prepare.setString(6, temp.getName());
            prepare.setInt(7, temp.hp);
            prepare.setInt(8, temp.getMaxHp());
            prepare.setInt(9, temp.atk);
            prepare.setInt(10, temp.getMaxAtk());
            prepare.setInt(11, temp.def);
            prepare.setInt(12, temp.getMaxDef());
            prepare.setInt(13, temp.luck);
            prepare.setInt(14, temp.getMaxLuck());
            //artifacts
            prepare.setString(15, player.getArtifactWeapon().getName());
            prepare.setInt(16, player.getArtifactWeapon().getBonus());
            prepare.setString(17, player.getArtifactChest().getName());
            prepare.setInt(18, player.getArtifactChest().getBonus());
            prepare.setString(19, player.getArtifactHead().getName());
            prepare.setInt(20, player.getArtifactHead().getBonus());
            prepare.executeUpdate();

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
