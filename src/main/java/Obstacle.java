import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Obstacle {

    private int x;
    private int y;

    List<Position> positions = new ArrayList<>();

    public Obstacle() {
        x = ThreadLocalRandom.current().nextInt(5, 75);
        y = ThreadLocalRandom.current().nextInt(5, 19);
    }

    public List<Position> getPositions() {
        return positions;
    }

    private char symbol = '\u2588';


    public char getSymbol() {
        return symbol;
    }


    public void printBlock(int sizeX, int sizeY, Terminal terminal) throws IOException {
        int posX = x;
        int posY = y;

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {

                terminal.setCursorPosition(posX, posY);
                positions.add(new Position(posX, posY));
                terminal.putCharacter(symbol);
                posX++;
            }
            posX = posX - sizeX;
            posY++;
        }
    }

    public void printBlock(int sizeX, int sizeY, int posX, int posY, Terminal terminal) throws IOException {

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                terminal.setCursorPosition(posX, posY);
                positions.add(new Position(posX, posY));
                terminal.putCharacter(symbol);
                posX++;
            }
            posX = posX - sizeX;
            posY++;
        }
    }
}