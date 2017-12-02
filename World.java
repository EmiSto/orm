/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingtest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Emil
 */
public class World extends JPanel implements ActionListener {

    public boolean Up = false;
    public boolean Right = true;
    public boolean Left = false;
    public boolean Down = false;

    int worldX;
    int worldY;

    int moveSize = 20;

    int ballHeight = 20;
    int ballWidth = 20;
    int nBalls;
    ArrayList<Integer> xBalls = new ArrayList<Integer>();
    ArrayList<Integer> yBalls = new ArrayList<Integer>();

    Fruit fruit;

    public World(int x, int y, int worldX, int worldY) {

        this.worldX = worldX;
        this.worldY = worldY;

        this.nBalls = 1;
        this.xBalls.add(x);
        this.yBalls.add(y);
        grow();
        grow();
        grow();
        grow();

        this.fruit = new Fruit(worldX, worldY);

        setFocusable(true);
        addKeyListener(new TAdapter());

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.yellow);

        int x;
        int y;
        for (int i = 0; i < nBalls - 1; i++) {
            x = xBalls.get(i);
            y = yBalls.get(i);
            g.fillRect(x, y, ballWidth, ballHeight);
        }
        g.setColor(Color.red);
        g.fillRect(xBalls.get(nBalls - 1), yBalls.get(nBalls - 1), ballWidth, ballHeight);

        g.fillRect(fruit.getxFruit(), fruit.getyFruit(), fruit.getWidth(), fruit.getHeight());
    }

    public void grow() {
        this.nBalls = this.nBalls + 1;

        int x = xBalls.get(0);
        int y = yBalls.get(0);

        xBalls.add(0, x);
        yBalls.add(0, y);
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

            if (eat()) {
                grow();
                respawnFruit();
            }
        } else if (Down) {

            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            y = y + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

            if (eat()) {
                grow();
                respawnFruit();
            }
        } else if (Left) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x - moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

            if (eat()) {
                grow();
                respawnFruit();
            }
        } else if (Right) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

            if (eat()) {
                grow();
                respawnFruit();
            }

        }

    }

    public boolean eat() {
        int xHead = xBalls.get(nBalls - 1);
        int xFruit = fruit.getxFruit();
        int yHead = yBalls.get(nBalls - 1);
        int yFruit = fruit.getyFruit();

        boolean xCollision = xHead <= xFruit + fruit.getWidth() && xHead >= xFruit - fruit.getWidth();
        boolean yCollision = yHead <= yFruit + fruit.getHeight() && yHead >= yFruit - fruit.getHeight();
        if (xCollision && yCollision) {
            return true;
        }
        return false;
    }

    public void respawnFruit() {

        fruit.setxFruit((int) (Math.random() * this.worldX));
        fruit.setyFruit((int) (Math.random() * this.worldY));
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if (Down) {
                        break;
                    }
                    Up = true;
                    Down = false;
                    Right = false;
                    Left = false;
                    break;
                case KeyEvent.VK_DOWN:
                    if (Up) {
                        break;
                    }
                    Up = false;
                    Down = true;
                    Right = false;
                    Left = false;
                    break;
                case KeyEvent.VK_LEFT:
                    if (Right) {
                        break;
                    }
                    Up = false;
                    Down = false;
                    Right = false;
                    Left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (Left) {
                        break;
                    }
                    Up = false;
                    Down = false;
                    Right = true;
                    Left = false;
                    break;
                default:
                    break;
            }

            move();
            checkCollision();
            repaint();
        }
    }


    /*public static void main(String args[]) {
        JFrame js = new JFrame();
        World w = new World(20, 200);
        js.add(w);
        js.setSize(800, 800);
        js.setBackground(Color.black);
        js.setVisible(true);
        
    }*/
}
