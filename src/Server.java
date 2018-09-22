import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1342); // Create a socket on the server side.
        Socket ssAccept = ss.accept(); // Accept the incoming request of the client
        Scanner serverScanner = new Scanner(ssAccept.getInputStream()); // Gets the data from the client
        String isbn = serverScanner.nextLine(); // Save the data from the client

        Protocol protocol = new Protocol(isbn);
        Book myBook = protocol.BookInfo();

        PrintStream printFromServer = new PrintStream(ssAccept.getOutputStream()); // Sends data to the client
        printFromServer.println(myBook); // Send the requested book back to the client

    }
}
