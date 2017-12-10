import java.io.*;
import java.net.*;
import java.util.*;

public class Server{

    public static void main(String args[]){
	try{
	    
	    
	    String ids = "abcd";
	    int port = 9876;
	    int idPort = 9877;
	    
	    DatagramSocket idSocket = new DatagramSocket(idPort);
	    World world = new World();
            world.fruit.respawn();
	    
	    //gör en scanner &skit
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Antal spelare: ");
	    int players = sc.nextInt();
	    
	    for(int i = 0; i < players;i++){
		byte[] receiveDataName = new byte[64];
		DatagramPacket receiveName = new DatagramPacket(receiveDataName, receiveDataName.length);
		idSocket.receive(receiveName);
		
		IDHandler idThread = new IDHandler(receiveName, idSocket, ids.charAt(i), world, players);
		
	    }
	    	   
	    System.out.println("" + world.getSnakes().size());
	    
	    DatagramSocket packetSocket = new DatagramSocket(port);


	    
	    while(true){
		//väntar till någon skickar ett paket genom socketen
		byte[] receiveData = new byte[64];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		packetSocket.receive(receivePacket);

		//Anropar konstruktorn för PacketHandler
		//med paketet som är mottaget och socketen
		PacketHandler thread = new PacketHandler(receivePacket, packetSocket, world);
        
	    }

        }catch(SocketException e){
	    System.out.println("Socket error");
	}catch(IOException e){
	    System.out.println("IO error");
	}
    }

}
