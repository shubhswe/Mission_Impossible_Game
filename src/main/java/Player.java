public class Player {

    private int x;
    private int y;
    private  int oldX;
    private  int oldY;
    private int direction = 4;
    private char symbol = 'X'; // X ﬕ
    private boolean isAlive = true;
    private boolean levelCompleted = false;
    private boolean allItemsCollected = false;

    public boolean isLevelCompleted() {
        return levelCompleted;
    }

    public void setLevelCompleted(boolean levelCompleted) {
        this.levelCompleted = levelCompleted;
    }

    public boolean isAllItemsCollected() {
        return allItemsCollected;
    }

    public void setAllItemsCollected(boolean allItemsCollected) {
        this.allItemsCollected = allItemsCollected;
    }

    public Player(int x, int y) {
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

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move(){

        switch (this.direction){
            case 1:
                moveUp();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveDown();
                break;
            case 4:
                moveRight();
                break;
        }

    }

    public void moveUp(){
        oldX = x;
        oldY = y;
        y--;
    }
    public void moveLeft(){
        oldX = x;
        oldY = y;
        x--;
    }
    public void moveDown(){
        oldX = x;
        oldY = y;
        y++;
    }
    public void moveRight(){
        oldX = x;
        oldY = y;
        x++;
    }

    public void reverseDirection(){

        switch (this.direction){
            case 1:
                setDirection(3);
                break;
            case 2:
                setDirection(4);
                break;
            case 3:
                setDirection(1);
                break;
            case 4:
                setDirection(2);
                break;
        }
    }

//    public boolean levelComplete(Player tom, int x, int y, List<Position> exitPos, Terminal terminal){
//        boolean complete=false;
//
//        for(Position p: getPositions()){
//            if( tom.getX() == p.getX() && tom.getY() == p.getY()){
//                tom.reverseDirection();
//                tom.move();
//            }
//        }
//
//
//        return complete;
//    }
}
