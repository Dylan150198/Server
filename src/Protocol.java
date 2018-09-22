import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import javax.xml.ws.Response;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Protocol {
    private String isbn;
    private String jsonFromApi = null;

    public Protocol(String isbn) {
        this.isbn = isbn;
    }

    public BookDetail BookInfo() throws IOException {
        URL book = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn); // Pass the desired URL as an object:
        InputStream input = book.openStream(); // Opening a InputStream to the URL
        Reader reader = new InputStreamReader(input, "UTF-8"); // Reading the InputStream
        JsonResult result = new Gson().fromJson(reader, JsonResult.class);  // Parsing the JSON to an object using Gson
        BookDetail bookInfo = new BookDetail(); // Creating a new object and passing along some data for this new object.
        bookInfo.setTitle(result.getBookDetail().getTitle());
        bookInfo.setSubTitle(result.getBookDetail().getSubTitle());
        bookInfo.setAuthors(result.getBookDetail().getAuthors());
        return bookInfo; // Returning the object.
    }
}

