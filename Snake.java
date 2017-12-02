import java.awt.event.ActionListener;

class Snake {

    public int worldX;
    public int worldY;
    public int moveSize = 20;
    public int ballHeight = 20;
    public int ballWidth = 20;
    public int nBalls;
    public ArrayList<Integer> xBalls = new ArrayList<Integer>();
    public ArrayList<Integer> yBalls = new ArrayList<Integer>();

    public Snake(int posX, int posY, int worldX, int worldY){
	this.worldX = worldX;
	this.worldY = worldY;

	this.nBalls = 4;
	this.xBalls.add(posX);
	this.yBalls.add(posY);
	this.xBalls.add(posX + ballHeight);
	this.yBalls.add(posY + ballsWidth);
	this.xBalls.add(posX + 2*ballHeight);
	this.yBalls.add(posY + 2*ballsWidth);
	this.xBalls.add(posX + 3*ballHeight);
	this.yBalls.add(posY + 3*ballsWidth);
    }
    
    public void checkCollision() {
        
        int x = xBalls.get(nBalls-1);
        int y = yBalls.get(nBalls-1);
        for (int i = 0; i < nBalls - 1; i++) {

            if ((xBalls.get(nBalls - 1).equals( xBalls.get(i))) && (yBalls.get(nBalls - 1).equals(  yBalls.get(i)))) {
                System.out.println("Game over");
            }
        }
        if(x >= worldX - ballWidth){
           System.out.println("högervägg");
        }
        if(x < 0 + ballWidth){
            System.out.println("Vänstervägg");
        }
        if(y >= worldY - ballHeight/2){
            System.out.println("golvet");
        }
        if(y < 0 + ballHeight){
            System.out.println("taket");
        }
    }
    
}
