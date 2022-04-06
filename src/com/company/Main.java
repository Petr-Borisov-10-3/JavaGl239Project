package com.company;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        JFrame window = new JFrame("Shooting game");
        logic shoot = new logic();
        window.setSize(1210,837);
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.setLocation(175,0);
        window.add(shoot);
        shoot.requestFocusInWindow();
    }
}
