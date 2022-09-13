package Tank;

import java.util.Vector;

public class Enemy_tank extends Tank_model implements Runnable{
    Vector<Bullet> vb = new Vector<Bullet>();

    Vector<Enemy_tank> enemies = new Vector<Enemy_tank>();

    public void setEnemies(Vector<Enemy_tank> enemies) {
        this.enemies = enemies;
    }

    public Enemy_tank(int x, int y) {
        super(x, y);
    }

    public boolean istouch(){
        switch (this.getDirection()){
            case 0:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy_tank et = enemies.get(i);
                    if(et != this){
                        if(et.getDirection()==0 || et.getDirection()==2){
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+40)
                            && this.getY()<=(et.getY()+60) && this.getY()>=et.getY()){
                                return true;
                            }
                            if(this.getX()+40>=et.getX() && this.getX()+40<=(et.getX()+40)
                                    && this.getY()<=(et.getY()+60) && this.getY()>=et.getY()){
                                return true;
                            }
                        }
                        if(et.getDirection()==1 || et.getDirection()==3){
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+60)
                                    && this.getY()<=(et.getY()+40) && this.getY()>=et.getY()){
                                return true;
                            }
                            if(this.getX()+40>=et.getX() && this.getX()+40<=(et.getX()+60)
                                    && this.getY()<=(et.getY()+40) && this.getY()>=et.getY()){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy_tank et = enemies.get(i);
                    if(et != this){
                        if(et.getDirection()==0 || et.getDirection()==2){
                            if(this.getX()+60>=et.getX() && this.getX()+60<=(et.getX()+40)
                                    && this.getY()<=(et.getY()+60) && this.getY()>=et.getY()){
                                return true;
                            }
                            if(this.getX()+60>=et.getX() && this.getX()+60<=(et.getX()+40)
                                    && this.getY()+40<=(et.getY()+60) && this.getY()+40>=et.getY()){
                                return true;
                            }
                        }
                        if(et.getDirection()==1 || et.getDirection()==3){
                            if(this.getX()+60>=et.getX() && this.getX()+60<=(et.getX()+60)
                                    && this.getY()<=(et.getY()+40) && this.getY()>=et.getY()){
                                return true;
                            }
                            if(this.getX()+60>=et.getX() && this.getX()+60<=(et.getX()+60)
                                    && this.getY()+40<=(et.getY()+40) && this.getY()+40>=et.getY()){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy_tank et = enemies.get(i);
                    if(et != this){
                        if(et.getDirection()==0 || et.getDirection()==2){
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+40)
                                    && this.getY()+60<=(et.getY()+60) && this.getY()+60>=et.getY()){
                                return true;
                            }
                            if(this.getX()+40>=et.getX() && this.getX()+40<=(et.getX()+40)
                                    && this.getY()+60<=(et.getY()+60) && this.getY()+60>=et.getY()){
                                return true;
                            }
                        }
                        if(et.getDirection()==1 || et.getDirection()==3){
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+60)
                                    && this.getY()+60<=(et.getY()+40) && this.getY()+60>=et.getY()){
                                return true;
                            }
                            if(this.getX()+40>=et.getX() && this.getX()+40<=(et.getX()+60)
                                    && this.getY()+60<=(et.getY()+40) && this.getY()+60>=et.getY()){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy_tank et = enemies.get(i);
                    if(et != this){
                        if(et.getDirection()==0 || et.getDirection()==2){
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+40)
                                    && this.getY()<=(et.getY()+60) && this.getY()>=et.getY()){
                                return true;
                            }
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+40)
                                    && this.getY()+40<=(et.getY()+60) && this.getY()+40>=et.getY()){
                                return true;
                            }
                        }
                        if(et.getDirection()==1 || et.getDirection()==3){
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+60)
                                    && this.getY()<=(et.getY()+40) && this.getY()>=et.getY()){
                                return true;
                            }
                            if(this.getX()>=et.getX() && this.getX()<=(et.getX()+60)
                                    && this.getY()+40<=(et.getY()+40) && this.getY()+40>=et.getY()){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while(true) {
            //发射子弹
            if(vb.size()<4 && isIslive()) {
                Bullet b = null;
                switch (getDirection()) {
                    case 0:
                        b = new Bullet(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        b = new Bullet(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        b = new Bullet(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        b = new Bullet(getX(), getY() + 20, 3);
                        break;
                }
                vb.add(b);
                new Thread(b).start();
            }

            //移动
            switch (getDirection()) {
                case 0:
                    for (int i = 0; i < 10 ; i++) {
                        if((getY()-getSpeed())>0 && !istouch()) {
                            moveup();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }

                    }
                    break;
                case 1:
                    for (int i = 0; i < 10; i++) {
                        if ((getX() + 60 + getSpeed()) < 1000 && !istouch()) {
                            moveright();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 10; i++) {
                        if((getY()+getSpeed())<750 && !istouch()) {
                            movedown();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; i < 10; i++) {
                        if ((getX() - getSpeed()) > 0 && !istouch()) {
                            moveleft();
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }
                    }
                    break;
            }

            try{
                Thread.sleep(500);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

            setDirection((int)( Math.random() * 4));
            if(isIslive() == false){
                break;
            }
        }
    }
}
