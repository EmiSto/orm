<<<<<<< HEAD

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
=======
import java.awt.event.ActionListener;

class Snake {

    public int worldX;
    public int worldY;
    public int moveSize = 20;
    public int ballHeight = 20;
    public int ballWidth = 20;
    public int nBalls;
    public ArrayList<Integer> xBalls = new ArrayList<Integer>();
    public ArrayList<Integer> yBalls = new ArrayList<Integer>();

    public Snake(int posX, int posY, int worldX, int worldY){
	this.worldX = worldX;
	this.worldY = worldY;

	this.nBalls = 4;
	this.xBalls.add(posX);
	this.yBalls.add(posY);
	this.xBalls.add(posX + ballHeight);
	this.yBalls.add(posY + ballsWidth);
	this.xBalls.add(posX + 2*ballHeight);
	this.yBalls.add(posY + 2*ballsWidth);
	this.xBalls.add(posX + 3*ballHeight);
	this.yBalls.add(posY + 3*ballsWidth);
    }
    
    public void checkCollision() {
        
        int x = xBalls.get(nBalls-1);
        int y = yBalls.get(nBalls-1);
        for (int i = 0; i < nBalls - 1; i++) {

            if ((xBalls.get(nBalls - 1).equals( xBalls.get(i))) && (yBalls.get(nBalls - 1).equals(  yBalls.get(i)))) {
                System.out.println("Game over");
            }
        }
        if(x >= worldX - ballWidth){
           System.out.println("högervägg");
        }
        if(x < 0 + ballWidth){
            System.out.println("Vänstervägg");
        }
        if(y >= worldY - ballHeight/2){
            System.out.println("golvet");
        }
        if(y < 0 + ballHeight){
            System.out.println("taket");
        }
    }
    
>>>>>>> 880667ec38cfb10ec1b1db2490fbbffed71ae5ec
}
