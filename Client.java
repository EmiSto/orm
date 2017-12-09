import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.lang.Boolean;
import java.io.*;
import java.net.*;

class Client extends Canvas implements ActionListener {

    private Timer timer;
    private int speed = 500;
    private int pause = 1000;
    private Parser parser = new Parser();
    //World w;
    // playerInput pi;
    public int worldX = 800;
    public int worldY = 600;

    public Snake mySnake;
    public ArrayList<Snake> otherSnake = new ArrayList();

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
	//String ip = "192.168.1.89";
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
	updateSnakes(received);
    }
    
    public void updateSnakes(String snakes) {
	ArrayList<String> info = parser.parse(snakes);
	char id;
	String headX;
	String headY;

	for (int i = 0; i < 1 + otherSnake.size(); i++) {
	    id = info.get(0).charAt(0);
	    headX = info.get(1);
	    headY = info.get(2);
	    // System.out.println(id);
	    if(id == mySnake.getName()) {
		mySnake.updateHead(headX, headY);		
		info.remove(0);
		info.remove(0);
		info.remove(0);
		System.out.println("Updated");
	    }
	    else{
		for(int j = 0; j < otherSnake.size()-1; j++){
		    if(id == otherSnake.get(j).getName()){
			otherSnake.get(j).updateHead(headX, headY);
			info.remove(0);
			info.remove(0);
			info.remove(0);
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

    private void makeMySnake(String pos, String pName){
	ArrayList<String> parsed = parser.parse(pos);
	mySnake = new Snake(parsed.get(0).charAt(0), pName, parsed.get(1), parsed.get(2));
    }

    public static String sendName(String name)throws Exception{
	byte[] sendData = new byte[2048];
	byte[] receiveData = new byte[2048];
	int port = 9877;
	InetAddress ip = InetAddress.getByName("192.168.1.89");
	DatagramSocket serverSocket = new DatagramSocket();
	

	sendData = name.getBytes();
	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
	serverSocket.send(sendPacket);

	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	serverSocket.receive(receivePacket);
	String idPos = new String(receivePacket.getData());
	System.out.println("client sendName: " + idPos);
	return idPos;
      	
    }
    
    public static void main(String args[]) throws Exception{
        Client client = new Client();
	Scanner sc = new Scanner(System.in);
	System.out.println("Namn: ");
	String playerName = sc.nextLine();
	
	String idPos = sendName(playerName);
	System.out.println("client main: " + idPos);
	client.makeMySnake(idPos, playerName);
	
        client.initGame(client);
    }
}
