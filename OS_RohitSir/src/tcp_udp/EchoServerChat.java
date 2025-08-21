package tcp_udp;
import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServerChat {

    // Store clients as Map<Writer, ClientName>
    private static final Map<PrintWriter, String> clientMap =
        Collections.synchronizedMap(new HashMap<>());

    public static void main(String args[]) {
        int port = 12345;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                try {
                    Socket client = server.accept();
                    String clientName = client.getRemoteSocketAddress().toString();
                    System.out.println("Client connected: " + clientName);

                    Thread t = new Thread(() -> handleClient(client, clientName));
                    t.start();
                } catch (Exception e) {
                    System.out.println("Error while accepting a connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to start server on port " + port);
            e.printStackTrace();
        }
    }

    public static void handleClient(Socket client, String clientName) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        ) {
            // Add this client’s writer and name to the map
            clientMap.put(out, clientName);

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received from " + clientName + ": " + line);

                // Broadcast to all connected clients
                synchronized (clientMap) {
                    for (PrintWriter writer : clientMap.keySet()) {
                        writer.println(clientName + " says: " + line);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Client disconnected: " + clientName);
        } finally {
            closeConnection(client);
        }
    }

    public static void closeConnection(Socket client) {
        try {
            client.close();
        } catch (Exception e) {}
    }
}
