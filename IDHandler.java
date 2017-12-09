import java.net.*;
import java.io.*;

//hanterar alla paket som skickas till servern genom trådning
public class IDHandler implements Runnable{
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    private byte[] sendData;
    private char id;
    private World world;
    //konstruktor
    IDHandler(DatagramPacket receivePacket, DatagramSocket serverSocket, char id, World world){

	this.serverSocket = serverSocket;
	this.receivePacket = receivePacket;
	this.sendData = new byte[2048];
	this.id = id;
	this.world = world;
	System.out.println("creating id-handler thread" );
	  
    }
    //kod som alla trådar kör
    public void run(){
	System.out.println("running ID-handler thread");
	try {
	    String name = new String(receivePacket.getData());
	    System.out.println("Name: " + name + "Id: " + id);
	    
	    world.addSnake(id, name);
	    Snake mySnake;
	    Snake correctSnake;
	    String idPos;
	    int size = this.world.getSnakes().size();
	    for(int s = 0; s < size;s++){
		mySnake = world.getSnakes().get(s);
		if(mySnake.getName() == id){
		    correctSnake = mySnake;
		    idPos = "" + id + ";" + correctSnake.getHead();
		    System.out.println("idhandler: " + idPos);
		    sendData = idPos.getBytes();
		    break;
		}
	    }
	    
	    
	    
	    DatagramPacket response = new DatagramPacket(this.sendData, this.sendData.length, this.receivePacket.getAddress(), this.receivePacket.getPort());
	    this.serverSocket.send(response);
	    	    
	} catch (SocketException e) {
	    System.out.println("Socket error");
	} catch (IOException e) {
	    System.out.println("IO error");
	}
	System.out.println("Thread exiting.");
    }
}

