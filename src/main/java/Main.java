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
    public static void main(String[] args) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        Player tom = new Player(0,2);
        drawBoard(terminal, tom);





    }

    public static void drawBoard(Terminal terminal, Player tom) throws IOException {
        terminal.setCursorPosition(tom.getX(), tom.getY());
        terminal.putCharacter(tom.getSymbol());
        terminal.flush();
    }

}