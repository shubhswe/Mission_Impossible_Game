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

        Player tom = new Player(0, 2);
//        Treasure treasure1 = new Treasure();
        Obstacle obstacle = new Obstacle(25, 16);

        boolean playerIsAlive=true;

        obstacle.printBlock(3, 3, terminal,obstacle);
        while (playerIsAlive) {

            drawBoard(terminal, tom, obstacle);

        }
    }

    public static void drawBoard(Terminal terminal, Player tom, Obstacle obstacle) throws IOException {
        terminal.setCursorPosition(tom.getX(), tom.getY());         //printing tom
        terminal.putCharacter(tom.getSymbol());

//        terminal.setCursorPosition(treasure1.randomX(), treasure1.randomY());         // printing treasure
//        terminal.putCharacter(treasure1.getSymbol());
//        terminal.flush();
//
//        terminal.setCursorPosition(25, 16);         //i=0
//        terminal.putCharacter(obstacle.getSymbol());
//        terminal.setCursorPosition(26, 16);         //i=1
//        terminal.putCharacter(obstacle.getSymbol());
//        terminal.setCursorPosition(25, 17);         //i=2
//        terminal.putCharacter(obstacle.getSymbol());
//        terminal.setCursorPosition(26, 17);         //i=3
//        terminal.putCharacter(obstacle.getSymbol());
//

        terminal.flush();

         //   terminal.setCursorPosition();         //printing obstacle
//            terminal.putCharacter(obstacle.getSymbol());
//           obstacle.printBlock(25, 16);

        }
    }

