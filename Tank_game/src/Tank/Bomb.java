package Tank;

public class Bomb {
    private int x;
    private int y;
    int life = 9;
    boolean islive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void life_down() {
        if (life > 0) {
            life--;
        } else {
            islive = false;
        }
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isIslive() {
        return islive;
    }

    public void setIslive(boolean islive) {
        this.islive = islive;
    }
}
