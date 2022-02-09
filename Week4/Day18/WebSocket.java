import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;  
import java.net.*;  


public class WebSocket {

    //output stream
    public OutputStream getWebsocketOutputStream() throws IOException {
        Socket socket = new Socket();  
        InetAddress inetAddress=InetAddress.getByName("localhost");  
        //the port should be greater or equal to 0, else it will throw an error  
        int port=8080;  
        //calling the constructor of the SocketAddress class  
        SocketAddress socketAddress=new InetSocketAddress(inetAddress, port);  
        //binding the  socket with the inetAddress and port number  
        socket.bind(socketAddress);  
        //connect() method connects the specified socket to the server  
        socket.connect(socketAddress);  
        //getOutputStream() returns an output stream for writing bytes to this socket.  
        DataOutputStream  outputStream=new DataOutputStream(socket.getOutputStream());  
        System.out.println("Output Stream: "+outputStream); 
        return outputStream; 
    }

    //input stream
    public InputStream getWebsocketInputStream() throws IOException {
        Socket socket = new Socket();  
        InetAddress inetAddress=InetAddress.getByName("localhost");  
        //the port should be greater or equal to 0, else it will throw an error  
        int port=8080;  
        //calling the constructor of the SocketAddress class  
        SocketAddress socketAddress=new InetSocketAddress(inetAddress, port);  
        //binding the  socket with the inetAddress and port number  
        socket.bind(socketAddress);  
        //connect() method connects the specified socket to the server  
        socket.connect(socketAddress);  
        DataInputStream inputStream=new DataInputStream(  
        new BufferedInputStream(socket.getInputStream()));  
        System.out.println("Input Stream: "+inputStream);   
        return inputStream;
    }


    // constructor
    public static void runWebSocket() throws IOException, NoSuchAlgorithmException {

        // instantiate serversocket 
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server has started on 127.0.0.1:8080.\r\nWaiting for a connection...");
		
        while (true) {
                Socket client;
            try {
                client = server.accept();
                System.out.println("A client connected.");
            } finally {

            }

            InputStream in = client.getInputStream(); // read(byte[] b, int off, int len)
            OutputStream out = client.getOutputStream(); // write(byte[] b, int off, int len)
            Scanner s = new Scanner(in, "UTF-8");

            // handshake
            try {
                String data = s.useDelimiter("\\r\\n\\r\\n").next();
                Matcher get = Pattern.compile("^GET").matcher(data);

                if (get.find()) {
                    Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
                    match.find();
                    byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n"
                        + "Connection: Upgrade\r\n"
                        + "Upgrade: websocket\r\n"
                        + "Sec-WebSocket-Accept: "
                        + Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-1").digest((match.group(1) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("UTF-8")))
                        + "\r\n\r\n").getBytes("UTF-8");
                    out.write(response, 0, response.length);
        
                    byte[] decoded = new byte[6];
                    byte[] encoded = new byte[] { (byte) 198, (byte) 131, (byte) 130, (byte) 182, (byte) 194, (byte) 135 };
                    byte[] key = new byte[] { (byte) 167, (byte) 225, (byte) 225, (byte) 210 };
                    for (int i = 0; i < encoded.length; i++) {
                        decoded[i] = (byte) (encoded[i] ^ key[i & 0x3]);
                        }
                }
            } finally {
                s.close();
            }
        }
    }


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        runWebSocket(); 
    }
}
