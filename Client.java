import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.lang.Boolean;

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
    }

    private class TAdapter extends KeyAdapter {

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
    }
}
