package Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable{
    boolean open = true;
    My_tank hero = null;
    Vector<Enemy_tank> enemies = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    Vector<Node> nodes = null;
    //add picture

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    int enemies_size = 9;
    public MyPanel(int key) {
        nodes = Recorder.load();
        Recorder.setEnemy_tanks(enemies);
        hero = new My_tank(500,100);
        hero.setSpeed(8);
        switch(key) {
            case 0:
                for (int i = 0; i < enemies_size; i++) {
                Enemy_tank e_t = new Enemy_tank(100 * (i + 1), 0);

                e_t.setEnemies(enemies);
                e_t.setDirection(2);
                e_t.setSpeed(5);
                Thread thread_enemy = new Thread(e_t);
                thread_enemy.start();

                Bullet bullet = new Bullet(e_t.getX() + 20, e_t.getY() + 60, e_t.getDirection());
                e_t.vb.add(bullet);
                enemies.add(e_t);
                Thread thread_enemy_bullet = new Thread(bullet);
                thread_enemy_bullet.start();
                }
                break;
            case 1:
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    Enemy_tank e_t = new Enemy_tank(node.getX(),node.getY());

                    e_t.setEnemies(enemies);
                    e_t.setDirection(node.getDirection());
                    e_t.setSpeed(5);
                    Thread thread_enemy = new Thread(e_t);
                    thread_enemy.start();

                    Bullet bullet = new Bullet(e_t.getX() + 20, e_t.getY() + 60, e_t.getDirection());
                    e_t.vb.add(bullet);
                    enemies.add(e_t);
                    Thread thread_enemy_bullet = new Thread(bullet);
                    thread_enemy_bullet.start();
                }
                break;
        }

        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb3.png"));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);

        g.setColor(Color.white);
        g.setFont(new Font("",Font.BOLD,20));
        g.drawString("killed:"+""+(Recorder.killed).toString(),900,20);

        if(open == false){
            g.setColor(Color.white);
            g.setFont(new Font("",Font.BOLD,20));
            g.drawString("game over",450,320);
        }

        //画自己的坦克
        if(hero.isIslive()) {
            draw_tank(hero.getX(), hero.getY(), g, hero.getDirection(), 0);
        }

        //画自己的子弹
        for(int j = 0;j<hero.bullets.size();j++) {
            if (hero.bullets.get(j).isIslive() != false && hero.bullets.get(j) != null) {
                g.fill3DRect(hero.bullets.get(j).getX(), hero.bullets.get(j).getY(), 4, 4, true);
            }
        }

        //画爆炸
        if(!bombs.isEmpty()){
            try{
                Thread.sleep(20);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

            for(int i = 0;i<bombs.size();i++){
                Bomb bomb = bombs.get(i);
                if(bomb.life>6){
                    g.drawImage(image1,bomb.getX(),bomb.getY(),60,60,this);
                }else if(bomb.life>3 && bomb.life<6){
                    g.drawImage(image2,bomb.getX(),bomb.getY(),60,60,this);
                }else{
                    g.drawImage(image3,bomb.getX(),bomb.getY(),60,60,this);
                }
                bomb.life_down();
                if (bomb.life == 0){
                    bombs.remove(bomb);
                }
            }
        }

        //画敌人坦克
        for (int i = 0; i < enemies.size(); i++) {
            Enemy_tank et = enemies.get(i);
            if(et.isIslive()) {
                draw_tank(et.getX(), et.getY(), g, et.getDirection(), 1);
                for (int j = 0; j < et.vb.size(); j++) {
                    Bullet bullet = et.vb.get(j);
                    if (bullet.isIslive() == true) {
                        g.fill3DRect(bullet.getX(), bullet.getY(), 4, 4, true);
                    } else {
                        et.vb.remove(bullet);
                    }
                }
            }
        }

    }

    @Override
    public void run() { //刷新整个图
        while(true){
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

            if(hero.b != null && hero.b.isIslive() && hero.isIslive()){
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy_tank et = enemies.get(i);
                    hit_enemy_tank(hero.bullets,et);
                }
            }



            for (int j = 0; j < enemies.size(); j++) {
                Enemy_tank et = enemies.get(j);
                hit_my_tank(et.vb,hero);
            }

            if(enemies.size()<2){
                Enemy_tank et = new Enemy_tank(((int)(Math.random()*3)*100),0);
                et.setEnemies(enemies);
                et.setDirection(2);
                et.setSpeed(5);
                enemies.add(et);
                Thread thread_enemy = new Thread(et);
                thread_enemy.start();
            }


            this.repaint();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirection(0);
            hero.moveup();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirection(1);
            hero.moveright();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirection(2);
            hero.movedown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirection(3);
            hero.moveleft();
        }else if(e.getKeyCode() == KeyEvent.VK_J){
            //if(hero.b == null || !hero.b.isIslive()) {
                hero.shot();
            //}
        }
        //this.repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    /**
     *
     * @param x
     * @param y
     * @param g
     * @param direction　方向
     * @param type　敵か　自分か
     */

    public void draw_tank(int x,int y,Graphics g,int direction,int type){
        switch (type){
            case 0: //自分
                g.setColor(Color.blue);
                break;
            case 1://敵
                g.setColor(Color.red);
                break;
        }

        switch (direction){
            case 0://up
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://right
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://down
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fill3DRect(x+30,y,10,60,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://left
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("error");
        }

    }

    public void hit_my_tank(Vector<Bullet> bullets ,My_tank hero){
        for(int i = 0;i< bullets.size();i++) {
            Bullet b = bullets.get(i);
            if (hero.isIslive() && b.isIslive()) {
                switch (hero.getDirection()) {
                    case 0:
                    case 2:
                        if (b.getX() > hero.getX() && b.getX() < hero.getX() + 40 && b.getY() > hero.getY() && b.getY() < hero.getY() + 60) {
                            System.out.println("jizhonglewo");
                            b.setIslive(false);
                            hero.setIslive(false);


                            Bomb bomb1 = new Bomb(hero.getX(), hero.getY());
                            bombs.add(bomb1);


                            open=false;
                        }
                        break;
                    case 1:
                    case 3:
                        if (b.getX() > hero.getX() && b.getX() < hero.getX() + 60 && b.getY() > hero.getY() && b.getY() < hero.getY() + 40) {
                            System.out.println("jizhonglewo");
                            b.setIslive(false);
                            hero.setIslive(false);


                            Bomb bomb2 = new Bomb(hero.getX(), hero.getY());
                            bombs.add(bomb2);

                            open=false;
                        }
                        break;
                }
            }
        }
    }

    public void hit_enemy_tank(Vector<Bullet> bullets , Enemy_tank et){
        for(int i = 0;i< bullets.size();i++) {
            Bullet b = bullets.get(i);
            switch (et.getDirection()) {
                case 0:
                case 2:
                    if (b.getX() > et.getX() && b.getX() < et.getX() + 40 && b.getY() > et.getY() && b.getY() < et.getY() + 60) {
                        System.out.println("jizhongle");
                        Recorder.killed++;
                        b.setIslive(false);
                        et.setIslive(false);

                        enemies.remove(et);

                        Bomb bomb1 = new Bomb(et.getX(), et.getY());
                        bombs.add(bomb1);
                    }
                    break;
                case 1:
                case 3:
                    if (b.getX() > et.getX() && b.getX() < et.getX() + 60 && b.getY() > et.getY() && b.getY() < et.getY() + 40) {
                        System.out.println("jizhongle");
                        Recorder.killed++;
                        b.setIslive(false);
                        et.setIslive(false);

                        enemies.remove(et);

                        Bomb bomb2 = new Bomb(et.getX(), et.getY());
                        bombs.add(bomb2);
                    }
                    break;
            }
        }
    }
}
