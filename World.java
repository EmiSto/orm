
import java.util.ArrayList;

//c[0] är namn
//c[1] är direction
public class World {

    ArrayList<Snake> snake = new ArrayList();

    Fruit fruit = new Fruit(800, 600);
    
    char eater = 'z';
    
    

    public World() {

    }

    public void addSnake(char name, String pName) {
        int startX = 20;
        int startY = 20;
        int noSnakes = snake.size();
        int uNames = noSnakes;
        System.out.println("noSnakes: " + noSnakes);
        switch (noSnakes) {
            case 0:
                startX = 20;
                startY = 20;
                break;
            case 1:
                startX = 20;
                startY = 160;
                break;
            case 2:
                startX = 20;
                startY = 300;
                break;
            case 3:
                startX = 20;
                startY = 440;
                break;
            default:
                System.out.println("Fel antal snakes");
                break;
        }

        Snake a = new Snake(name, pName, startX, startY);
        for (int i = 0; i < snake.size(); i++) {
            if (snake.get(i).getName() != name) {
                uNames -= 1;

            } else {
                System.out.println(name + " finns redan");
            }
        }
        if (uNames == 0) {
            snake.add(a);
            System.out.println("En ny orm");
        }
    }

    //sätter ny riktning och flyttar ormen
    public void updatePosition(char c[]) {
        for (int i = 0; i < snake.size(); i++) {
            if (snake.get(i).getName() == c[0]) {
                snake.get(i).setDirection(c[1]);
                snake.get(i).move();
                break;
            }
        }

        for (int i = 0; i < snake.size(); i++) {
            if (eat(snake.get(i))) {
                fruit.respawn();
                eater = snake.get(i).getName();
                break;
            }
            eater = 'z';
        }

    }

    //Tittar om en orm åt en frukt
    public boolean eat(Snake s) {
        int nBalls = s.getSize();
        int xHead = s.getSnakeX().get(nBalls - 1);
        int yHead = s.getSnakeY().get(nBalls - 1);
        if (xHead == fruit.getxFruit() && yHead == fruit.getyFruit()) {
            return true;
        }
        return false;
    }

    public String response() {
        String data = "";
        //den som åt
        data += this.eater + ";";
        
        //frukten
        data += fruit.getxFruit() + ";";
        data += fruit.getyFruit() + ";";
        
        //ormarna
        for (int i = 0; i < snake.size(); i++) {
            data += snake.get(i).getName() + ";";
            data += snake.get(i).getHead();
        }
        return data;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snake;
    }

    public String getFruit() {
        return "" + fruit.getxFruit() + ";" + fruit.getyFruit() + ";";
    }
}
