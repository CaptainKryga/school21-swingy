package org.jbashiri.utils;

import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.Player;
import org.jbashiri.model.classes.HeroClass;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private static final String dbLink = "jdbc:sqlite:src/main/resources/heroes.db";
    private static Connection connection;

    public DataBase() throws CustomException {
        connect();
    }

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
    public static void createTable(String name) throws SQLException, ClassNotFoundException, CustomException {
        java.lang.Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection(dbLink);
        Statement stmt = connection.createStatement();
        String query = "create table " + name + " ( " +
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
                "headBonus varchar(30), " +
                "posX int, " +
                "posY int, " +
                "playerMap varchar(10000), " +
                "enemyMap varchar(10000) " +
                " )";
//        System.out.println(query);
//        System.out.println(stmt);
        stmt.executeUpdate(query);
        stmt.close();
    }

    public static void addNewHero(Player player) {
        CustomLogger.singleton.printLog("ADD NEW HERO", 2);
                            //player:
        String sqlQuery = "INSERT INTO heroes(playerName, playerLevel, experience, score, countHealthBanks, " +
                //class
                "className, hp, maxHp, atk, maxAtk, def, maxDef, luck, maxLuck, " +
                //artifacts
                "weaponName, weaponBonus, chestName, chestBonus, headName, headBonus, " +
                "posX, posY, playerMap, enemyMap) " +
                //values [?]
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prepare = getConnect().prepareStatement(sqlQuery)) {
            //player
            prepare.setString(1, player.getPlayerName());
            prepare.setInt(2, player.getLevel());
            prepare.setInt(3, player.getExperience());
            prepare.setInt(4, player.getScore());
            prepare.setInt(5, player.getCountHealthBanks());
            //class
            HeroClass temp = player.getHeroClass();
            prepare.setString(6, temp.getClassName().toString());
            prepare.setInt(7, temp.hp);
            prepare.setInt(8, temp.getMaxHp());
            prepare.setInt(9, temp.atk);
            prepare.setInt(10, temp.getMaxAtk());
            prepare.setInt(11, temp.def);
            prepare.setInt(12, temp.getMaxDef());
            prepare.setInt(13, temp.luck);
            prepare.setInt(14, temp.getMaxLuck());
            //artifacts
            prepare.setString(15, player.getArtifactWeapon().getArtName());
            prepare.setInt(16, player.getArtifactWeapon().getBonus());
            prepare.setString(17, player.getArtifactChest().getArtName());
            prepare.setInt(18, player.getArtifactChest().getBonus());
            prepare.setString(19, player.getArtifactHead().getArtName());
            prepare.setInt(20, player.getArtifactHead().getBonus());

            prepare.setInt(21, player.getPos().x);
            prepare.setInt(22, player.getPos().y);

            prepare.setString(23, player.encode(player.mapPlayer));
            prepare.setString(24, player.encode(player.mapEnemy));
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
                list.add(String.format("%s (%s)", res.getString("playerName"), res.getString("className")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    //delete all heroes
    public static void deleteAllHeroes() {
        ArrayList<String> list = getAll();
        try (Connection connection = getConnect()) {
            for(int i = 0; i < list.size(); i++) {
                PreparedStatement st = connection.prepareStatement("DELETE FROM heroes WHERE playerName = '" + list.get(i).split(" ")[0] + "';");
                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }

    public static Player getHero(String playerName) {
        String sql = "SELECT * FROM heroes";
        Player load = null;

        try (Statement stmt = getConnect().createStatement()) {
            ResultSet res = stmt.executeQuery(sql);
            for (int i = 1; res.next(); i++) {
                if (res.getString("playerName").equals(playerName)) {
                    load = new Player(res.getString("playerName"), res.getInt("playerLevel"),
                            res.getInt("experience"), res.getInt("score"),
                            res.getInt("countHealthBanks"),

                            res.getString("className"), res.getInt("hp"),
                            res.getInt("maxHp"), res.getInt("atk"),
                            res.getInt("maxAtk"), res.getInt("def"),
                            res.getInt("maxDef"), res.getInt("luck"),
                            res.getInt("maxLuck"),

                            res.getString("weaponName"), res.getInt("weaponBonus"),
                            res.getString("chestName"), res.getInt("chestBonus"),
                            res.getString("headName"), res.getInt("headBonus"),

                            res.getInt("posX"), res.getInt("posY"),
                            res.getString("playerMap"), res.getString("enemyMap"));
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }

        return load;
    }

    public static void deleteHero(String playerName) {
        try (Connection connection = getConnect()) {
            PreparedStatement st = connection.prepareStatement("DELETE FROM heroes WHERE playerName = '" + playerName + "';");
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveHero(Player player) {
        CustomLogger.singleton.printLog("SAVE HERO >> " + player.getPlayerName(), 2);

        String sql = "UPDATE heroes SET playerLevel = ?, experience = ?, score = ?, countHealthBanks = ?, " +
                "className = ?, hp = ?, maxHp = ?, atk = ?, maxAtk = ?, def = ?, maxDef = ?, luck = ?, maxLuck = ?, " +
                "weaponName = ?, weaponBonus = ?, chestName = ?, chestBonus = ?, headName = ?, headBonus = ?, " +
                "posX = ?, posY = ?, playerMap = ?, enemyMap = ?" +
                "WHERE playerName = ?";

        try (PreparedStatement ps = getConnect().prepareStatement(sql)) {
            ps.setInt(1, player.getLevel());
            ps.setInt(2, player.getExperience());
            ps.setInt(3, player.getScore());
            ps.setInt(4, player.getCountHealthBanks());

            HeroClass heroClass = player.getHeroClass();
            ps.setString(5, heroClass.getClassName().toString());
            ps.setInt(6, heroClass.hp);
            ps.setInt(7, heroClass.getMaxHp());
            ps.setInt(8, heroClass.atk);
            ps.setInt(9, heroClass.getMaxAtk());
            ps.setInt(10, heroClass.def);
            ps.setInt(11, heroClass.getMaxDef());
            ps.setInt(12, heroClass.luck);
            ps.setInt(13, heroClass.getMaxLuck());

            ps.setString(14, player.getArtifactWeapon().getArtName());
            ps.setInt(15, player.getArtifactWeapon().getBonus());
            ps.setString(16, player.getArtifactChest().getArtName());
            ps.setInt(17, player.getArtifactChest().getBonus());
            ps.setString(18, player.getArtifactHead().getArtName());
            ps.setInt(19, player.getArtifactHead().getBonus());

            ps.setInt(20, player.getPos().x);
            ps.setInt(21, player.getPos().y);

            ps.setString(22, player.encode(player.mapPlayer));
            ps.setString(23, player.encode(player.mapEnemy));

            ps.setString(24, player.getPlayerName());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }
}
