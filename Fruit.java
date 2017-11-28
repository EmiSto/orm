/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingtest;

public class Fruit {
    
    int xFruit;
    int yFruit;
    int fruitHeight = 10;
    int fruitWidth = 10;

    public Fruit(int x, int y) {

        this.xFruit = (int) (Math.random() * (x - x / 2));
        this.yFruit = (int) (Math.random() * (y - y / 2));
    }
    
    public int getxFruit(){
        return this.xFruit;
    }
    
    public int getyFruit(){
        return this.yFruit;
    }
    
    public int getWidth(){
        return this.fruitWidth;
    }
    
    public int getHeight(){
        return this.fruitHeight;
    }

    public void setxFruit(int x){
        this.xFruit = x;
    }
    
    public void setyFruit(int y){
        this.yFruit = y;
    }

}
