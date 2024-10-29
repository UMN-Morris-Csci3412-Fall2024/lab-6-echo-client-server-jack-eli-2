package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {

    // Define the port number the client will connect to
    public static final int port = 6013;

    public static void main(String[] args) {
        String server;

        // Determine the server address
        if (args.length == 0) {
            server = "127.0.0.1"; // Default to localhost if no arguments are provided
        } else {
            server = args[0]; // Use the provided server address
        }

        try {
            // Connect to the server
            Socket socket = new Socket(server, port);

            // Get input from the user (System.in)
            InputStream userInput = System.in;
            // Get input and output streams for the socket
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            int data;
            // Read from System.in and send to the server
            while ((data = userInput.read()) != -1) {
                outputStream.write(data);
                outputStream.flush(); // Ensure data is sent immediately

                // Read response from the server
                int response = inputStream.read();
                System.out.write(response);
                System.out.flush();
            }

            // Close the connection
            socket.close();
        } catch (IOException e) {
            // Handle any IO exceptions
            e.printStackTrace();
        }
    }
}