package org.jbashiri.utils;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.Player;
import org.jbashiri.model.classes.Class;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private static final String dbLink = "jdbc:sqlite:src/main/resources/heroes.db";
    private static Connection connection;

    public static void connect() throws CustomException {
        Connection connect;
        try {
            java.lang.Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection(dbLink);
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


    public static Connection getConnect() throws CustomException {
        if (connection == null)
            connect();
        return connection;
    }

    //gen file db
    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:/src/main/resources/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //gen table
    public static void createTable() throws SQLException, ClassNotFoundException, CustomException {
        java.lang.Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection(dbLink);
        Statement stmt = connection.createStatement();
        String query = "create table " + "heroes" + " ( " +
                "id int , " +
                "playerName varchar(30), " +
                "playerLevel int , " +
                "experience int , " +
                "score int , " +
                "countHealthBanks int , " +
                "className varchar(30) , " +
                "hp int , " +
                "maxHp int , " +
                "atk int , " +
                "maxAtk int , " +
                "def int , " +
                "maxDef int , " +
                "luck int , " +
                "maxLuck int , " +
                "weaponName varchar(30) , " +
                "weaponBonus varchar(30) , " +
                "chestName varchar(30) , " +
                "chestBonus varchar(30) , " +
                "headName varchar(30) , " +
                "headBonus varchar(30) " +
                " )";
        System.out.println(query);
        System.out.println(stmt);
        stmt.executeUpdate(query);
        stmt.close();
    }

    public static void addNewHero(Player player) {
                            //player:
        String sqlQuery = "INSERT INTO heroes(playerName, playerLevel, experience, score, countHealthBanks, " +
                //class
                "className, hp, maxHp, atk, maxAtk, def, maxDef, luck, maxLuck, " +
                //artifacts
                "weaponName, weaponBonus, chestName, chestBonus, headName, headBonus) " +
                //values [?]
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> getAll() {
        String sql = "SELECT * FROM heroes";
        ArrayList<String> list = new ArrayList<>();

        try (Statement stmt = getConnect().createStatement()) {
            ResultSet res = stmt.executeQuery(sql);
            for (int i = 1; res.next(); i++) {
                list.add(String.format("%d. %s (%s)", i, res.getString("playerName"), res.getString("className")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
