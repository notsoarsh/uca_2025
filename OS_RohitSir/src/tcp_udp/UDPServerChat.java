package tcp_udp;
import java.net.*;
import java.util.*;

public class UDPServerChat{
    private static final int port = 12345;
    //A set to keep track of the broadcast messages
    private static final  Set<InetSocketAddress> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args){
        try(DatagramSocket server = new DatagramSocket(port)){
            System.out.println("UDP server started on port: " + port);

            //byte array to store the buffer sent by os
            byte[] buffer = new byte[1024];  //here we keep msg limit to 1kb 
            while(true){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                server.receive(packet);

                String msg = new String(packet.getData(),0,packet.getLength()); //creating a message for the packet
                InetSocketAddress clientAdd = (InetSocketAddress)packet.getSocketAddress();

                //adding the client to set
                clients.add(clientAdd);
                System.out.println("Received from "+ clientAdd + ": " + msg);

                //broadcast this to all clients
                synchronized(clients){
                    for(InetSocketAddress addr: clients){
                        String bcastMsg = clientAdd + " says: " + msg;
                        byte[] data = bcastMsg.getBytes();
                        DatagramPacket sendToAll = new DatagramPacket(data, data.length, addr.getAddress(), addr.getPort());
                        server.send(sendToAll);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}