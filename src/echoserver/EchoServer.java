package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 12345; // Default port

        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else if (args.length > 1) {
            System.err.println("Usage: java echoserver.EchoServer [port]");
            System.exit(1);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server is running on port " + port + "...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     InputStream inputStream = clientSocket.getInputStream();
                     OutputStream outputStream = clientSocket.getOutputStream()) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        outputStream.flush(); // Ensure data is sent immediately
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}