import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Protocol
{
    private String isbn;
    private String jsonFromApi = null;
    public Protocol(String isbn)
    {
        this.isbn = isbn;
    }
    public Book BookInfo() throws IOException {
        // Pass the desired URL as an object:
        URL bookInfo = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn);
        // https://www.googleapis.com/books/v1/volumes?q=isbn?=9789043026970
        // Type cast the URL object into a HttpURLConnection object.
        HttpURLConnection conn = (HttpURLConnection) bookInfo.openConnection();
        // Set the request type
        conn.setRequestMethod("GET");
        // Open a connection stream
        conn.connect();
        // Get the corresponding response code
        int responsecode = conn.getResponseCode();
        // Now we need to perform a check so that if the response code is not 200, we throw a runtime exception
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " +responsecode);
        }
        else
        {
            // Scanner to read each line from the API
            Scanner scApi = new Scanner(bookInfo.openStream());
            while (scApi.hasNext())
            {
                jsonFromApi += scApi.nextLine();
            }
            System.out.println(jsonFromApi);
            scApi.close();
        }
        System.out.println(jsonFromApi);
        // JSON reading using GSON
        JsonReader jsonReader = new JsonReader(new StringReader(jsonFromApi)); // Creating a GSON JsonReader

        return new Book();
        }
    }

