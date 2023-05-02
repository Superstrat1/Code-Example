import java.util.Collections;
import java.util.List;

public class FormatterAndStringBuilder {
    public static StringBuilder fsb (List<String> list) {
        List<String> sortedList = list;
        Collections.sort(sortedList);
        StringBuilder builder = new StringBuilder();
        for (String s : sortedList) {
            String tabs = "";
            int indent = s.split("/").length - Main.mainURL.split("/").length;
            for (int i = 0; i < indent; i++) {
                tabs = tabs + "\t";
            }
            builder.append(tabs + s).append("\n");
        }

        return builder;
    }
}
