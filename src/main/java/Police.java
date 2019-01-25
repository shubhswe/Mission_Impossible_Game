import java.util.concurrent.ThreadLocalRandom;

public class Police {
    private int x;
    private int y;
    private char symbol;
    private int oldX;
    private int oldY;
    Integer count = 0;

    public Police(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.oldX = x;
        this.oldY = y;
    }

    public int getX() {
        return x;
    }

    public void setX() {
        x = ThreadLocalRandom.current().nextInt(5, 75);
    }

    public void setY() {
        y = ThreadLocalRandom.current().nextInt(5, 19);
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void moveTowards(Player player) {

        oldX = x;
        oldY = y;

        int diffX = this.x - player.getX();
        int absDiffX = Math.abs(diffX);
        int diffY = this.y - player.getY();
        int absDiffY = Math.abs(diffY);

        if (absDiffX > absDiffY) {
            // Move horizontal! <--->
            if (diffX < 0) {
                this.x += 1;
            } else {
                this.x -= 1;
            }
        } else if (absDiffX < absDiffY) {
            // Move vertical! v / ^
            if (diffY < 0) {
                this.y += 1;
            } else {
                this.y -= 1;
            }
        } else {
            // Move diagonal! / or \
            if (diffX < 0) {
                this.x += 1;
            } else {
                this.x -= 1;
            }
            if (diffY < 0) {
                this.y += 1;
            } else {
                this.y -= 1;
            }
        }
    }

}
