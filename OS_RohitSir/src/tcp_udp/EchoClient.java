package tcp_udp;
import java.io.*;
import java.util.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String hostServer = "localhost";
        int serverPort = 12345;
        try(Socket socket = new Socket(hostServer, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner scn = new Scanner(System.in);
        ){
            System.out.println("Client connection is made");
            String msg;
            while(true){
                System.out.print("Enter a line to send to the server: ");
                msg = scn.nextLine();
                if("exit".equals(msg)){
                    break;
                }
                out.println(msg); //sends the content to the server
                System.out.println("Server replied back: " + in.readLine());
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }    
}
