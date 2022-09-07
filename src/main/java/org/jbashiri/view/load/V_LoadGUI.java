package org.jbashiri.view.load;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Load;
import org.jbashiri.model.M_Load;
import org.jbashiri.utils.CustomLogger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class V_LoadGUI implements V_Load {
    private C_Load c_load;
    private M_Load m_load;
    private JPanel frame;

    private JEditorPane infoPanel = new JEditorPane();
    private JButton btnLoad = new JButton("Load");
    private JButton btnCreate = new JButton("Create");

    private String lastSelectHero;

    public V_LoadGUI(C_Load c_load, M_Load m_load) {
        this.c_load = c_load;
        this.m_load = m_load;
    }

    @Override
    public void init(ArrayList<String> list) {
        CustomLogger.singleton.printLog("LOAD GUI INIT #", 3);
        frame = (JPanel) Main.getFrame().getContentPane();
        frame.removeAll();
        frame.setLayout(new GridBagLayout());
        frame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        final JList l = new JList(list.toArray());
        l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        l.setLayoutOrientation(JList.VERTICAL);
        l.setVisibleRowCount(-1);
        JScrollPane lScroll = new JScrollPane(l);
        lScroll.setPreferredSize(new Dimension(200, 200));
        lScroll.setMinimumSize(new Dimension(150, 150));
        frame.add(lScroll);

        infoPanel.setEditable(false);
        infoPanel.setText("Hero information, click hero now!");
        if (list.toArray().length == 0)
            infoPanel.setText("Old heroes clear");
        JScrollPane infoScroll = new JScrollPane(infoPanel);
        infoScroll.setPreferredSize(new Dimension(200, 200));
        infoScroll.setMinimumSize(new Dimension(150, 150));
        frame.add(infoScroll, gbc);

        frame.add(btnLoad, gbc);
        frame.add(btnCreate, gbc);
        btnLoad.setEnabled(false);

        frame.setVisible(true);

        Main.getFrame().setContentPane(frame);
        Main.getFrame().revalidate();
        Main.showFrame();


        l.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (l.getSelectedIndex() != -1) {
                        btnLoad.setEnabled(true);
                        lastSelectHero = l.getSelectedValue().toString().split(" ")[0];
                        infoPanel.setText(m_load.getPlayerData(lastSelectHero));
                    } else
                        btnLoad.setEnabled(false);
                }
            }
        });
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_load.initGame(lastSelectHero, false);
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_load.initCreate(false);
            }
        });

    }

    @Override
    public void inputError() {

    }

    @Override
    public void printDivider() {

    }

    @Override
    public void switchUI(ArrayList<String> list, boolean isConsole) {
        if (!isConsole) {
            if (frame == null)
                init(list);
            Main.showFrame();
        } else {
            Main.hideFrame();
        }
    }
}
