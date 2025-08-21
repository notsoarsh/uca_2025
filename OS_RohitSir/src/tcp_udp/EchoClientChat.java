package tcp_udp;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClientChat {
    public static void main(String[] args) {
        String hostServer = "localhost";
        int serverPort = 12345;
        try (
            Socket socket = new Socket(hostServer, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scn = new Scanner(System.in);
        ) {
            System.out.println("Client connection is made");

            // Thread to listen for server messages
            Thread listener = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println("\n[Server Broadcast] " + response);
                        System.out.print("Enter a line to send: ");
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            listener.start();

            // Main thread sends user input
            String msg;
            while (true) {
                System.out.print("Enter a line to send: ");
                msg = scn.nextLine();
                if ("exit".equals(msg)) {
                    break;
                }
                out.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
