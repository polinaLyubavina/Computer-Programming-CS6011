import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.util.regex.*;

public class ThreadedWebServer {

    final ServerSocket serverSock;
    ArrayList<Socket> websockets = new ArrayList<>();

    //-------------------------------------------

    public class WebSocket {
        private void broadcastMessageToClients(byte[] decoded) throws IOException {
            int frameCount  = 0;
            byte[] frame = new byte[10];
        
            frame[0] = (byte) 129;
        
            if(decoded.length <= 125){
                frame[1] = (byte) decoded.length;
                frameCount = 2;
            } else if(decoded.length >= 126 && decoded.length <= 65535) {
                frame[1] = (byte) 126;
                int len = decoded.length;
                frame[2] = (byte)((len >> 8 ) & (byte)255);
                frame[3] = (byte)(len & (byte)255); 
                frameCount = 4;
            } else {
                frame[1] = (byte) 127;
                int len = decoded.length;
                frame[2] = (byte)((len >> 56 ) & (byte)255);
                frame[3] = (byte)((len >> 48 ) & (byte)255);
                frame[4] = (byte)((len >> 40 ) & (byte)255);
                frame[5] = (byte)((len >> 32 ) & (byte)255);
                frame[6] = (byte)((len >> 24 ) & (byte)255);
                frame[7] = (byte)((len >> 16 ) & (byte)255);
                frame[8] = (byte)((len >> 8 ) & (byte)255);
                frame[9] = (byte)(len & (byte)255);
                frameCount = 10;
            }
        
            int bLength = frameCount + decoded.length;
        
            byte[] reply = new byte[bLength];
        
            int bLim = 0;
            for(int i = 0; i < frameCount; i++){
                reply[bLim] = frame[i];
                bLim++;
            }
            for(int i = 0; i < decoded.length; i++){
                reply[bLim] = decoded[i];
                bLim++;
            }
        
            for (Socket client : websockets) {
                System.out.println(client);
                OutputStream out = client.getOutputStream(); // write(byte[] b, int off, int len)
                out.write(reply);
                out.flush();
            }
        }

        // constructor
        public void runWebSocket(Socket client, String data) throws IOException, NoSuchAlgorithmException {
            // Add the socket to the websockets list
            websockets.add(client);
            
            InputStream in = client.getInputStream(); // read(byte[] b, int off, int len)
            OutputStream out = client.getOutputStream(); // write(byte[] b, int off, int len)
            Scanner s = new Scanner(in);
            
            // handshake
            try {
                Matcher get = Pattern.compile("GET").matcher(data);

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

                    byte[] encoded = new byte[1024];
                    int len = 0;
                    while (true) {
                        len = in.read(encoded);
                        if (len != -1) {
                            System.out.println("encoded " + new String(encoded));
                            byte lengthByte = encoded[1];
                            int length = lengthByte & (byte) 127;

                            int indexFirstMask = 2;

                            if (length == 126)
                                indexFirstMask = 4;
                            else if (length == 127)
                                indexFirstMask = 10;

                            int indexFirstDataByte = indexFirstMask + 4;
                            byte[] decoded = new byte[len - indexFirstDataByte];
                            byte[] key = Arrays.copyOfRange(encoded, indexFirstMask, indexFirstMask + 4);

                            for (int i = indexFirstDataByte, j = 0; i < len; i++, j++) {
                                decoded[j] = (byte) (encoded[i] ^ key[j % 4]);
                            }

                            // System.out.println(encoded[1] & 0x000000ffL);
                            System.out.println("decoded " + new String(decoded, StandardCharsets.UTF_8));

                            broadcastMessageToClients(decoded);
                        }
                    }
                }
            } finally {
                s.close();
            }
        }
    }


    //-------------------------------------------

    public class Room {

        // add a client (synchronized)

        // send message to all clients in the room (synchronized)

        // remove client from room (synchronized)

        public synchronized static ThreadedWebServer.Room getRoom(String name) {
            // if room already exists, return it
            // if it doesn't exist, create it and add it to list of rooms, then return the room
        };
    }

    //-------------------------------------------

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            String location;
            try (Socket connectionSocket = serverSock.accept()) {
                Scanner scanIn =  new Scanner(connectionSocket.getInputStream(), "UTF-8");
                String line = scanIn.nextLine();
                String data = scanIn.useDelimiter("\\r\\n\\r\\n").next();

                Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);

                // splits the GET / HTTP / 1.1
                System.out.println(line);
                if (match.find()) {
                    WebSocket ws = new WebSocket();
                    ws.runWebSocket(connectionSocket, line + "\n" + data);
                } else {
                    location = readRequest(line);

                    // creates output data stream
                    DataOutputStream outpudding = new DataOutputStream(connectionSocket.getOutputStream());
                    try {
                        makeHeader(outpudding);
                        readsHTML(outpudding, location);
                    } catch (IOException e2) {
                        badInput(outpudding);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }

    // constructor
    public ThreadedWebServer() throws IOException, InterruptedException {
        serverSock = new ServerSocket(8080);
        System.out.println("Server Starting.");

        while (true) {
            // creates Thread
            Thread myThreads[] = new Thread[128]; 
            for(int t = 0; t < myThreads.length; t++) {
                myThreads[t] = new Thread(new MyRunnable());
                myThreads[t].start(); 
            }

            //for loop to join 
            for(int i = 0; i < myThreads.length; i++) {
                myThreads[i].join(); 
            }
        }
    }

    static String readRequest(String line) throws IOException {
        // Split the first line of the request on " " and get the location at the 1 index
        String location = line.split(" ")[1];

        return location;
    }

    // Reads in HTML file
    static void readsHTML(DataOutputStream output, String location) throws IOException {
        if (location.equals("/")) {
            location = "/index.html";
        }
        File index = new File("Resources" + location);    // TODO dynamically populate based on user's request/input
        Scanner indexReader = new Scanner(index);
        String indexLine;
        while(indexReader.hasNextLine()){
            // move to next line
            indexLine = indexReader.nextLine();
            // converts String to bytes that server can send
            output.write((indexLine + "\n").getBytes()); 
        }
        //closes scanner
        indexReader.close();
    }

    // bad input
    static void badInput(DataOutputStream output) throws IOException {
        output.write("HTTP/1.1 404 Not Found\n".getBytes());
        output.write("\n".getBytes());
        output.write("BAD - Is Not Found.".getBytes());
    }

    // adds Header
    static void makeHeader(DataOutputStream output) throws IOException {
        // converts String to bytes that server can send
        output.write("HTTP/1.1 200 OK\n".getBytes());
        output.write("Content-Type:text/html\n".getBytes());
        // outpudding.write("Content-Length:1024\n".getBytes());
        output.write("Date: Oct 3, 2021".getBytes());
        output.write("\n".getBytes());
        output.write("\n".getBytes());
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        new ThreadedWebServer();
    }
}