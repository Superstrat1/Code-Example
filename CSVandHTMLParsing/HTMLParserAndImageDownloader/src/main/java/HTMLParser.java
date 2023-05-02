import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

    public class HTMLParser {
        public static String parseFile (String path) {
            StringBuilder builder = new StringBuilder();

            try {
                List<String> lines = Files.readAllLines(Paths.get(path));
                lines.forEach(l -> builder.append(l + "\n"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return builder.toString();
        }
    }

