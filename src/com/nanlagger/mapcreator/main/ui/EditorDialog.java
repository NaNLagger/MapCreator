package com.nanlagger.mapcreator.main.ui;

import com.nanlagger.mapcreator.main.controllers.Controller;
import com.nanlagger.mapcreator.main.entities.Field;
import com.nanlagger.mapcreator.main.entities.FieldEntities;
import com.nanlagger.mapcreator.main.entities.Position;

import javax.swing.*;
import java.awt.event.*;

public class EditorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private Position fieldPosition;

    public EditorDialog(Position position) {
        fieldPosition = position;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        comboBox1.addItem("LAND");
        comboBox1.addItem("OCEAN");

        comboBox2.addItem("LAND_GRASS");
        comboBox2.addItem("LAND_HILL");
        comboBox2.addItem("LAND_MOUNT");
        comboBox2.addItem("OCEAN_OCEAN");
        comboBox2.addItem("OCEAN_SEA");
        comboBox2.addItem("OCEAN_REEFS");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Controller.updateField(fieldPosition, comboBox1.getSelectedItem().toString(), comboBox2.getSelectedItem().toString());
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
