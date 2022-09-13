package Tank;

public class Tank_model {
    private int x;
    private int y;
    private int direction;

    private int speed;
    private boolean islive = true;

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }

    public void moveup(){
        y-=speed;
    }

    public void moveright(){
        x+=speed;
    }

    public void movedown(){
        y+=speed;
    }

    public void moveleft(){
        x-=speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Tank_model(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
