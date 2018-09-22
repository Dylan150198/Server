import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1355); // Create a socket on the server side.
        Socket ssAccept = ss.accept(); // Accept the incoming request of the client
        Scanner serverScanner = new Scanner(ssAccept.getInputStream()); // Gets the data from the client
        String isbn = serverScanner.nextLine(); // Save the data from the client
        Protocol protocol = new Protocol(isbn); // Creating a new instance of our protocol
        BookDetail myBook = protocol.BookInfo(); // Placing the result of our protocol into a BookDetail object.
        HashMap<Integer, String> bookMap = new HashMap<Integer, String>(); // New HashMap
        bookMap.put(1, myBook.getTitle()); // Placing the title into the hashmap.
        bookMap.put(2, myBook.getSubTitle()); // Placing the subtitle into the hashmap.
        PrintStream printFromServer = new PrintStream(ssAccept.getOutputStream()); // Use a PrintStream to pass data to the client.
        // Finally, send the requested book back to the client
        printFromServer.println("Title: " + bookMap.get(1) + ", Subtitle: " + bookMap.get(2) + ", Auteur(s): " + myBook.getAuthors());


    }
}
