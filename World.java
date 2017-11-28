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

    int worldX;
    int worldY;

    int moveSize = 10;

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

        this.fruit = new Fruit(worldX, worldY);

        setFocusable(true);
        addKeyListener(new TAdapter());

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.yellow);

        int x;
        int y;
        for (int i = 0; i < nBalls; i++) {
            x = xBalls.get(i);
            y = yBalls.get(i);
            g.fillOval(x, y, ballWidth, ballHeight);
        }

        g.fillRect(fruit.getxFruit(), fruit.getyFruit(), fruit.getWidth(), fruit.getHeight());
    }

    public void grow() {
        this.nBalls = this.nBalls + 1;

        int x = xBalls.get(0);
        int y = yBalls.get(0);

        xBalls.add(0, x);
        yBalls.add(0, y);
    }

    public void moveDown() {

        int x = this.xBalls.get(nBalls - 1);
        int y = this.yBalls.get(nBalls - 1);
        y = y + moveSize;

        this.xBalls.add(x);
        this.yBalls.add(y);

        this.xBalls.remove(0);
        this.yBalls.remove(0);

        if (eat()) {
            grow();
            respawnFruit();
        }
    }

    public void moveUp() {

        int x = this.xBalls.get(nBalls - 1);
        int y = this.yBalls.get(nBalls - 1);
        y = y - moveSize;

        this.xBalls.add(x);
        this.yBalls.add(y);

        this.xBalls.remove(0);
        this.yBalls.remove(0);

        if (eat()) {
            grow();
            respawnFruit();
        }
    }

    public void moveLeft() {

        int x = this.xBalls.get(nBalls - 1);
        int y = this.yBalls.get(nBalls - 1);
        x = x - moveSize;

        this.xBalls.add(x);
        this.yBalls.add(y);

        this.xBalls.remove(0);
        this.yBalls.remove(0);

        if (eat()) {
            grow();
            respawnFruit();
        }
    }

    public void moveRight() {
        int x = this.xBalls.get(nBalls - 1);
        int y = this.yBalls.get(nBalls - 1);
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

    public boolean eat() {
        int xHead = xBalls.get(nBalls - 1);
        int xFruit = fruit.getxFruit();
        int yHead = yBalls.get(nBalls -1);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_DOWN) {
                moveDown();
            }

            if (key == KeyEvent.VK_UP) {
                moveUp();
            }

            if (key == KeyEvent.VK_RIGHT) {
                moveRight();
            }

            if (key == KeyEvent.VK_LEFT) {
                moveLeft();
            }
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
