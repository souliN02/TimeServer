import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 2002;

        try (Socket socket = new Socket(host, port);
             Scanner scanner = new Scanner(System.in);
             PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
             Scanner input = new Scanner(socket.getInputStream())) {

            while (true) {
                System.out.print("Press 't' to get the time and date, or 'exit' to exit program: ");
                String userInput = scanner.nextLine();
                output.println(userInput);

                if ("t".equals(userInput)) {
                    String response = input.nextLine();
                    System.out.println("Time and date is: " + response);
                } else if ("exit".equals(userInput)) {
                    System.out.println("Goodbye.");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}