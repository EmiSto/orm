package swingtest;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JLabel;

public class Snake extends JFrame {
    
    World w;
    playerInput pi;

    public Snake() {

        setSize(800, 800);
        setTitle("Orm");
        setResizable(false);
        setBackground(Color.black);


        this.w = new World(20, 200, 800, 800);
        w.setBackground(Color.black);

        add(this.w);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void bl() {
        System.out.println("BLARAHGH");
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Snake snok = new Snake();
                snok.setVisible(true);
            }
        });
    }

}
