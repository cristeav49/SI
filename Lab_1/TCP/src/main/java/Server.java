import java.io.*;
import java.net.*;

class TCPServer {
    private static final int maximumNrOfMessages = 10;
    private static final String message = "sent another message";
    private static final int interval = 2; // milliseconds

    public static void main(String argv[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6789);
        int counter = 0;

        boolean isStopped = false;
        while (!isStopped) {
            Socket connectionSocket = serverSocket.accept();

            // send data to client
            DataOutputStream outToClient = new DataOutputStream(
                    connectionSocket.getOutputStream());

            System.out.println("send message" + message);
            outToClient.writeBytes(message);
            Thread.sleep(interval * 1000);
            connectionSocket.close();
            counter++;

            if (counter == maximumNrOfMessages) {
                isStopped = true;
            }
        }

        System.out.println("server stopped!");
    }
}
