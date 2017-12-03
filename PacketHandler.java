import java.net.*;
import java.io.*;

//hanterar alla paket som skickas till servern genom trådning
public class PacketHandler implements Runnable{
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    private byte[] sendData;

    //konstruktor
    PacketHandler(DatagramPacket receivePacket, DatagramSocket serverSocket){

	this.serverSocket = serverSocket;
	this.receivePacket = receivePacket;
	this.sendData = new byte[2048];
	System.out.println("creating thread" );
	  
    }
    //kod som alla trådar kör
    public void run(){
	System.out.println("running thread");
	try {
	    //kalla på funktion som uppdaterar gamestate
	    //lägg in det som ska skickas tillbaks till klienten i "msg"
	    String msg = "received msg";
	    this.sendData = msg.getBytes();
	    //Gör om msg till ett DatagramPacket som sedan skickas till klienten genom socketen
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

