UDP
--
1. send UDP or/and TCP messages
```java
for (int i = 0; i < 100; i++) {
            String sentence = Integer.toString(i);
            System.out.println("nr = " + sentence);

            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

            // send data to server
            clientSocket.send(sendPacket);
        }
```

2.send file over a network 
```java
public static void main(String args[]) throws Exception {
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("172.31.224.23");

```

3.port scanning of a given IP (with possibility of including a range of ports; ex: 1-100)  

```java
  public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        int port = 0;
```

```java
   public static void main(String args[]) throws Exception {
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("172.31.224.23");

        // get from user
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String sentence = inFromUser.readLine();

        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 80);

        // send data to server
        clientSocket.send(sendPacket);

```

Implementation
--

Output of sending message from server to client
<div align="center">
<img src="https://github.com/cristeav49/SI/blob/master/Lab_1/images/3.PNG" width="1000"/>
</div>

Hello World Main
<div align="center">
<img src="https://github.com/cristeav49/SI/blob/master/Lab_1/images/4.PNG" width="1000"/>
</div>
