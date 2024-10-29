package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
    // Define the port number the server will listen on
    public static final int portNumber = 6013;
    
    public static void main(String[] args) {
        try {
            // Start listening on the specified port number
            ServerSocket sock = new ServerSocket(portNumber);

            // Continuously listen for client connections
            while (true) {
                // Accept a client connection
                Socket client = sock.accept();
                System.out.println("Got request!");

                // Get input and output streams for the client socket
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();
                
                int data;
                // Read data from the client and write it back (echo)
                while ((data = input.read()) != -1) {
                    System.out.println("Received byte:" + data);
                    output.write(data);
                }

                // Close the client socket when done
                client.close();
            }
        } catch (IOException ioe) {
            // Handle any unexpected IO exceptions
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}