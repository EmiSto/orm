
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
    private int speed = 200;
    private int pause = 1000;
    private Parser parser = new Parser();
    //World w;
    // playerInput pi;
    public int worldX = 800;
    public int worldY = 600;

    public Snake mySnake;
    public ArrayList<Snake> otherSnake = new ArrayList();

    Fruit fruit = new Fruit();

    public void paint(Graphics g) {

        super.paint(g);
        int ballWidth = mySnake.getWidth();
        int ballHeight = mySnake.getHeight();

        g.setColor(mySnake.getColor());
        //Om mySnake är död, rita ut game over
        if (mySnake.isDead()) {

            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, worldX / 2 - metr.stringWidth(msg), metr.getHeight());

            g.setColor(Color.gray);
        }

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
        for (int i = 0; i < otherSnake.size(); i++) {

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

        //Ritar frukten
        g.setColor(Color.pink);
        g.fillRect(fruit.getxFruit(), fruit.getyFruit(), fruit.getWidth(), fruit.getHeight());
    }

    public void update() throws Exception {
        sendDirections();
    }

    public void sendDirections() throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        //String ip = "192.168.1.89";
        String ip = "192.168.1.117";
        int port = 9876;
        InetAddress IPAddress = InetAddress.getByName(ip);
        char[] direction = mySnake.getDirection();
        String dirString = "" + direction[0] + direction[1];

        byte[] sendData = new byte[16];
        byte[] receiveData = new byte[64];
        sendData = dirString.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String received = new String(receivePacket.getData());
        System.out.println("Server: " + received);
        addOtherSnakes(received);
        updateSnakes(received);
        mySnake.checkCollision();
        mySnake.checkOtherCollision(otherSnake);
    }

    public void updateSnakes(String snakes) {
        ArrayList<String> info = parser.parse(snakes);
        char id;
        String headX;
        String headY;

        //
        char eater = info.get(0).charAt(0);
        info.remove(0);

        //Uppdaterar Frukten
        int fruitX = Integer.parseInt(info.get(0));
        int fruitY = Integer.parseInt(info.get(1));

        info.remove(0);
        info.remove(0);

        if (fruit.getxFruit() != fruitX && fruit.getyFruit() != fruitY && mySnake.getName() == eater) {
            mySnake.grow();
            fruit.setxFruit(fruitX);
            fruit.setyFruit(fruitY);
        }

        for (int i = 0; i < 1 + otherSnake.size(); i++) {
            id = info.get(0).charAt(0);
            headX = info.get(1);
            headY = info.get(2);
            // System.out.println(id);
            if (id == mySnake.getName()) {
                mySnake.updateHead(headX, headY);
                info.remove(0);
                info.remove(0);
                info.remove(0);
            } else {
                for (int j = 0; j < otherSnake.size(); j++) {
                    if (fruit.getxFruit() != fruitX && fruit.getyFruit() != fruitY && otherSnake.get(j).getName() == eater) {
                        otherSnake.get(j).grow();
                        fruit.setxFruit(fruitX);
                        fruit.setyFruit(fruitY);
                    }
                    if (id == otherSnake.get(j).getName()) {
                        otherSnake.get(j).updateHead(headX, headY);
                        info.remove(0);
                        info.remove(0);
                        info.remove(0);
                    }
                }
            }

        }

    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (mySnake.isDead() == false) {
                update();
            }
            repaint();
            mySnake.checkCollision();
            timer.setInitialDelay(pause);
            timer.start();
        } catch (IOException e1) {
            System.out.println("IOException");

        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("Arrayindex out of obunds");
        } catch (NullPointerException e3) {
            System.out.println("Nullpointer");
        } catch (IndexOutOfBoundsException e4) {
            System.out.println(" Index out of bounds");
        } catch (IllegalStateException e5) {
            System.out.println("Illegal Stat");
        } catch (Exception e6) {
            System.out.println("Exception");
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
        JFrame jf = new JFrame("Orm pre-alpha v0.1");
        setFocusable(true);
        jf.setSize(worldX, worldY + 20);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(c);
        setBackground(Color.black);
        addKeyListener(new TAdapter());
        timer = new Timer(speed, this);
        timer.start();
    }

    private void addOtherSnakes(String snakes) {
        if (otherSnake.size() == 0) {

            ArrayList<String> parsed = parser.parse(snakes);
            char id;
            String headX;
            String headY;
            Snake snake;

            //tar bort ätaren
            parsed.remove(0);

            fruit.setxFruit(Integer.parseInt(parsed.get(0)));
            fruit.setyFruit(Integer.parseInt(parsed.get(1)));
            //tar bort frukten
            parsed.remove(0);
            parsed.remove(0);

            for (int i = 0; i < parsed.size(); i += 3) {

                id = parsed.get(i).charAt(0);
                if (id != mySnake.getName()) {
                    headX = parsed.get(i + 1);
                    headY = parsed.get(i + 2);
                    snake = new Snake(id, "John Doe", headX, headY);

                    switch (id) {
                        case 'a':
                            snake.setColor(Color.GREEN);
                            break;
                        case 'b':
                            snake.setColor(Color.BLUE);
                            break;
                        case 'c':
                            snake.setColor(Color.ORANGE);
                            break;
                        case 'd':
                            snake.setColor(Color.CYAN);
                            break;
                        default:
                            System.out.println("Det blev ingen färg");
                            break;
                    }
                    otherSnake.add(snake);
                }
            }
        }
    }

    private void makeMySnake(String pos, String pName) {
        ArrayList<String> parsed = parser.parse(pos);
        mySnake = new Snake(parsed.get(0).charAt(0), pName, parsed.get(1), parsed.get(2));

        switch (parsed.get(0).charAt(0)) {
            case 'a':
                mySnake.setColor(Color.GREEN);
                break;
            case 'b':
                mySnake.setColor(Color.BLUE);
                break;
            case 'c':
                mySnake.setColor(Color.ORANGE);
                break;
            case 'd':
                mySnake.setColor(Color.CYAN);
                break;
            default:
                System.out.println("Det blev ingen färg");
                break;
        }

    }

    public static String sendName(String name) throws Exception {
        byte[] sendData = new byte[64];
        byte[] receiveData = new byte[64];
        int port = 9877;
        InetAddress ip = InetAddress.getByName("192.168.1.117");
        DatagramSocket serverSocket = new DatagramSocket();

        sendData = name.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
        serverSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        String idPos = new String(receivePacket.getData());

        return idPos;

    }

    public static void main(String args[]) throws Exception {
        Client client = new Client();
        Scanner sc = new Scanner(System.in);
        System.out.println("Namn: ");
        String playerName = sc.nextLine();

        String idPos = sendName(playerName);

        client.makeMySnake(idPos, playerName);

        client.initGame(client);
    }
}
