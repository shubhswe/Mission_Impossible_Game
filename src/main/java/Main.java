import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


/*
       while(true){

       drawBoard();            - Prints Player, Treasure and possible Guard and Obastacle to the terminal

       changeDirection();      - Takes keyboard input which defines direction (Doesn't move here)

       movePlayer();           - Moves Player one step in the set direction

       checkMovement();        - Checks if Player has hit something, Treasure/Guard so on.

       }
        */

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        Player tom = new Player(0, 2);
        Treasure treasure1 = new Treasure();

        KeyStroke keyStroke = null;
        int gameSpeed = 100;                                    //Sets the speed of the game, lower number means faster game (milliseconds)

        boolean playerIsAlive=true;

        while (playerIsAlive) {

            movePlayer(keyStroke, tom, terminal);               //Sets direction based on arrow keys and moves player one step in set direction. (default direction is right.)

            drawBoard(terminal, tom, treasure1);

            Thread.sleep(gameSpeed);
        }
    }

    public static void drawBoard(Terminal terminal, Player tom, Treasure treasure1) throws IOException {
        terminal.setCursorPosition(tom.getX(), tom.getY());         //printing tom
        terminal.putCharacter(tom.getSymbol());
        terminal.setCursorPosition(tom.getOldX(), tom.getOldY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(treasure1.getX(), treasure1.getY());         // printing treasure
        terminal.putCharacter(treasure1.getSymbol());
        terminal.flush();
    }


    public static void movePlayer(KeyStroke keyStroke, Player tom, Terminal terminal) throws IOException {

        keyStroke = terminal.pollInput();

        if(keyStroke != null) {

            switch (keyStroke.getKeyType()) {
                case ArrowUp:
                    tom.setDirection(1);
                    break;
                case ArrowLeft:
                    tom.setDirection(2);
                    break;
                case ArrowDown:
                    tom.setDirection(3);
                    break;
                case ArrowRight:
                    tom.setDirection(4);
                    break;
            }
        }

        tom.move();

    }

}
