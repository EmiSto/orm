import java.io.*;
import java.net.*;

public class Server{
    
    public static void main(String args[]){
	try{
	    //skapar en ny socket med port 9876
	    //skapar en buffer att ta emot data i
	    DatagramSocket serverSocket = new DatagramSocket(9876);
	    byte[] receiveData = new byte[2048];

	    while(true){
		//skapar ett paketobjekt
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		//väntar till någon skickar ett paket genom socketen
		serverSocket.receive(receivePacket);

		//gör om paketet till en sträng
		String msg = new String(receivePacket.getData());
		System.out.println("received: " +msg);

		//Anropar konstruktorn för PacketHandler
		//med paketet som är mottaget och socketen
		PacketHandler thread = new PacketHandler(receivePacket, serverSocket);
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
