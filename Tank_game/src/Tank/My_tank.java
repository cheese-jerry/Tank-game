package Tank;

import java.util.Vector;

public class My_tank extends Tank_model{
    Bullet b = null ; // 因为要根据当前对象的方向创建先null
    Vector<Bullet> bullets = new Vector<>();
    public My_tank(int x, int y) {
        super(x, y);
    }

    public void shot(){
        switch (getDirection()){
            case 0:
                b = new Bullet(getX()+20,getY(),0);
                break;
            case 1:
                b = new Bullet(getX()+60,getY()+20,1);
                break;
            case 2:
                b = new Bullet(getX()+20,getY()+60,2);
                break;
            case 3:
                b = new Bullet(getX(),getY()+20,3);
                break;
        }
        bullets.add(b);
        Thread thread_b = new Thread(b);
        thread_b.start();
    }
}
