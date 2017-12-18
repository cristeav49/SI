import java.io.*;
import java.net.*;

/**
 * Usage:input a string, server will return a value.
 **/

class TCPClient {
    public static void main(String argv[]) {
        String fromServer = "";

        boolean isStopped = false;
        while (!isStopped) {
            try {
                Socket clientSocket = new Socket("localhost", 6789);
                // client read from server
                BufferedReader inFromServer = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                fromServer = inFromServer.readLine();

                System.out.println("FROM SERVER: " + fromServer);

                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Connection stopped");
                break;
            }

        }
    }
}
