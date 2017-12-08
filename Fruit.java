
public class Fruit {

    int xFruit = 10;
    int yFruit = 10;
    int fruitHeight = 20;
    int fruitWidth = 20;
    
    int worldX;
    int worldY;
    

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
    
    public void respawn(){
         while (this.xFruit % 20 != 0) {
            this.xFruit = (int) (Math.random() * this.worldX) * 10;
        }
        while (this.yFruit % 20 != 0) {
            this.yFruit = (int) (Math.random() * this.worldY) * 10;
        }
    }

    public static void main (String [] args){
        Fruit f = new Fruit(800,600);
        for(int i = 0; i < 5; i++){
        f.respawn();
        System.out.println("x pos: " + f.getxFruit());
        System.out.println("y pos: " + f.getyFruit());
        }
    }
}
