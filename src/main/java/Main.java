import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
       while(true){

       drawBoard();            - Prints Player, Treasure and possible Guard and Obstacle to the terminal

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
        List<Obstacle> listOfObstacles = new ArrayList<>(); //list of obstacles
        Obstacle obstacle = new Obstacle();
        Obstacle topWall = new Obstacle();
        Obstacle leftWall = new Obstacle();
        Obstacle bottomWall = new Obstacle();
        Obstacle rightWall = new Obstacle();
        Collections.addAll(listOfObstacles,obstacle, topWall, leftWall, bottomWall, rightWall);


        KeyStroke keyStroke = null;
        int gameSpeed = 100;                                    //Sets the speed of the game, lower number means faster game (milliseconds)

        boolean playerIsAlive=true;



        while (playerIsAlive) {

            drawBoard(terminal, tom, obstacle, topWall, leftWall, bottomWall, rightWall, treasure1);

            movePlayer(keyStroke, tom, terminal);               //Sets direction based on arrow keys and moves player one step in set direction. (default direction is right.)

            checkMove(terminal, tom, treasure1, listOfObstacles);

            Thread.sleep(gameSpeed);

        }
    }

    public static void drawBoard(Terminal terminal, Player tom, Obstacle obstacle, Obstacle topWall, Obstacle leftWall, Obstacle bottomWall, Obstacle rightWall, Treasure treasure1) throws IOException {

        terminal.setCursorPosition(treasure1.getX(), treasure1.getY());         // printing treasure
        terminal.putCharacter(treasure1.getSymbol());

        terminal.setCursorPosition(tom.getX(), tom.getY());                      //printing tom
        terminal.putCharacter(tom.getSymbol());
        terminal.setCursorPosition(tom.getOldX(), tom.getOldY());
        terminal.putCharacter(' ');

        obstacle.printBlock(3, 3, terminal);            //Printing obstacles
        topWall.printBlock(80,1,0,0,terminal);
        leftWall.printBlock(2,20,0, 4,terminal);
        bottomWall.printBlock(80,1,0,23,terminal);
        rightWall.printBlock(2,20,78,0,terminal);
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

        terminal.flush();

    }

    public static void checkMove(Terminal terminal, Player tom, Treasure treasure1, List <Obstacle> listOfObstacles) throws IOException {

        for(Obstacle o: listOfObstacles){
            for(Position p: o.getPositions()){
                if( tom.getX() == p.getX() && tom.getY() == p.getY()){
                    tom.reverseDirection();
                    tom.move();
                    terminal.flush();
                }
            }
        }

        if (tom.getX()==treasure1.getX()&&tom.getY()==treasure1.getY()){

            if(treasure1.isNotTaken()) {
                terminal.setCursorPosition(tom.getX(), tom.getY());
                treasure1.setSymbol(' ');
                terminal.bell();
                terminal.flush();
                treasure1.setNotTaken(false);
            }
        }

        terminal.flush();

    }

}
