
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientSnake extends Snake{



    public void checkCollision() {

        int x = xBalls.get(nBalls - 1);
        int y = yBalls.get(nBalls - 1);
        for (int i = 0; i < nBalls - 1; i++) {

            if ((xBalls.get(nBalls - 1).equals(xBalls.get(i))) && (yBalls.get(nBalls - 1).equals(yBalls.get(i)))) {
                System.out.println("Game over");
            }
        }
        if (x >= worldX - ballWidth) {
            System.out.println("högervägg");
        }
        if (x < 0 + ballWidth) {
            System.out.println("Vänstervägg");
        }
        if (y >= worldY - ballHeight / 2) {
            System.out.println("golvet");
        }
        if (y < 0 + ballHeight) {
            System.out.println("taket");
        }
    }

    
}
