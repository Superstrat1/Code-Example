import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageDownloader {
    private static InputStream inputStream = null;
    private static FileOutputStream outputStream = null;
    private static URL url = null;
    private static List<String> imageLinks = new ArrayList<>();

    public static void downloadImage(String parsedFile) {
        Document newDoc = Jsoup.parse(parsedFile);
        Elements link = newDoc.select("img");
        link.forEach(l -> imageLinks.add(l.attr("abs:src")));
        for (String path : imageLinks) {
            if(path.isEmpty()) {
                continue;
            }
            try {
                url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    inputStream = connection.getInputStream();
                    String[] fileName = path.split("/");
                    File file = new File("image\\" + fileName[fileName.length - 1]);
                    outputStream = new FileOutputStream(file);
                    int byteRead = -1;
                    byte[] buffer = new byte[1024];
                    while ((byteRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, byteRead);
                    }
                }
            } catch (
                    IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}