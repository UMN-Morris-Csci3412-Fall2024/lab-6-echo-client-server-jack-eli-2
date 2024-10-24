package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Default hostname
        int port = 12345; // Default port

        if (args.length == 1) {
            hostname = args[0];
        } else if (args.length > 1) {
            System.err.println("Usage: java echoserver.EchoClient [hostname]");
            System.exit(1);
        }

        try {
            // Connect to the server
            Socket socket = new Socket(hostname, port);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Read from System.in and send to the server
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = System.in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                outputStream.flush(); // Ensure data is sent immediately

                // Read response from the server
                int responseBytesRead = inputStream.read(buffer);
                System.out.write(buffer, 0, responseBytesRead);
                System.out.flush();
            }

            // Close the connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}