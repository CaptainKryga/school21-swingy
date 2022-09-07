package org.jbashiri.view.game;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Game;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.Enemy;
import org.jbashiri.model.M_Game;
import org.jbashiri.model.Player;
import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.jbashiri.utils.CustomStrings.getLengthNumber;
import static org.jbashiri.utils.CustomStrings.getSpaces;

public class V_GameGUI implements V_Game {
    private C_Game c_game;
    private M_Game m_game;

    private JPanel frame;
    private JButton btnMoveNorth = new JButton("NORTH");
    private JButton btnMoveSouth = new JButton("SOUTH");
    private JButton btnMoveWest = new JButton("WEST");
    private JButton btnMoveEast = new JButton("EAST");

    private JButton btnHealth = new JButton("HEALTH");
    private JButton btnAttack = new JButton("ATK");

    private JButton btnSet = new JButton("SET");
    private JButton btnIgnore = new JButton("IGNORE");

    private JButton btnFight = new JButton("FIGHT");
    private JButton btnLeave = new JButton("LEAVE");

    private JButton btnSwitch = new JButton("SWITCH");

    private JEditorPane infoPanel = new JEditorPane();
    private JEditorPane mapPanel = new JEditorPane();

    public V_GameGUI(C_Game c_game, M_Game m_game) {
        this.c_game = c_game;
        this.m_game = m_game;
    }
    private void init() {
        CustomLogger.singleton.printLog("V_GameGUI: INIT #", 3);
        frame = (JPanel) Main.getFrame().getContentPane();
        frame.removeAll();

        infoPanel.setEditable(false);
        mapPanel.setEditable(false);


        mapPanel.setBounds(200, 250, 300, 300);
        infoPanel.setBounds(505, 250,300, 300);

        btnMoveNorth.setBounds(300, 555, 100, 50);
        btnMoveSouth.setBounds(300, 610, 100, 50);
        btnMoveWest.setBounds(195, 582, 100, 50);
        btnMoveEast.setBounds(405, 582, 100, 50);

        btnLeave.setBounds(200, 195, 100, 50);
        btnFight.setBounds(300, 195, 100, 50);
        btnAttack.setBounds(400, 195, 100, 50);

        btnSwitch.setBounds(505, 195, 300, 50);

        btnHealth.setBounds(505, 555, 100, 50);
        btnSet.setBounds(605, 555, 100, 50);
        btnIgnore.setBounds(705, 555, 100, 50);


        frame.add(infoPanel);
        frame.add(mapPanel);

        frame.add(btnMoveNorth);
        frame.add(btnMoveSouth);
        frame.add(btnMoveWest);
        frame.add(btnMoveEast);

        frame.add(btnLeave);
        frame.add(btnFight);
        frame.add(btnAttack);

        frame.add(btnSwitch);

        frame.add(btnHealth);
        frame.add(btnSet);
        frame.add(btnIgnore);

        frame.setSize(1000, 1000) ;
        frame.setLayout(null);
        frame.setVisible(true);
        btnMoveNorth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.MOVE)
                        c_game.btnPress(CustomEnums.ButtonGame.NORTH);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnMoveSouth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.MOVE)
                        c_game.btnPress(CustomEnums.ButtonGame.SOUTH);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnMoveWest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.MOVE)
                        c_game.btnPress(CustomEnums.ButtonGame.WEST);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnMoveEast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.MOVE)
                        c_game.btnPress(CustomEnums.ButtonGame.EAST);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnLeave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.PREFIGHT)
                        c_game.btnPress(CustomEnums.ButtonGame.LEAVE);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnFight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.PREFIGHT)
                        c_game.btnPress(CustomEnums.ButtonGame.FIGHT);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.FIGHT)
                        c_game.btnPress(CustomEnums.ButtonGame.ATK);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() != CustomEnums.StateGame.PREFIGHT &&
                        m_game.getState() != CustomEnums.StateGame.FIGHT &&
                        m_game.getState() != CustomEnums.StateGame.WIN &&
                        m_game.getState() != CustomEnums.StateGame.DEFEAT)
                        c_game.switchUI(true);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnHealth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.MOVE)
                        c_game.btnPress(CustomEnums.ButtonGame.HEALTH);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.ARTIFACT)
                        c_game.btnPress(CustomEnums.ButtonGame.SET);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnIgnore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (m_game.getState() == CustomEnums.StateGame.ARTIFACT)
                        c_game.btnPress(CustomEnums.ButtonGame.IGNORE);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        printPlayerInfo(m_game.getPlayer());
        printMapPlayer(m_game.getMapPlayer());
    }

    @Override
    public void switchUI(CustomEnums.StateGame state, boolean isConsole) {
        if (!isConsole) {
            if (frame == null)
                init();
            Main.showFrame();
        } else {
            Main.hideFrame();
        }
    }

    @Override
    public void printPlayerInfo(Player player) {
        String result = "--- I.N.F.O.R.M.A.T.I.O.N. ---\n" +
        "NAME: " + player.getPlayerName() + "\n" +
        "SCORE: " + player.getScore() + "\n" +
        "CLASS: " + player.getHeroClass().getClassName() + "\n" +
        "LVL: " + player.getLevel() + "\n" +
        "EXP: " + player.getExperience() + "/" + player.calculateExperience(player.getLevel()) + "\n" +
        "HP: " + player.getHeroClass().hp + "/" + player.getHeroClass().getMaxHp() + "\n" +
        "ATK: " + player.getHeroClass().atk + "\n" +
        "DEF: " + player.getHeroClass().def + "\n" +
        "LCK: " + player.getHeroClass().luck + "\n" +
        "WEAPON: " + (player.getArtifactWeapon().getArtName().equals("-1") ? "no weapon" :
                        player.getArtifactWeapon().getNameAndStats()) + "\n" +
        "CHEST: " + (player.getArtifactChest().getArtName().equals("-1") ? "no chest" :
                        player.getArtifactChest().getNameAndStats()) + "\n" +
        "HEAD: " + (player.getArtifactHead().getArtName().equals("-1") ? "no head" :
                        player.getArtifactHead().getNameAndStats()) + "\n" +
        "Health banks: " + player.getCountHealthBanks();

        infoPanel.setText(result);
    }

    @Override
    public void printDirections() {

    }

    @Override
    public void inputError() {

    }

    @Override
    public void printMapEnemy(int[][] map) {

    }

    @Override
    public void printMapPlayer(char[][] map) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                result.append(map[x][y]);
            }
            result.append('\n');
        }
        mapPanel.setText(result.toString());
    }

    @Override
    public void printWin(int score) {
        mapPanel.setText("You made it to the edge of the map.\n" +
                "CONGRATULATION, it's a WIN!!!\n" +
                "YOU SCORE: " + score);
    }

    @Override
    public void printDefeat(int score) {
        mapPanel.setText("YOU ARE DEAD.\n" +
                "SCORE: " + score + ".\n" +
                "Your HERO has been DELETED.");
    }

    @Override
    public void printDivider() {

    }

    @Override
    public void printFight(Player player, Enemy enemy) {
        mapPanel.setText("You stumbled upon the enemy!!!\n" +
        "####   HERO      ENEMY\n" +
        "HP     " + player.getHeroClass().hp +
                getSpaces(10 - getLengthNumber(player.getHeroClass().hp)) + "" + enemy.hp + "\n" +
        "ATK    " + player.getHeroClass().atk +
                getSpaces(10 - getLengthNumber(player.getHeroClass().atk)) + "" + enemy.atk + "\n" +
        "DEF    " + player.getHeroClass().def +
                getSpaces(10 - getLengthNumber(player.getHeroClass().def)) + "" + enemy.def + "\n" +
        "write FIGHT => start battle" + "\n" +
        "write LEAVE => chance 50% on leave battle");
    }

    @Override
    public void printStartFight() {

    }

    @Override
    public void printAttack() {

    }

    @Override
    public void printFightFirstAttack(boolean isWinRollPlayer, int rollPlayer, int rollEnemy, int atk) {

    }

    @Override
    public void printFightSecondAttack(boolean isWinRollPlayer, int chance, int atk) {

    }

    @Override
    public void printChanceLeave(boolean isLeave, int rnd) {

    }

    @Override
    public void printUseHealthBank(int rnd, int countHealthBanks) {

    }

    @Override
    public void printSwitch(boolean isConsole) {

    }

    @Override
    public void printArtifact(Artifact old, Artifact _new) {
        mapPanel.setText("Drop new Artifact" + "\n" +
        "####      OLD         NEW" + "\n" +
        "name      " + (old.getArtName().equals("-1") ? "-" + getSpaces(11) :
                old.getArtName() + getSpaces(12 - old.getArtName().length())) + "" + _new.getArtName() + "\n" +
        "bonus HP  " + GetArtifactBonus(old, CustomEnums.TypeArt.Head) +
                "" + GetArtifactBonus(_new, CustomEnums.TypeArt.Head) + "\n" +
        "bonus ATK " + GetArtifactBonus(old, CustomEnums.TypeArt.Weapon) +
                "" + GetArtifactBonus(_new, CustomEnums.TypeArt.Weapon) + "\n" +
        "bonus DEF " + GetArtifactBonus(old, CustomEnums.TypeArt.Chest) +
                "" + GetArtifactBonus(_new, CustomEnums.TypeArt.Chest));

    }

    private String GetArtifactBonus(Artifact art, CustomEnums.TypeArt type) {
        if (art.getType() != type)
            return "-" + getSpaces(11);
        return art.getBonus() + getSpaces(12 - getLengthNumber(art.getBonus()));
    }

    @Override
    public void printSetOrDestroyArtifact() {

    }

    @Override
    public void playerKillEnemy(int exp) {

    }

    @Override
    public void printDropHealthBanks(int banks) {

    }

    @Override
    public void printLevelUp(Player player) {

    }

    @Override
    public void Loop() {

    }
}
