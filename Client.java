import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.*;
import java.awt.event.*;
import java.lang.Boolean;
import java.io.*;
import java.net.*;

class Client extends Canvas implements ActionListener {

    private Timer timer;
    private int speed = 500;
    private int pause = 1000;
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

    public void update() throws Exception {
	sendDirections();
    }

    public void sendDirections()throws Exception{
	DatagramSocket clientSocket = new DatagramSocket();
	String ip = "192.168.1.89";
	int port = 9876;
	InetAddress IPAddress = InetAddress.getByName(ip);
	char[] direction = mySnake.getDirection();
	String dirString = "" + direction[0] + direction[1];
	
	byte[] sendData = new byte[2048];
        byte[] receiveData = new byte[2048];
	sendData = dirString.getBytes();
	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	clientSocket.send(sendPacket);

	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	clientSocket.receive(receivePacket);
	String received = new String(receivePacket.getData());
	System.out.println("Server: " + received);
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

    public void actionPerformed(ActionEvent e){
	try{
        update();
        repaint();
        timer.setInitialDelay(pause);
        timer.start();
	}catch(Exception ktffkf){
	    System.out.println("throw that shit");
	}
    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    mySnake.setDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                    mySnake.setDirection('D');
                    break;
                case KeyEvent.VK_LEFT:
                    mySnake.setDirection('L');
                    break;
                case KeyEvent.VK_RIGHT:
                    mySnake.setDirection('R');
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
	client.mySnake = new ClientSnake('a');
        client.initGame(client);
    }
}
