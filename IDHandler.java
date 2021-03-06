import java.net.*;
import java.io.*;

//hanterar alla paket som skickas till servern genom trådning
public class IDHandler implements Runnable{
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    Thread t;
    private byte[] sendData;
    private char id;
    private World world;
    private int players;
    
    //konstruktor
    IDHandler(DatagramPacket receivePacket, DatagramSocket serverSocket, char id, World world, int players){

	this.serverSocket = serverSocket;
	this.receivePacket = receivePacket;
	this.sendData = new byte[64];
	this.id = id;
	this.world = world;
	this.players = players;
	t = new Thread(this, "thread");
	
	t.start();
    }
   
    //kod som alla trådar kör
    public void run(){
	
	try {
	    String name = new String(receivePacket.getData());
	    System.out.println("Name: " + name + " Id: " + id);
	    
	    this.world.addSnake(id, name);
	    Snake mySnake;
	    Snake correctSnake;
	    String idPos;
	    int size = this.world.getSnakes().size();
	    for(int s = 0; s < size;s++){
		mySnake = world.getSnakes().get(s);
		if(mySnake.getName() == id){
		    correctSnake = mySnake;
		    idPos = "" + id + ";" + correctSnake.getHead();
		    
		    sendData = idPos.getBytes();
		    break;
		}
	    }
	    int allsnakes = 0;
	    while(this.world.getSnakes().size() != this.players){
		//wtf += 1;
		//System.out.println("antal snakes: " +
		System.out.println("Waiting for other players..." );
		//Thread.sleep(100);
	        //System.out.println("antal snakes: " + this.world.getSnakes().size());
		//do absolutely nothing
	    }
	    
	    DatagramPacket response = new DatagramPacket(this.sendData, this.sendData.length, this.receivePacket.getAddress(), this.receivePacket.getPort());
	    this.serverSocket.send(response);
	    	    
	} catch (SocketException e) {
	    System.out.println("Socket error");
	} catch (IOException e) {
	    System.out.println("IO error");
	} //catch (InterruptedException e) {
	// System.out.println("Interrupt error");
	//}
	
    }
}

