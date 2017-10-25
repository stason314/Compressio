package com.AI;

import javax.swing.*;

/**
 * Created by Stanislav on 25.10.2017.
 */
public class Starter {

    public static void main(String[] args) {

        Gui gui = new Gui();
        JFrame jFrame = new JFrame("Expert System");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(gui.panel);
        jFrame.pack();
        jFrame.setSize(640,320);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
