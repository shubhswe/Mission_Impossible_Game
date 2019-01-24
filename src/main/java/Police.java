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
        // a monster wants to minimize the distance between itself and the player

        // Along which axis should the monster move in?
        // The monster will move in the direction in which the distance between monster and player is the largest.
        // Let's use the absolute value of the difference between the x-coordinates vs the y-coordinates!
        // Example of Math.abs -> https://www.tutorialspoint.com/java/lang/math_abs_int.htm

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
