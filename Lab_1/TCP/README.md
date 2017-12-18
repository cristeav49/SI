TCP
--
1. send UDP or/and TCP messages
```java

            // send data to client
            DataOutputStream outToClient = new DataOutputStream(
                    connectionSocket.getOutputStream());

            System.out.println("send message" + message);
            outToClient.writeBytes(message);
            Thread.sleep(interval * 1000);
            connectionSocket.close();
            counter++;
```

2. send a message at fixed intervals (every 5s send something, with the possibility to indicate the maximum number of messages) [0.5]
```java
class TCPServer {
    private static final int maximumNrOfMessages = 10;
    private static final String message = "sent another message";
    private static final int interval = 2; // milliseconds
```

3.port scanning of a given IP (with possibility of including a range of ports; ex: 1-100) [1] 

```java
 try {
                Socket clientSocket = new Socket("localhost", 6789);
                // client read from server
                BufferedReader inFromServer = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                fromServer = inFromServer.readLine();
```

```java
 public static void main(String argv[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(12345);
        int counter = 0;
```
Implementation
--

Output of sending message from server to client
<div align="center">
<img src="https://github.com/cristeav49/SI/blob/master/Lab_1/images/1.PNG" width="1000"/>
</div>

Output of receiving message from server 
<div align="center">
<img src="https://github.com/cristeav49/SI/blob/master/Lab_1/images/2.PNG" width="1000"/>
</div>
