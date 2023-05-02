import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
    public static void fileWriter (StringBuilder stringBuilder) {
        File file = new File("SiteMapProject/data/HTML.html");
        PrintWriter writer;

        try {
            writer = new PrintWriter(file.getPath());
            if (!file.exists()) {
                file.createNewFile();
            }

            writer.write(stringBuilder.toString());

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
