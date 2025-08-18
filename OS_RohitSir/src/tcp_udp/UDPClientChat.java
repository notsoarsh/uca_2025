package tcp_udp;
import java.net.*;
import java.util.*;

public class UDPClientChat {
    private static final String host = "localhost";
    private static final int port =  12345;

    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress servAddress = InetAddress.getByName(host);

            //Listening to server for messages
            Thread listener = new Thread(() -> {
                try{
                    byte[] buffer = new byte[1024];
                    while(true){
                        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                        socket.receive(packet);
                        String msg = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("\n [BroadCasted] : " + msg);
                        System.out.println("Enter a message: ");
                    }
                }catch(Exception e){
                    System.out.println("Disconnected from server");
                }
            });
            listener.start();

            //Main thread 
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("Enter a message: ");
                String msg = sc.nextLine();
                if(msg.equalsIgnoreCase("exit")) break;

                byte[] data = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, servAddress, port);
                socket.send(packet);
            }
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}