package org.jbashiri.view.game;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Game;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.Enemy;
import org.jbashiri.model.M_Game;
import org.jbashiri.model.Player;
import org.jbashiri.model.artifats.Artifact;
import org.jbashiri.utils.CustomEnums;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class V_GameGUI implements V_Game {
    private C_Game c_game;
    private M_Game m_game;

    private JPanel frame;
    private String[] directions = {"North", "East", "South", "West"};
    private JComboBox<String> directionsComboBox = new JComboBox<>(directions);
    private JButton btnMove = new JButton("Move");
    private JButton btnAttack = new JButton("Attack");
    private JButton btnSwitch = new JButton("Switch");

    private JEditorPane infoPanel = new JEditorPane();
    private JEditorPane mapPanel = new JEditorPane();

    public V_GameGUI(C_Game c_game, M_Game m_game) {
        this.c_game = c_game;
        this.m_game = m_game;
    }
    private void init() {
        frame = (JPanel) Main.getFrame().getContentPane();
        frame.removeAll();
        frame.setLayout(new GridBagLayout());
        frame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        infoPanel.setEditable(false);
        infoPanel.setText("Select hero to see information");
        infoPanel.setPreferredSize(new Dimension(220, 190));
        infoPanel.setMinimumSize(new Dimension(200, 200));
        frame.add(infoPanel, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);

        mapPanel.setEditable(false);
        mapPanel.setText("Map");
        JScrollPane mapScroll = new JScrollPane(mapPanel);
        mapScroll.setPreferredSize(new Dimension(300, 300));
        mapScroll.setMinimumSize(new Dimension(200, 200));

        directionsComboBox.setSelectedIndex(0);
        frame.add(directionsComboBox, gbc);
        frame.add(btnMove, gbc);
        frame.add(btnSwitch, gbc);

        frame.setVisible(true);
        Main.getFrame().setContentPane(frame);
        Main.getFrame().revalidate();
        Main.showFrame();

        btnMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c_game.btnPress((String) directionsComboBox.getSelectedItem());
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c_game.btnPress((String) directionsComboBox.getSelectedItem());
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c_game.switchUI(true);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void switchUI(CustomEnums.StateCreate state, boolean isConsole) {
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

    }

    @Override
    public void printWin(int score) {

    }

    @Override
    public void printDefeat(int score) {

    }

    @Override
    public void printDivider() {

    }

    @Override
    public void printFight(Player player, Enemy enemy) {

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
    public void printArtifact(Artifact nowArtifact, Artifact artifact) {

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
    public void switchUI(CustomEnums.StateGame state, boolean isConsole) {

    }

    @Override
    public void Loop() throws CustomException {

    }
}
