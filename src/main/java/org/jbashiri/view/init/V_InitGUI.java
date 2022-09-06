package org.jbashiri.view.init;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Init;
import org.jbashiri.model.M_Init;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class V_InitGUI implements V_Init {
    private JFrame frame;
    private JButton btnCreate = new JButton("Create");
    private JButton btnLoad = new JButton("Load");
    private JButton btnSwitch = new JButton("Switch");
    private C_Init c_init;

    public V_InitGUI(C_Init c_init, M_Init m_init) {
        this.c_init = c_init;
    }

    public void init() {
        frame = Main.getFrame();
        Main.getFrame().setTitle("SWINGY 1.0");

        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3,1));
        container.add(btnCreate);
        container.add(btnLoad);
        container.add(btnSwitch);
        frame.setSize(512,512);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_init.initCreate(false);
            }
        });
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_init.initLoad(false);
            }
        });
        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_init.switchUI(true);
            }
        });    }

    @Override
    public void inputError() {

    }

    @Override
    public void printDivider() {

    }

    @Override
    public void switchUI(boolean isConsole) {
        if (!isConsole) {
            if (frame == null)
                init();
            Main.showFrame();
            frame.setVisible(true);
        } else {
            Main.hideFrame();
            frame.setVisible(false);
        }
    }
}
