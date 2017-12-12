
public class Fruit {

    int xFruit = 20;
    int yFruit = 20;
    int fruitHeight = 20;
    int fruitWidth = 20;

    int worldX;
    int worldY;

    public Fruit() {
        this.worldX = 800 / 10 - 4;
        this.worldY = 600 / 10 - 4;
    }

    public Fruit(int x, int y) {

        this.worldX = x / 10 - 2;
        this.worldY = y / 10 - 2;

        respawn();
    }

    public int getxFruit() {
        return this.xFruit;
    }

    public int getyFruit() {
        return this.yFruit;
    }

    public int getWidth() {
        return this.fruitWidth;
    }

    public int getHeight() {
        return this.fruitHeight;
    }

    public void setxFruit(int x) {
        this.xFruit = x;
    }

    public void setyFruit(int y) {
        this.yFruit = y;
    }

    public void respawn() {

        do {
            this.xFruit = (int) (Math.random() * this.worldX) * 10;
        } while (this.xFruit % 20 != 0);

        do {
            this.yFruit = (int) (Math.random() * this.worldY) * 10;
        } while (this.yFruit % 20 != 0);

    }
}
