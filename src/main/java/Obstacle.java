import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.concurrent.ThreadLocalRandom;

public class Obstacle {

    private int x;
    private int y;
    private char symbol = '\u2588';


    public char getSymbol() {
        return symbol;
    }

    public Obstacle(int x, int y) {
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

    public void printBlock(int sizeX, int sizeY, Terminal terminal, Obstacle obstacle) throws IOException {


        int posX = ThreadLocalRandom.current().nextInt(5, 75);
        int posY = ThreadLocalRandom.current().nextInt(5, 19);

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {

                terminal.setCursorPosition(posX, posY);
                terminal.putCharacter(obstacle.getSymbol());
                posX++;
            }
            posX = posX - sizeX;
            posY++;


        }
    }
}