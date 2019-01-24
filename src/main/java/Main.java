import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import jaco.mp3.player.MP3Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        new MP3Player(new File("Mission-Impossible.mp3")).play();

        Player tom = new Player(10, 2);
        Treasure treasure1 = new Treasure();
        List<Obstacle> listOfObstacles = new ArrayList<>(); //list of obstacles
        Obstacle obstacle = new Obstacle();
        Obstacle topWall = new Obstacle();
        Obstacle leftWall = new Obstacle();
        Obstacle bottomWall = new Obstacle();
        Obstacle rightWall = new Obstacle();
        Collections.addAll(listOfObstacles,obstacle, topWall, leftWall, bottomWall, rightWall);

        List<Police> listOfPolice = new ArrayList<>();
        listOfPolice.add(new Police(0,2,'P'));
        listOfPolice.add(new Police(78,2,'P'));

        KeyStroke keyStroke = null;
        int gameSpeed = 80;         //Sets the speed of the game, lower number means faster game (milliseconds)
        int treasuresToCollect = 1;


        //welcomeScreen(terminal);
        levelCountdown(terminal);

        while (tom.isAlive()) {

            drawBoard(terminal, tom, obstacle, topWall, leftWall, bottomWall, rightWall, treasure1, listOfPolice);

            movePlayer(keyStroke, tom, terminal);               //Sets direction based on arrow keys and moves player one step in set direction. (default direction is right.)

            movePolice(listOfPolice, tom);

            checkMove(terminal, tom, treasure1, listOfObstacles, listOfPolice);

            Thread.sleep(gameSpeed);

        }

        terminal.clearScreen();
        terminal.setForegroundColor(TextColor.ANSI.RED);
        printTextDelay(terminal,"GAME OVER", 35,10,200);
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
    }

    public static void welcomeScreen(Terminal terminal) throws IOException, InterruptedException {
        boolean hasStartedGame = false;
        KeyStroke keyStroke = null;

        while(!hasStartedGame){

            terminal.setForegroundColor(TextColor.ANSI.GREEN);
            printTextDelay(terminal,"MISSION IMPOSSIBLE: THE GAME", 25, 3,200);
            printTextDelay(terminal,"-----------------------------",25,2,30);
            printTextDelay(terminal,"-----------------------------",25,4,30);

            printTextDelay(terminal,"Some super evil villain has", 25,7,60 );
            printTextDelay(terminal,"stolen valuable items and put", 25,8,60 );
            printTextDelay(terminal,"them in a museum.", 25,9,60 );
            Thread.sleep(200);
            printTextDelay(terminal,"We need to get them back.", 25,11,60 );
            printTextDelay(terminal,"Collect the items", 25,13,60 );
            printTextDelay(terminal,"and get out of there.", 25,14,60 );
            printTextDelay(terminal,"They WILL try to get you...", 25,16,60 );


            printTextDelay(terminal,"Start mission:", 25,18,60 );

            while(keyStroke == null) {
                printTextDelay(terminal, "Y", 40, 18, 60);
                Thread.sleep(500);
                printTextDelay(terminal, " ", 40, 18, 60);
                Thread.sleep(500);
                keyStroke = terminal.pollInput();
            }
            hasStartedGame = true;
            terminal.clearScreen();
        }
    }

    public static void levelCountdown(Terminal terminal) throws IOException, InterruptedException {

        printTextDelay(terminal,"Level 1, starting in:", 30,10,60);

        Thread.sleep(600);
        printTextDelay(terminal, "3", 40, 12, 0);
        Thread.sleep(600);
        printTextDelay(terminal, " ", 40, 12, 0);
        Thread.sleep(600);
        printTextDelay(terminal, "2", 40, 12, 0);
        Thread.sleep(600);
        printTextDelay(terminal, " ", 40, 12, 0);
        Thread.sleep(600);
        printTextDelay(terminal, "1", 40, 12, 0);
        Thread.sleep(600);
        printTextDelay(terminal, " ", 40, 12, 0);
        Thread.sleep(1000);

        terminal.clearScreen();
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        terminal.flush();

    }

    public static void drawBoard(Terminal terminal, Player tom, Obstacle obstacle, Obstacle topWall, Obstacle leftWall, Obstacle bottomWall, Obstacle rightWall, Treasure treasure1,List<Police> listOfPolice) throws IOException, InterruptedException {

        if(!treasure1.isCollected()) {
            printTextDelay(terminal, "Collect item(s)", 32, 1, 0);
        }else{
            printTextDelay(terminal, "Get out of there!", 32, 1, 0);
        }

        terminal.setCursorPosition(treasure1.getX(), treasure1.getY());         // printing treasure
        terminal.putCharacter(treasure1.getSymbol());

        terminal.setCursorPosition(tom.getX(), tom.getY());                      //printing tom
        terminal.putCharacter(tom.getSymbol());
        terminal.setCursorPosition(tom.getOldX(), tom.getOldY());
        terminal.putCharacter(' ');


        for(Police p: listOfPolice){
            terminal.setCursorPosition(p.getX(), p.getY());                      //printing police
            terminal.putCharacter(p.getSymbol());
            terminal.setCursorPosition(p.getOldX(), p.getOldY());
            terminal.putCharacter(' ');
        }

        obstacle.printBlock(3, 3, terminal);                     //Printing obstacles
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

    public static void checkMove(Terminal terminal, Player tom, Treasure treasure1, List <Obstacle> listOfObstacles, List<Police> listOfPolice) throws IOException, InterruptedException {

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

            if(!treasure1.isCollected()) {
                terminal.setCursorPosition(tom.getX(), tom.getY());
                new MP3Player(new File("Ball+Hit+Cheer.mp3")).play();
                treasure1.setSymbol(' ');
                treasure1.setCollected(true);
            }
        }

        for(Police p:listOfPolice){
            if(tom.getX() == p.getX() && tom.getY() == p.getY()){
                tom.setAlive(false);
                terminal.bell();
            }
        }
        terminal.flush();
    }

    public static void movePolice(List<Police> listOfPolices, Player tom){

        if(listOfPolices.get(0).count % 2 == 0) {
            for (Police p : listOfPolices) {
                p.moveTowards(tom);
            }
        }

        for(Police p: listOfPolices){
            p.count++;
        }
    }

    static void printTextDelay(Terminal terminal, String text, int startingX, int startingY, int milliseconds) throws IOException, InterruptedException {

        char[] charArray = text.toCharArray();
        int loopEnd = startingX + charArray.length;

        for (char e : charArray) {

            terminal.setCursorPosition(startingX, startingY);
            terminal.putCharacter(e);
            startingX++;
            Thread.sleep(milliseconds);
            terminal.flush();
        }

    }


}
