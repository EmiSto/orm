
import java.util.ArrayList;

//c[0] är namn
//c[1] är direction
public class World {

    ArrayList<Snake> snake = new ArrayList();

    public World() {

    }

    public void addSnake(char name, String pName) {
	int startX = 20;
	int startY = 20;
	int noSnakes = snake.size();
	switch (noSnakes) {
	case 0:
	    startX = 20;
	    startY = 20;
	    break;
	case 1:
	    startX = 700;
	    startY = 500;
	    break;
	case 2:
	    startX = 20;
	    startY = 500;
	case 3:
	    startX = 700;
	    startY = 20;
	default:
	    System.out.println("Fel antal snakes");
	    break;
	}
	    
	Snake a = new Snake(name, pName, startX, startY);
        for (int i = 0; i < snake.size(); i++) {
            if (snake.get(i).getName() != name){
                snake.add(a);
		System.out.println("En ny orm");
            }
            else{
                System.out.println(name + " finns redan");
            }
        }
	if(snake.size() == 0){
	    snake.add(a);
	}
    }
    
    //sätter ny riktning och flyttar ormen
    public void updatePosition(char c[]){
        for(int i = 0; i<snake.size(); i++){
            if(snake.get(i).getName() == c[0]){
                snake.get(i).setDirection(c[1]);
                snake.get(i).move();
                break;
            }
        }
    }
    
    public String response(){
	String data = "";
	for(int i = 0; i < snake.size(); i++){
	    data += snake.get(i).getName() + ";";
	    data += snake.get(i).getHead();
	}
	return data;
    }
    
    public ArrayList<Snake> getSnakes(){
        return this.snake;
    }
}
