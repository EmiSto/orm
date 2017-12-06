
import java.awt.Color;
import java.util.ArrayList;

public abstract class Snake {

    public boolean Up = false;
    public boolean Right = true;
    public boolean Left = false;
    public boolean Down = false;

    public int nBalls;
    public ArrayList<Integer> xBalls = new ArrayList<Integer>();
    public ArrayList<Integer> yBalls = new ArrayList<Integer>();

    public int moveSize = 20;
    public int ballHeight = 20;
    public int ballWidth = 20;

    public int worldX;
    public int worldY;
    
    public char name;
    
    boolean alive = true;
    Color color;

    public Snake(char c){
	this.name = c;
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

    public char[] getDirection() {
        char[] info = new char[2];
        info[0] = this.name;
        if (Up) {
            info[1] = 'U';
        }
        if (Down) {
            info[1] = 'D';
        }
        if (Right) {
            info[1] = 'R';
        }
        if (Left) {
            info[1] = 'L';
        }
        return info;
    }

    public ArrayList<Integer> getSnakeX() {
        return xBalls;
    }

    public ArrayList<Integer> getSnakeY() {
        return yBalls;
    }
    
    public void updatePos(ArrayList<Integer> xBalls, ArrayList<Integer> yBalls){
        this.xBalls = xBalls;
        this.yBalls = yBalls;
    }

    public int getSize() {
        return this.nBalls;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getHeight(){
        return ballHeight;
    }
    
    public int getWidth(){
        return ballWidth;
    }
    
     public char getName() {
        return this.name;
    }
}
