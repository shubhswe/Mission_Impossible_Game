import java.util.concurrent.ThreadLocalRandom;


public class Treasure {
    private int x;
    private int y;
    private char symbol = '♦';
    private boolean isCollected=false;
    private int blinker =0;

    public boolean isNotTaken() {
        return isCollected;
    }

    public Treasure() {
        this.x = randomX();
        this.y = randomY();
    }
    public Treasure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
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

    public int getBlinker() {
        return blinker;
    }

    public void setTreasureColorSwitch() {
        this.blinker++;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public int randomY(){
        return ThreadLocalRandom.current().nextInt(2, 22);

    }

    public int randomX(){
        return ThreadLocalRandom.current().nextInt(2, 78);

    }


}
