import java.net.*;

class EchoServer {
  public static void main(String args[]) throws Exception {
    DatagramSocket d = new DatagramSocket(9090);
    byte[] received = new byte[1024];
    DatagramPacket dp = new DatagramPacket(received, received.length);

    System.out.println("Server listening on port 9090...");

    while (true) {
      d.receive(dp);
      InetAddress ipAddress = dp.getAddress();
      String message = new String(dp.getData(), 0, dp.getLength()).trim();
      System.out.println(ipAddress + " " + message);

      // Exit condition
      if (message.equalsIgnoreCase("exit")) {
        System.out.println("Exit command received. Closing server...");
        break;
       }
     }

     d.close();
     System.out.println("Server stopped.");
  }
}
