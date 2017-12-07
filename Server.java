import java.io.*;
import java.net.*;
import java.util.*;

public class Server{

    
    public static void main(String args[]){
	try{
	    //gör IdHandler som hanterar första paketet klienterna skickar, typ samma som PacketHandler, ge varje klient en unik ID

	    World world = new World();
	    world.addSnake('a', "Shapo");
	    System.out.println("" + world.getSnakes().size());
	    //skapar en ny socket med port 9876
	    //skapar en buffer att ta emot data i
	    int port = 9876;
	    DatagramSocket serverSocket = new DatagramSocket(port);
	    
	    while(true){
		//skapar en buffer
		byte[] receiveData = new byte[2048];

		//skapar ett paketobjekt
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		//väntar till någon skickar ett paket genom socketen
		serverSocket.receive(receivePacket);


		//Anropar konstruktorn för PacketHandler
		//med paketet som är mottaget och socketen
		PacketHandler thread = new PacketHandler(receivePacket, serverSocket, world);
		//kör "run" funktionen i tråden (som en main funktion)
		thread.run();
	    }

        }catch(SocketException e){
	    System.out.println("Socket error");
	}catch(IOException e){
	    System.out.println("IO error");
	}
    }

}
