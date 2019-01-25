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
        Collections.addAll(listOfObstacles, obstacle, topWall, leftWall, bottomWall, rightWall);

        List<Police> listOfPolice = new ArrayList<>();
        listOfPolice.add(new Police(0, 2, 'Ẋ'));
        listOfPolice.add(new Police(78, 2, 'Ẋ'));

        List<Position> listOfExitPos = new ArrayList<>();
        listOfExitPos.add(new Position(78, 20));
        listOfExitPos.add(new Position(78, 21));
        listOfExitPos.add(new Position(78, 22));

        KeyStroke keyStroke = null;
        int gameSpeed = 80;         //Sets the speed of the game, lower number means faster game (milliseconds)
        boolean isGameOver = false;

        //welcomeScreen(terminal);
        levelCountdown(terminal);

        while (true) {
            while (tom.isAlive()) {                     //Level 1

                drawBoard(terminal, tom, obstacle, topWall, leftWall, bottomWall, rightWall, treasure1, listOfPolice);

                movePlayer(keyStroke, tom, terminal);               //Sets direction based on arrow keys and moves player one step in set direction. (default direction is right.)

                movePolice(listOfPolice, tom);

                checkMove(terminal, tom, treasure1, listOfObstacles, listOfPolice, listOfExitPos);

                Thread.sleep(gameSpeed);

            }

            if (!treasure1.isCollected() && tom.isLevelCompleted() || !tom.isAlive() && !tom.isLevelCompleted()) {
                gameoverMessage(terminal);
                break;
            }

            level2(terminal, tom, listOfExitPos);

            if (!tom.isAlive() || !tom.isAllItemsCollected()) {
                gameoverMessage(terminal);
                break;
            }

            gameCompletedMessage(terminal);
        }

        terminal.setForegroundColor(TextColor.ANSI.GREEN);
        printTextDelay(terminal,"Game shutting down...", 30,20,60);
        Thread.sleep(2000);
        terminal.close();
    }

    public static void level2(Terminal terminal, Player tom, List<Position> listOfExitPos) throws IOException, InterruptedException {
        terminal.clearScreen();
        level2Countdown(terminal);
        terminal.clearScreen();

        Treasure treasure1 = new Treasure(75, 5);
        treasure1.setCollected(false);
        Treasure treasure2 = new Treasure(10, 20);
        treasure2.setCollected(false);

        List<Obstacle> listOfObstacles = new ArrayList<>(); //list of obstacles
        Obstacle obstacle = new Obstacle();
        Obstacle topWall = new Obstacle();
        Obstacle leftWall = new Obstacle();
        Obstacle bottomWall = new Obstacle();
        Obstacle rightWall = new Obstacle();
        Obstacle wall1 = new Obstacle();
        Obstacle wall2 = new Obstacle();
        Obstacle wall3 = new Obstacle();
        Obstacle wall4 = new Obstacle();
        Collections.addAll(listOfObstacles, obstacle, topWall, leftWall, bottomWall, rightWall, wall1, wall2, wall3, wall4);

        Police police = new Police(9, 19, 'Ẋ');
        Police police2 = new Police(74, 6, 'Ẋ');
        List<Police> policeList = new ArrayList<>();
        policeList.add(police);
        policeList.add(police2);

        KeyStroke keyStroke = null;
        tom.setX(10);
        tom.setY(2);
        tom.setDirection(4);
        tom.setAlive(true);

        int gameSpeed = 100;

        while (tom.isAlive()) {

            drawBoard2(terminal, tom, treasure1, treasure2, topWall, leftWall, bottomWall, rightWall, wall1, wall2, wall3, wall4, police, police2);

            movePlayer(keyStroke, tom, terminal);

            movePolice(policeList, tom);

            checkMove2(terminal, tom, treasure1, treasure2, listOfObstacles, policeList, listOfExitPos);

            Thread.sleep(gameSpeed);
        }

    }

    public static void welcomeScreen(Terminal terminal) throws IOException, InterruptedException {
        boolean hasStartedGame = false;
        KeyStroke keyStroke = null;

        while (!hasStartedGame) {

            terminal.setForegroundColor(TextColor.ANSI.GREEN);
            printTextDelay(terminal, "MISSION IMPOSSIBLE: THE GAME", 25, 3, 200);
            printTextDelay(terminal, "-----------------------------", 25, 2, 30);
            printTextDelay(terminal, "-----------------------------", 25, 4, 30);

            printTextDelay(terminal, "Some super evil villain has", 25, 7, 60);
            printTextDelay(terminal, "stolen valuable items and put", 25, 8, 60);
            printTextDelay(terminal, "them in a museum.", 25, 9, 60);
            Thread.sleep(200);
            printTextDelay(terminal, "We need to get them back.", 25, 11, 60);
            printTextDelay(terminal, "Collect the items", 25, 13, 60);
            printTextDelay(terminal, "and get out of there.", 25, 14, 60);
            printTextDelay(terminal, "They WILL try to get you...", 25, 16, 60);


            printTextDelay(terminal, "Start mission:", 25, 18, 60);

            while (keyStroke == null) {
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

        terminal.setForegroundColor(TextColor.ANSI.GREEN);
        printTextDelay(terminal, "Level 1, starting in:", 30, 10, 60);

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

    public static void level2Countdown(Terminal terminal) throws IOException, InterruptedException {

        Thread.sleep(600);

        terminal.setForegroundColor(TextColor.ANSI.GREEN);
        printTextDelay(terminal, "Level 2, starting in:", 30, 10, 60);

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

    public static void gameoverMessage(Terminal terminal) throws IOException, InterruptedException {

        terminal.clearScreen();
        terminal.setForegroundColor(TextColor.ANSI.RED);

        printTextDelay(terminal, "GAME OVER", 35, 10, 200);
        terminal.setForegroundColor(TextColor.ANSI.WHITE);

    }

    public static void gameCompletedMessage(Terminal terminal) throws IOException, InterruptedException {

        terminal.clearScreen();
        terminal.setForegroundColor(TextColor.ANSI.YELLOW);

        printTextDelay(terminal, "CONGRATULATIONS!", 35, 10, 200);
        printTextDelay(terminal, "-----------------------------", 29, 9, 30);
        printTextDelay(terminal, "-----------------------------", 29, 11, 30);
        printTextDelay(terminal, "||||||", 29, 10, 30);
        printTextDelay(terminal, "||||||", 51, 10, 30);

        printTextDelay(terminal, "You've completed the game.", 30, 13, 200);
        printTextDelay(terminal, "You saved the world", 32, 15, 200);


        terminal.setForegroundColor(TextColor.ANSI.WHITE);
    }

    public static void drawBoard(Terminal terminal, Player tom, Obstacle obstacle, Obstacle topWall, Obstacle leftWall, Obstacle bottomWall, Obstacle rightWall, Treasure treasure1, List<Police> listOfPolice) throws IOException, InterruptedException {

        if (!treasure1.isCollected()) {
            printTextDelay(terminal, "Collect item(s)", 32, 1, 0);
        } else {
            printTextDelay(terminal, "Get out of there!", 32, 1, 0);

            if (treasure1.getBlinker() % 2 == 0) {
                terminal.setForegroundColor(TextColor.Indexed.fromRGB(255, 234, 0)); // Exit arrow blinking color
            } else {
                terminal.setForegroundColor(TextColor.Indexed.fromRGB(128, 75, 0)); // Exit arrow blinking color
            }

            printTextDelay(terminal, ">>", 77, 21, 0);

        }

        terminal.setCursorPosition(treasure1.getX(), treasure1.getY());         // printing treasure
        if (treasure1.getBlinker() % 2 == 0) {
            terminal.setForegroundColor(TextColor.Indexed.fromRGB(255, 234, 0)); // Treasure color
        } else {
            terminal.setForegroundColor(TextColor.Indexed.fromRGB(128, 75, 0)); // Treasure color
        }
        terminal.putCharacter(treasure1.getSymbol());
        treasure1.setTreasureColorSwitch();
        terminal.setForegroundColor(TextColor.ANSI.WHITE);

        terminal.setCursorPosition(tom.getX(), tom.getY());                      //printing tom
        terminal.setForegroundColor(TextColor.Indexed.fromRGB(245, 40, 40)); // Tom color
        terminal.putCharacter(tom.getSymbol());
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        terminal.setCursorPosition(tom.getOldX(), tom.getOldY());
        terminal.putCharacter(' ');

        for (Police p : listOfPolice) {
            terminal.setCursorPosition(p.getX(), p.getY());                      //printing police
            terminal.setForegroundColor(TextColor.Indexed.fromRGB(245, 127, 23)); // Police color
            terminal.putCharacter(p.getSymbol());
            terminal.setForegroundColor(TextColor.ANSI.WHITE);
            terminal.setCursorPosition(p.getOldX(), p.getOldY());
            terminal.putCharacter(' ');
        }

        terminal.setForegroundColor(TextColor.Indexed.fromRGB(138, 51, 53)); // Obstacle color
        obstacle.printBlock(3, 3, terminal);                     //Printing obstacles
        topWall.printBlock(80, 1, 0, 0, terminal);
        leftWall.printBlock(2, 20, 0, 4, terminal);
        bottomWall.printBlock(80, 1, 0, 23, terminal);
        rightWall.printBlock(2, 20, 78, 0, terminal);
        terminal.setForegroundColor(TextColor.ANSI.WHITE);

//        terminal.setCursorPosition(78, 20);
//        terminal.putCharacter('E');
//        terminal.setCursorPosition(78, 21);
//        terminal.putCharacter('E');
//        terminal.setCursorPosition(78, 22);
//        terminal.putCharacter('E');


        terminal.flush();
    }

    public static void drawBoard2(Terminal terminal, Player tom, Treasure treasure1, Treasure treasure2, Obstacle topWall, Obstacle leftWall, Obstacle bottomWall, Obstacle rightWall, Obstacle wall1, Obstacle wall2, Obstacle wall3, Obstacle wall4, Police police, Police police2) throws IOException, InterruptedException {

        if (!treasure1.isCollected() && !treasure2.isCollected()) {
            printTextDelay(terminal, "Collect item(s)", 32, 1, 0);
        } else if (treasure1.isCollected() && treasure2.isCollected()) {
            printTextDelay(terminal, "Get out of there!", 32, 1, 0);

            if (treasure1.getBlinker() % 2 == 0) {
                terminal.setForegroundColor(TextColor.Indexed.fromRGB(255, 234, 0)); // Exit arrow blinking color
            } else {
                terminal.setForegroundColor(TextColor.Indexed.fromRGB(128, 75, 0)); // Exit arrow blinking color
            }
            printTextDelay(terminal, ">>", 77, 21, 0);
        }

        terminal.setCursorPosition(treasure2.getX(), treasure2.getY());         // printing treasure2

        if (treasure2.getBlinker() % 2 == 0) {
            terminal.setForegroundColor(TextColor.Indexed.fromRGB(255, 234, 0)); // Treasure color
        } else {
            terminal.setForegroundColor(TextColor.Indexed.fromRGB(128, 75, 0)); // Treasure color
        }
        terminal.putCharacter(treasure2.getSymbol());
        treasure2.setTreasureColorSwitch();
        terminal.setForegroundColor(TextColor.ANSI.WHITE);

        terminal.setCursorPosition(treasure1.getX(), treasure1.getY());         // printing treasure
        if (treasure1.getBlinker() % 2 == 0) {
            terminal.setForegroundColor(TextColor.Indexed.fromRGB(255, 234, 0)); // Treasure color
        } else {
            terminal.setForegroundColor(TextColor.Indexed.fromRGB(128, 75, 0)); // Treasure color
        }
        terminal.putCharacter(treasure1.getSymbol());
        treasure1.setTreasureColorSwitch();
        terminal.setForegroundColor(TextColor.ANSI.WHITE);

        terminal.setCursorPosition(tom.getX(), tom.getY());                      //printing tom
        terminal.setForegroundColor(TextColor.Indexed.fromRGB(245, 40, 40)); // Tom color
        terminal.putCharacter(tom.getSymbol());
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        terminal.setCursorPosition(tom.getOldX(), tom.getOldY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(police.getX(), police.getY());                                //printing police
        terminal.setForegroundColor(TextColor.Indexed.fromRGB(245, 127, 23));  // Police color
        terminal.putCharacter(police.getSymbol());
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        terminal.setCursorPosition(police.getOldX(), police.getOldY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(police2.getX(), police2.getY());                                //printing police2
        terminal.setForegroundColor(TextColor.Indexed.fromRGB(245, 127, 23));  // Police color
        terminal.putCharacter(police2.getSymbol());
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        terminal.setCursorPosition(police2.getOldX(), police2.getOldY());
        terminal.putCharacter(' ');

        terminal.setForegroundColor(TextColor.Indexed.fromRGB(138, 51, 53)); // Obstacle color
        topWall.printBlock(80, 1, 0, 0, terminal);
        leftWall.printBlock(2, 20, 0, 4, terminal);
        bottomWall.printBlock(80, 1, 0, 23, terminal);
        rightWall.printBlock(2, 20, 78, 0, terminal);
        wall1.printBlock(15, 1, 0, 15, terminal);
        wall2.printBlock(2, 8, 19, 15, terminal);
        wall3.printBlock(45, 1, 35, 15, terminal);
        wall4.printBlock(2, 12, 50, 0, terminal);
        terminal.setForegroundColor(TextColor.ANSI.WHITE);

        terminal.flush();
    }

    public static void movePlayer(KeyStroke keyStroke, Player tom, Terminal terminal) throws IOException {
        keyStroke = terminal.pollInput();

        if (keyStroke != null) {

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

    public static void checkMove(Terminal terminal, Player tom, Treasure treasure1, List<Obstacle> listOfObstacles, List<Police> listOfPolice, List<Position> listOfExitPos) throws IOException, InterruptedException {

        for (Obstacle o : listOfObstacles) {
            for (Position p : o.getPositions()) {
                if (tom.getX() == p.getX() && tom.getY() == p.getY()) {
                    tom.reverseDirection();
                    tom.move();
                    terminal.flush();
                }
            }
        }

        if (tom.getX() == treasure1.getX() && tom.getY() == treasure1.getY()) {

            if (!treasure1.isCollected()) {
                terminal.setCursorPosition(tom.getX(), tom.getY());
                new MP3Player(new File("Ball+Hit+Cheer.mp3")).play();
                treasure1.setSymbol(' ');
                treasure1.setCollected(true);
            }
        }

        for (Police p : listOfPolice) {
            if (tom.getX() == p.getX() && tom.getY() == p.getY()) {
                tom.setAlive(false);
                terminal.bell();
            }
        }
        if (listOfPolice.get(0).getX() == listOfPolice.get(1).getX() && listOfPolice.get(0).getY() == listOfPolice.get(1).getY()) {
            listOfPolice.get(0).setX();
            listOfPolice.get(0).setY();
            terminal.flush();
        }


        for (Position p : listOfExitPos) {
            if (treasure1.isCollected()) {
                if (tom.getX() == p.getX() && tom.getY() == p.getY()) {
                    tom.setLevelCompleted(true);
                    terminal.setForegroundColor(TextColor.ANSI.GREEN);
                    printTextDelay(terminal, "Level completed!", 35, 12, 120);
                    tom.setAlive(false);
                    terminal.setForegroundColor(TextColor.ANSI.WHITE);
                }
            } else if (!treasure1.isCollected()) {
                if (tom.getX() == p.getX() && tom.getY() == p.getY()) {

                    tom.setAlive(false);
                }
            }
        }
        terminal.flush();
    }

    public static void checkMove2(Terminal terminal, Player tom, Treasure treasure1, Treasure treasure2, List<Obstacle> listOfObstacles, List<Police> policeList, List<Position> listOfExitPos) throws IOException, InterruptedException {

        for (Obstacle o : listOfObstacles) {
            for (Position p : o.getPositions()) {
                if (tom.getX() == p.getX() && tom.getY() == p.getY()) {
                    tom.reverseDirection();
                    tom.move();
                    terminal.flush();
                }
            }
        }

        if (tom.getX() == treasure1.getX() && tom.getY() == treasure1.getY()) {

            if (!treasure1.isCollected()) {
                terminal.setCursorPosition(tom.getX(), tom.getY());
                new MP3Player(new File("Ball+Hit+Cheer.mp3")).play();
                treasure1.setSymbol(' ');
                treasure1.setCollected(true);
            }
        }

        if (tom.getX() == treasure2.getX() && tom.getY() == treasure2.getY()) {

            if (!treasure2.isCollected()) {
                terminal.setCursorPosition(tom.getX(), tom.getY());
                new MP3Player(new File("Ball+Hit+Cheer.mp3")).play();
                treasure2.setSymbol(' ');
                treasure2.setCollected(true);
            }
        }

        if (treasure1.isCollected() && treasure2.isCollected()) {
            tom.setAllItemsCollected(true);
        }

        for (Police p : policeList) {
            if (tom.getX() == p.getX() && tom.getY() == p.getY()) {
                tom.setAlive(false);
                terminal.bell();
            }
        }
        if (policeList.get(0).getX() == policeList.get(1).getX() && policeList.get(0).getY() == policeList.get(1).getY()) {
            policeList.get(0).setX();
            policeList.get(0).setY();
            terminal.flush();
        }

        for (Position p : listOfExitPos) {
            if (treasure1.isCollected()) {
                if (tom.getX() == p.getX() && tom.getY() == p.getY()) {
                    tom.setLevelCompleted(true);
                    terminal.setForegroundColor(TextColor.ANSI.GREEN);
                    printTextDelay(terminal, "Level completed!", 35, 12, 120);
                    tom.setAlive(false);
                    terminal.setForegroundColor(TextColor.ANSI.WHITE);
                }
            } else if (!treasure1.isCollected()) {
                if (tom.getX() == p.getX() && tom.getY() == p.getY()) {

                    tom.setAlive(false);
                }
            }
        }

        terminal.flush();
    }

    public static void movePolice(List<Police> listOfPolices, Player tom) {

        if (listOfPolices.get(0).count % 2 == 0) {
            for (Police p : listOfPolices) {
                p.moveTowards(tom);
            }
        }

        for (Police p : listOfPolices) {
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
