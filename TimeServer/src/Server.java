import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        int port = 2002;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is ready for connections...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Connection from " + clientSocket.getInetAddress());

                    Scanner input = new Scanner(clientSocket.getInputStream());
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                    String request = input.nextLine();
                    if ("t".equals(request)) {
                        String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
                        output.println(currentTime);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}