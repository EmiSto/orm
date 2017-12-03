
import java.util.ArrayList;

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
    
    public ArrayList<ServerSnake> getSnakes(){
        return this.snake;
    }
}
