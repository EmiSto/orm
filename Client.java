
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.lang.Boolean;
import java.util.ArrayList;

class Client extends Canvas implements ActionListener {

    private Timer timer;
    private int speed;
    private int pause;
    //World w;
    // playerInput pi;
    public int worldX = 800;
    public int worldY = 600;

    public ClientSnake mySnake;
    public ArrayList<ClientSnake> otherSnake;

    public void paint(Graphics g) {

        super.paint(g);
        int ballWidth = mySnake.getWidth();
        int ballHeight = mySnake.getHeight();

        g.setColor(mySnake.getColor());
        int nBalls = mySnake.getSize();
        ArrayList<Integer> xBalls = mySnake.getSnakeX();
        ArrayList<Integer> yBalls = mySnake.getSnakeY();

        //Rita våran orm
        int x;
        int y;
        for (int i = 0; i < nBalls - 1; i++) {
            x = xBalls.get(i);
            y = yBalls.get(i);
            g.fillRect(x, y, ballWidth, ballHeight);
        }
        g.setColor(Color.red);
        g.fillRect(xBalls.get(nBalls - 1), yBalls.get(nBalls - 1), ballWidth, ballHeight);

        //Rita alla andras ormar
        for (int i = 0; i < otherSnake.size() - 1; i++) {
            g.setColor(otherSnake.get(i).getColor());
            nBalls = otherSnake.get(i).getSize();
            xBalls = otherSnake.get(i).getSnakeX();
            yBalls = otherSnake.get(i).getSnakeY();

            for (int j = 0; j < nBalls - 1; j++) {
                x = xBalls.get(j);
                y = yBalls.get(j);
                g.fillRect(x, y, ballWidth, ballHeight);
            }
            g.setColor(Color.red);
            g.fillRect(xBalls.get(nBalls - 1), yBalls.get(nBalls - 1), ballWidth, ballHeight);
        }
    }

    public void update() {

    }

    public void updatesnakes(ArrayList<Snake> snakes) {
        for (int i = 0; i < snakes.size(); i++) {
            if (this.mySnake.getName() == snakes.get(i).getName()) {
                this.mySnake.setDirection(snakes.get(i).getDirection()[1]);
                this.mySnake.updatePos(snakes.get(i).getSnakeX(), snakes.get(i).getSnakeY());
            } else {
                for (int j = 0; j < otherSnake.size(); i++) {
                    if (this.otherSnake.get(j).getName() == snakes.get(i).getName()) {
                        this.otherSnake.get(j).setDirection(snakes.get(i).getDirection()[1]);
                        this.otherSnake.get(j).updatePos(snakes.get(i).getSnakeX(), snakes.get(i).getSnakeY());
                    }
                }
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
        timer.setInitialDelay(pause);
        timer.start();

    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    this.mySnake.setDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                    this.mySnake.setDirection('D');
                    break;
                case KeyEvent.VK_LEFT:
                    this.mySnake.setDirection('L');
                    break;
                case KeyEvent.VK_RIGHT:
                    this.mySnake.setDirection('R');
                    break;
                default:
                    break;
            }
        }
    }

    private void initGame(Client c) {
        JFrame jf = new JFrame("New Window");
        setFocusable(true);
        jf.setSize(worldX, worldY);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(c);
        setBackground(Color.black);
        addKeyListener(new TAdapter());
        timer = new Timer(speed, this);
        timer.start();
    }

    public static void main(String args[]) {
        Client client = new Client();
        client.initGame(client);
    }
}
