import java.util.concurrent.ThreadLocalRandom;


public class Treasure {
    private int x;
    private int y;
    private char treasure = '*';

    public Treasure(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int randomY(){
        return ThreadLocalRandom.current().nextInt(1, 20);

    }

    public void randomX(){


    }
}
