package org.jbashiri.view.init;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Init;
import org.jbashiri.exceptions.CustomException;
import org.jbashiri.model.M_Init;
import org.jbashiri.utils.CustomLogger;

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
        CustomLogger.singleton.printLog("INIT GUI INIT #", 3);
        frame = Main.getFrame();
        Main.getFrame().setTitle("SWINGY 1.0");

        Container container = frame.getContentPane();
        container.setLayout(new GridLayout(3,1));
        container.add(btnCreate);
        container.add(btnLoad);
        container.add(btnSwitch);
        frame.setSize(1024,1024);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c_init.initCreate(false);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c_init.initLoad(false);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    c_init.switchUI(true);
                } catch (CustomException ex) {
                    throw new RuntimeException(ex);
                }
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
        } else {
            Main.hideFrame();
        }
    }
}
