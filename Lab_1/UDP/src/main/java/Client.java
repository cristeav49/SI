import java.io.*;
import java.net.*;

class Client {
    public static void main(String args[]) throws Exception {
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("172.31.224.23");

        // get from user
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//        String sentence = inFromUser.readLine();
//
//        sendData = sentence.getBytes();
//        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
//
//        // send data to server
//        clientSocket.send(sendPacket);

        for (int i = 0; i < 100; i++) {
            String sentence = Integer.toString(i);
            System.out.println("nr = " + sentence);

            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

            // send data to server
            clientSocket.send(sendPacket);
        }

        // get data from server
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}
