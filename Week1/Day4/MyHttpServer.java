import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyHttpServer {

    // constructor
    public MyHttpServer() {
        System.out.println("Server Starting.");
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



    public static void main(String[] args) throws IOException {

        MyHttpServer myHttpServer = new MyHttpServer(); 
        final ServerSocket serverSock = new ServerSocket(8080);

        while (true) {
            try (Socket connectionSocket = serverSock.accept()) {

                String location = readRequest(connectionSocket);

                // creates output data stream

                DataOutputStream outpudding = new DataOutputStream(connectionSocket.getOutputStream());
                System.out.print(location);
                if (!location.equals("/")) {
                    badInput(outpudding);

                } else {
                    makeHeader(outpudding);
                    readsHTML(outpudding);
                }
            }
        }
    }
}