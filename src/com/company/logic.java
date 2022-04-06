package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class logic extends JPanel implements ActionListener, KeyListener {
    private int botscore=0,playerscore=0;
    private int playerx=50,playery=400,botx=1100,boty=500;
    private Timer time;
    public logic(){
        addKeyListener(this);
        time = new Timer(10,this);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g){
        //fields
        g.setColor(Color.ORANGE);
        g.fillRect(0,0,600,801);
        g.setColor(Color.PINK);
        g.fillRect(601,0,601,801);
        //line between fields
        g.setColor(Color.DARK_GRAY);
        g.drawLine(600,0,600,801);
        //score bottom line
        g.setColor(Color.DARK_GRAY);
        g.drawLine(450,150,750,150);
        //score left n right borders
        g.setColor(Color.DARK_GRAY);
        //left
        g.drawLine(450,0,450,150);
        //right
        g.drawLine(750,0,750,150);
        //your score writing
        g.setColor(Color.DARK_GRAY);
        Font f1 = new Font("Arial",Font.BOLD,30);
        g.setFont(f1);
        g.drawString("YOU",490,30);
        //bot's score writing
        g.setFont(f1);
        g.drawString("BOT",640,30);
        //writings underline
        g.setColor(Color.DARK_GRAY);
        g.drawLine(450,37,750,37);
        //bot and players score
        Font f = new Font("Arial",Font.BOLD,75);
        //you
        g.setFont(f);
        g.drawString(String.valueOf(playerscore),510,115);
        //bot
        g.setFont(f);
        g.drawString(String.valueOf(botscore),660,115);
        //shotborders
        g.setColor(Color.GREEN);
        g.drawLine(500,150,500,800);
        g.drawLine(700,150,700,800);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,148,1200,3);
        Font f2 = new Font("Arial",Font.BOLD,60);
        g.setFont(f2);
        g.drawString("SURVIVE AND",22,90);
        g.drawString("KILL THE BOT",760,90);
        //map
        g.setColor(Color.RED);
        g.fillRect(100,150,50,150);
        g.fillRect(100,400,300,50);
        g.fillRect(350,250,50,300);
        g.fillRect(100,550,50,150);
        g.fillRect(100,650,200,50);

        g.fillRect(1050,650,50,150);
        g.fillRect(800,500,300,50);
        g.fillRect(800,400,50,300);
        g.fillRect(900,250,200,50);
        g.fillRect(1050,250,50,150);
        //player
        g.setColor(Color.BLUE);
        g.fillOval(playerx,playery,50,50);
        //bot
        g.fillOval(botx,boty,50,50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode()==KeyEvent.VK_W){
            playery-=5;
            if(playery<150){
                playery+=5;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            playery+=5;
            if(playery>750){
                playery-=5;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            playerx-=5;
            if(playerx<0){
                playerx+=5;
            }
            if((playerx<150&&playerx>140) && ((playery>=150 && playery<=300) || (playery>=500 && playery<=700))){
                playerx+=5;
            }
            if((playerx<300&&playerx>290) && ((playery>=600 && playery<=700))){
                playerx+=5;
            }
            if((playerx<400&&playerx>390) && ((playery>=200 && playery<=550))){
                playerx+=5;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            playerx+=5;
            if(playerx>450){
                playerx-=5;
            }
            if((playerx>50&&playerx<60) && ((playery>=150 && playery<=300) || (playery>=350 && playery<=450) || (playery>=500 && playery<=700))){
                playerx-=5;
            }
            if((playerx>300&&playerx<310) && ((playery>=200 && playery<=550))){
                playerx-=5;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            time.start();


        }

    }
}
