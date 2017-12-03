<<<<<<< HEAD

=======
>>>>>>> 880667ec38cfb10ec1b1db2490fbbffed71ae5ec
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.lang.Boolean;
<<<<<<< HEAD
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

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
        timer.setInitialDelay(pause);
        timer.start();
=======

class Client extends Canvas implements ActionListener
{
    //World w;
    // playerInput pi;
    publice int worldX = 800;
    public int worldY = 600;

    public paint(Graphics g){
	
    }
    
    public void update(){
    }
    
    public void actionPerformed(ActionEvent e) {
	update();
	repaint();
>>>>>>> 880667ec38cfb10ec1b1db2490fbbffed71ae5ec
    }

    private class TAdapter extends KeyAdapter {

<<<<<<< HEAD
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
=======
	public void keyPressed(KeyEvent e){
	    int keyCode = e.getKeyCode();
	    switch(keyCode){
	    case KeyEvent.VK_UP:
		System.out.println("UP");
		break;
	    case KeyEvent.VK_DOWN:

		System.out.println("DOWN");
		break;
	    case KeyEvent.VK_LEFT:

		System.out.println("LEFT");
		break;
	    case KeyEvent.VK_RIGHT:
		System.out.println("RIGHT");
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
    }
    
    public static void main(String args[])
    {
	Client client = new Client();
	client.initGame(client);
>>>>>>> 880667ec38cfb10ec1b1db2490fbbffed71ae5ec
    }
}
