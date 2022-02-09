import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreadedWebServer {

    final ServerSocket serverSock;

    //-------------------------------------------

    public class WebSocket {

        // web socket will always be true as long as its open/connection with it exists
        // while(true) {
            // continue doing something with web socket
        // }

        // DataInputStream wraps a normal InputStream to read bytes & ints

        public void readFully(ArrayList<Byte> anyarray[]) {};

        public void readNBytes() {};

        public void readInt() {};

        public void readShort() {};

        public void readLong() {};

    }


    //-------------------------------------------

    public class Room {

        // add a client (synchronized)

        // send message to all clients in the room (synchronized)

        // remove client from room (synchronized)

        public synchronized static Room getRoom(String name) {
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
                location = readRequest(connectionSocket);

                // creates output data stream
                DataOutputStream outpudding = new DataOutputStream(connectionSocket.getOutputStream());
                System.out.print(location);
                if (!location.equals("/")) {
                    badInput(outpudding);

                } else {
                    makeHeader(outpudding);
                    readsHTML(outpudding);
                }

                Thread.sleep(5000);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
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
            Thread myThreads[] = new Thread[4]; 
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

    static String readRequest(Socket conn) throws IOException {
        InputStreamReader isr =  new InputStreamReader(conn.getInputStream());
        BufferedReader reader = new BufferedReader(isr);

        // splits the GET / HTTP / 1.1
        String line = reader.readLine();
        // TODO print line out and based on the line fetch the file
        
        // Split the first line of the request on " " and get the location at the 1 index
        String location = line.split(" ")[1];
        while (!line.isEmpty()) {
            System.out.println(line);
            line = reader.readLine();
        }

        return location;
    }

    // Reads in HTML file
    static void readsHTML(DataOutputStream output) throws IOException {
        File index = new File("index.html");    // TODO dynamically populate based on user's request/input
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