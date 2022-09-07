package org.jbashiri.view.create;

import org.jbashiri.Main;
import org.jbashiri.controller.C_Create;
import org.jbashiri.exceptions.CustomError;
import org.jbashiri.model.M_Create;
import org.jbashiri.model.classes.Mage;
import org.jbashiri.model.classes.Paladin;
import org.jbashiri.model.classes.Ranger;
import org.jbashiri.model.classes.Warrior;
import org.jbashiri.utils.CustomEnums;
import org.jbashiri.utils.CustomLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class V_CreateGUI implements V_Create {

    private C_Create c_create;
    private M_Create m_create;

    private JPanel frame;
    private JLabel heroNameLabel = new JLabel("Player name:");
    private JTextField heroNameField = new JTextField(10);
    private JButton btnCreate = new JButton("Create");
    private String[] heroClasses = {"Warrior", "Paladin", "Ranger", "Mage"};
    private JLabel heroClass = new JLabel("Class:");
    private JComboBox<String> classesComboBox = new JComboBox<>(heroClasses);
    private JEditorPane infoPane = new JEditorPane();

    public V_CreateGUI(C_Create c_create, M_Create m_create) {
        this.c_create = c_create;
        this.m_create = m_create;
    }

    @Override
    public void init(CustomEnums.StateCreate state) {
        frame = (JPanel) Main.getFrame().getContentPane();
        frame.removeAll();
        frame.setLayout(new GridBagLayout());
        frame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel createHeroPanel = new JPanel();
        createHeroPanel.add(heroNameLabel);
        createHeroPanel.add(heroNameField);
        createHeroPanel.setVisible(true);
        frame.add(createHeroPanel, gbc);

        JPanel classPannel = new JPanel();
        classPannel.add(heroClass);
        classesComboBox.setSelectedIndex(0);
        classPannel.add(classesComboBox);
        classPannel.setVisible(true);
        frame.add(classPannel, gbc);

        infoPane.setEditable(false);
        infoPane.setFont(new Font("monospaced", Font.PLAIN, 14));
        infoPane.setText(new Warrior().getAllDefaultStats() + "\n" +
                            new Paladin().getAllDefaultStats() + "\n" +
                            new Ranger().getAllDefaultStats() + "\n" +
                            new Mage().getAllDefaultStats() + "\n");
        infoPane.setPreferredSize(new Dimension(250, 100));
        infoPane.setMinimumSize(new Dimension(250, 80));
        frame.add(infoPane, gbc);

        frame.add(btnCreate, gbc);
        Main.showFrame();

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = heroNameField.getText();
                String className = (String) classesComboBox.getSelectedItem();

                CustomError error = m_create.isCorrectNewPlayerName(playerName);
                if (error.type != CustomEnums.Error.clear) {
                    inputErrorName(error, heroNameField.getText());
                    return;
                }
                error = m_create.isCorrectNewClassName(className);
                if (error.type != CustomEnums.Error.clear) {
                    inputErrorName(error, (String) classesComboBox.getSelectedItem());
                    return;
                }

                CustomLogger.singleton.printLog("Name > " + playerName + " > class > " + className, 3);

                m_create.setPlayerName(playerName);
                m_create.setClassName(className);

                c_create.initGame(m_create.getPlayerName(),
                        m_create.getPlayerClass(), false);
            }
        });
    }


    @Override
    public void switchUI(CustomEnums.StateCreate state, boolean isConsole) {
        if (!isConsole) {
            if (frame == null)
                init(state);
            Main.showFrame();
        } else {
            Main.hideFrame();
        }
    }

    @Override
    public void inputError(CustomEnums.StateCreate state) {

    }

    public void inputErrorName(CustomError error, String line) {
        String err = "";
        if (error.type == CustomEnums.Error.errorWords) {
            err = line + " > incorrect name[use only words, don't specific simbols]\nPlease write other.";
        } else if (error.type == CustomEnums.Error.errorRestrictionWords) {
            err = line + " > restriction name [don't use name's: (create)(load)(switch)]\nPlease write other.";
        } else if (error.type == CustomEnums.Error.errorIsTaken) {
            err = line + " > name is TAKEN\nPlease write other.";
        } else if (error.type == CustomEnums.Error.errorIncorrectPlayerName) {
            err = line + " > " + error.error + "\nPlease write other.";
        } else if (error.type == CustomEnums.Error.errorIncorrectPlayerClass) {
            err = line + " > " + error.error + "\nPlease write other.";
        }
        JOptionPane.showMessageDialog(null, err);
    }

    @Override
    public void printDivider() {

    }

    @Override
    public void printStartState(CustomEnums.StateCreate state) {

    }
}
