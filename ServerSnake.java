
import java.util.ArrayList;

public class ServerSnake extends Snake {

    public ServerSnake(char name) {
        this.name = name;
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

        } else if (Down) {

            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            y = y + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Left) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x - moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        } else if (Right) {
            x = this.xBalls.get(nBalls - 1);
            y = this.yBalls.get(nBalls - 1);
            x = x + moveSize;

            this.xBalls.add(x);
            this.yBalls.add(y);

            this.xBalls.remove(0);
            this.yBalls.remove(0);

        }
    }

    public ArrayList<Integer> getSnakeX() {
        return xBalls;
    }

    public ArrayList<Integer> getSnakeY() {
        return yBalls;
    }

    public int getSize() {
        return this.nBalls;
    }

    public void grow() {
        this.nBalls = this.nBalls + 1;

        int x = xBalls.get(0);
        int y = yBalls.get(0);

        xBalls.add(0, x);
        yBalls.add(0, y);
    }
}
