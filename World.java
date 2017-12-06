
import java.util.ArrayList;

//c[0] är namn
//c[1] är direction
public class World {

    ArrayList<ServerSnake> snake;

    public World() {

    }

    public void addSnake(char name) {
        ServerSnake a = new ServerSnake(name);
        for (int i = 0; i < snake.size() - 1; i++) {
            if (snake.get(i).getName() != name){
                snake.add(a);
            }
            else{
                System.out.println(name + " finns redan");
            }
        }
    }
    
    //sätter ny riktning och flyttar ormen
    public void updatePosition(char c[]){
        for(int i = 0; i<snake.size()-1; i++){
            if(snake.get(i).getName() == c[0]){
                snake.get(i).setDirection(c[1]);
                snake.get(i).move();
                break;
            }
        }
    }
    

    
    public ArrayList<Snake> getSnakes(){
        return this.snake;
    }
}
