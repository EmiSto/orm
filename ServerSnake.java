
import java.util.ArrayList;

public class ServerSnake {

    private boolean Up = false;
    private boolean Right = true;
    private boolean Left = false;
    private boolean Down = false;

    int worldX;
    int worldY;

    int moveSize = 20;

    int ballHeight = 20;
    int ballWidth = 20;
    int nBalls;
    ArrayList<Integer> xBalls = new ArrayList<Integer>();
    ArrayList<Integer> yBalls = new ArrayList<Integer>();
    
    private char name;
    
    public void ServerSnake(char name){
        this.name = name;
    }

    public void move() {
        int x;
        int y;

        if (Up) {

            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            y = y - moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Down) {

            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            y = y + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Left) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x - moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Right) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        }
    }

    public void setDirection(char c) {
        if (c == 'U') {
            Up = true;
            Down = false;
            Right = false;
            Left = false;
        }
        if (c == 'D') {
            Up = false;
            Down = true;
            Right = false;
            Left = false;
        }
        if (c == 'R') {
            Up = false;
            Down = false;
            Right = true;
            Left = false;
        }
        if (c == 'L') {
            Up = false;
            Down = false;
            Right = false;
            Left = true;
        }
    }
    
    public char getName(){
        return this.name;
    }

    public ArrayList<Integer> getSnakeX() {
        return xBalls;
    }

    public ArrayList<Integer> getSnakeY() {
        return yBalls;
    }

    public void grow() {
        this.nBalls = this.nBalls + 1;

        int x = xBalls.get(0);
        int y = yBalls.get(0);

        xBalls.add(0, x);
        yBalls.add(0, y);
    }
}
