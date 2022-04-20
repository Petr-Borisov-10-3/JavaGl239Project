package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class logic extends JPanel implements ActionListener, KeyListener {
    private int botscore = 0, playerscore = 0;
    private int playerx = 50, playery = 400, botx = 1100, boty = 500;
    private int pbulletspeedx=0,pbulletspeedy=0,bbulletspeedx=0,bbulletspeedy=0;
    private int pbulletx,pbullety,bbulletx,bbullety;
    private int pbounceoff=0,bbounceoff=0;
    private int pbulletcrossborder=0;
    private boolean showdialoguewindow=true,showangrymode=false;
    private boolean pshoot=false,bshoot=false;
    private Timer time;
    private Timer procTime;
    private long prevT;
    private int menuOpacity = 255;

    public logic() {
        addKeyListener(this);
        time = new Timer(10, this);
        //survivance timer
        procTime = new Timer(25000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerscore += 1;
            }
        });
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        //fields
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, 600, 801);
        g.setColor(Color.PINK);
        g.fillRect(601, 0, 601, 801);
        //line between fields
        g.setColor(Color.DARK_GRAY);
        g.drawLine(600, 0, 600, 801);
        //score bottom line
        g.setColor(Color.DARK_GRAY);
        g.drawLine(450, 150, 750, 150);
        //score left n right borders
        g.setColor(Color.DARK_GRAY);
        //left
        g.drawLine(450, 0, 450, 150);
        //right
        g.drawLine(750, 0, 750, 150);
        //your score writing
        g.setColor(Color.DARK_GRAY);
        Font f1 = new Font("Arial", Font.BOLD, 30);
        g.setFont(f1);
        g.drawString("YOU", 490, 30);
        //bot's score writing
        g.setFont(f1);
        g.drawString("BOT", 640, 30);
        //writings underline
        g.setColor(Color.DARK_GRAY);
        g.drawLine(450, 37, 750, 37);
        //bot and players score
        Font f = new Font("Arial", Font.BOLD, 75);
        //you
        g.setFont(f);
        g.drawString(String.valueOf(playerscore), 510, 115);
        //bot
        g.setFont(f);
        g.drawString(String.valueOf(botscore), 660, 115);
        //shotborders
        g.setColor(Color.GREEN);
        g.drawLine(500, 150, 500, 800);
        g.drawLine(700, 150, 700, 800);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 148, 1200, 3);
        Font f2 = new Font("Arial", Font.BOLD, 60);
        Font f3 = new Font("Arial", Font.BOLD,53);
        g.setFont(f3);
        g.drawString("SURVIVE 25S OR", 7, 90);
        g.setFont(f2);
        g.drawString("KILL THE BOT", 760, 90);
        //map
        g.setColor(Color.RED);
        g.fillRect(100, 150, 50, 150);
        g.fillRect(100, 400, 300, 50);
        g.fillRect(350, 250, 50, 300);
        g.fillRect(100, 550, 50, 150);
        g.fillRect(100, 650, 200, 50);

        g.fillRect(1050, 650, 50, 150);
        g.fillRect(800, 500, 300, 50);
        g.fillRect(800, 400, 50, 300);
        g.fillRect(900, 250, 200, 50);
        g.fillRect(1050, 250, 50, 150);
        //player
        g.setColor(Color.BLUE);
        g.fillOval(playerx, playery, 50, 50);
        //bot
        g.fillOval(botx, boty, 50, 50);
        //player's bullet
        g.setColor(Color.DARK_GRAY);
        if(pshoot==false) {
            pbulletx = playerx + 15;
            pbullety = playery + 15;
        }
        g.fillOval(pbulletx,pbullety,20,20);
        //bot's bullet
        if(bshoot==false) {
            bbulletx = botx + 15;
            bbullety = boty + 15;
        }
        g.fillOval(bbulletx,bbullety,20,20);
        //dialogue window
        if(showdialoguewindow==true&&showangrymode==false) {
            g.setColor(new Color(139, 0, 255, menuOpacity));
            g.fillRect(300, 400, 600, 150);
            g.setColor(new Color(255, 140, 0, menuOpacity));
            Font f4 = new Font("Arial", Font.BOLD, 53);
            g.setFont(f4);
            g.drawString("press ENTER to start", 333, 485);
        }
        if(showangrymode==true){
            g.setColor(new Color(139, 0, 255, menuOpacity));
            g.fillRect(300, 400, 600, 150);
            g.setColor(new Color(255, 140, 0, menuOpacity));
            Font f4 = new Font("Arial", Font.BOLD, 25);
            g.setFont(f4);
            g.drawString("bot is ANGRY and has 2x damage", 385, 450);
            g.drawString("you don't have any ability to kill him", 373, 490);
            g.drawString("objective: SURVIVE", 475, 530);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if(pshoot==true){
            pbulletx+=pbulletspeedx;
            pbullety+=pbulletspeedy;
            //screenborders bounceoff
            if (pbulletx>1180 || pbulletx<0){
                pbulletspeedx*=-1;
                pbounceoff+=1;
            }
            if(pbullety<150 || pbullety>780){
                pbulletspeedy*=-1;
                pbounceoff+=1;
            }
            /*
            //vertical bounceoffs
            //mapborders bounceoff botside forward
            if((pbullety>380&&pbullety<700&&pbulletx>780&&pbulletx<790)||(pbullety>230&&pbullety<300&&pbulletx>880&&pbulletx<890)||(pbullety>230&&pbullety<400&&pbulletx>1030&&pbulletx<1050)||(pbullety>630&&pbullety<=800&&pbulletx>1030&&pbulletx<1040)){
                pbulletspeedx*=-1;
                pbounceoff+=1;
            }
            //mapborders bounceoff botside backward
            if((pbullety>380&&pbullety<700&&pbulletx>840&&pbulletx<850)||(pbullety>480&&pbullety<550&&pbulletx>1090&&pbulletx<1100)||(pbullety>230&&pbullety<400&&pbulletx>1090&&pbulletx<1100)||(pbullety>630&&pbullety<=800&&pbulletx>1090&&pbulletx<1100)){
                pbulletspeedx*=-1;
                pbounceoff+=1;
            }
            //horizontal bounceoffs
            //mapborders bounceoff botside upward
            if((pbullety>230&&pbullety<240&&pbulletx>880&&pbulletx<1100)||(pbullety>380&&pbullety<390&&pbulletx>780&&pbulletx<850)||(pbullety>480&&pbullety<490&&pbulletx>830&&pbulletx<1100)||(pbullety>630&&pbullety<640&&pbulletx>1030&&pbulletx<1100)){
                pbulletspeedy*=-1;
                pbounceoff+=1;
            }
            //mapborders bounceoff botside downward
            if((pbullety>690&&pbullety<700&&pbulletx>780&&pbulletx<850)||(pbullety>540&&pbullety<550&&pbulletx>830&&pbulletx<1100)||(pbullety>390&&pbullety<400&&pbulletx>1030&&pbulletx<1100)||(pbullety>290&&pbullety<300&&pbulletx>880&&pbulletx<1075)){
                pbulletspeedy*=-1;
                pbounceoff+=1;
            }
            if(pbulletx>695&&pbulletx<705){
                pbulletcrossborder+=1;
            }
            if(pbulletcrossborder==2){
                pbounceoff=14;
                pbulletcrossborder=0;
            }
            //bounceoff count
            if(pbounceoff>=14){
                pshoot=false;
                pbounceoff=0;
            }

             */
            if(new Rectangle(pbulletx,pbullety,20,20).intersects(new Rectangle(botx,boty,50,50))){
                playerscore+=1;
                time.stop();
                procTime.restart();
                if(playerscore<4) {
                    showdialoguewindow = true;
                }
                if(playerscore==4){
                    showangrymode=true;
                }
                pshoot=false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        //player movement
        if (e.getKeyCode() == KeyEvent.VK_W) {
            playery -= 5;
            if (playery < 150) {
                playery += 5;
            }
            if ((playery > 290 && playery < 300) && ((playerx >= 50 && playerx <= 150))) {
                playery += 5;
            }
            if ((playery > 440 && playery < 450) && ((playerx >= 50 && playerx <= 350))) {
                playery += 5;
            }
            if ((playery > 540 && playery < 550) && ((playerx >= 300 && playerx <= 400))) {
                playery += 5;
            }
            if ((playery > 690 && playery < 700) && ((playerx >= 50 && playerx <= 300))) {
                playery += 5;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            playery += 5;
            if (playery > 750) {
                playery -= 5;
            }
            if ((playery > 350 && playery < 360) && ((playerx >= 50 && playerx <= 300))) {
                playery -= 5;
            }
            if ((playery > 500 && playery < 510) && ((playerx >= 50 && playerx <= 150))) {
                playery -= 5;
            }
            if ((playery > 600 && playery < 610) && ((playerx >= 150 && playerx <= 300))) {
                playery -= 5;
            }
            if ((playery > 200 && playery < 210) && ((playerx >= 300 && playerx <= 400))) {
                playery -= 5;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            playerx -= 5;
            if (playerx < 0) {
                playerx += 5;
            }
            if ((playerx < 150 && playerx > 140) && ((playery >= 150 && playery <= 300) || (playery >= 500 && playery <= 700))) {
                playerx += 5;
            }
            if ((playerx < 300 && playerx > 290) && ((playery >= 600 && playery <= 700))) {
                playerx += 5;
            }
            if ((playerx < 400 && playerx > 390) && ((playery >= 200 && playery <= 550))) {
                playerx += 5;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            playerx += 5;
            if (playerx > 450) {
                playerx -= 5;
            }
            if ((playerx > 50 && playerx < 60) && ((playery >= 150 && playery <= 300) || (playery >= 350 && playery <= 450) || (playery >= 500 && playery <= 700))) {
                playerx -= 5;
            }
            if ((playerx > 300 && playerx < 310) && ((playery >= 200 && playery <= 550))) {
                playerx -= 5;
            }
        }
        //upper-right shot
        if (e.getKeyCode() == KeyEvent.VK_E){
            pbulletspeedx=1;
            pbulletspeedy=1;
            pbulletspeedx*=5;
            pbulletspeedy*=-5;
            pshoot=true;
        }
        //middle-right shot
        if (e.getKeyCode() == KeyEvent.VK_R){
            pbulletspeedx=1;
            pbulletspeedy=1;
            pbulletspeedx*=5;
            pbulletspeedy*=0;
            pshoot=true;
        }
        //bottom-right shot
        if (e.getKeyCode() == KeyEvent.VK_F){
            pbulletspeedx=1;
            pbulletspeedy=1;
            pbulletspeedx*=5;
            pbulletspeedy*=5;
            pshoot=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            time.start();
            procTime.start();
            showdialoguewindow=false;
            showangrymode=false;
        }
    }

}
