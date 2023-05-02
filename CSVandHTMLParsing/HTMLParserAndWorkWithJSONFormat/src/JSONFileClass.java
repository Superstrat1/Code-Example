import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JSONFileClass {

    private static JSONObject totalMetroMapJSON;

    public static void jsonFileWorkMethod () {
        totalMetroMapJSON = new JSONObject();

        JSONObject mapStationJSON = new JSONObject();
        JSONArray arrayConnectorsJSON = new JSONArray();
        JSONArray arrayLinesJSON = new JSONArray();

        mapStationJSON.putAll(HTMLParser.getMapStation());

        arrayConnectorsJSON.addAll(HTMLParser.getListConnectors());

        arrayLinesJSON.addAll(HTMLParser.getListLines());

        totalMetroMapJSON.put("stations", mapStationJSON);
        totalMetroMapJSON.put("connections", arrayConnectorsJSON);
        totalMetroMapJSON.put("lines", arrayLinesJSON);
    }

    public static JSONObject getTotalMetroMapJSON() {
        return totalMetroMapJSON;
    }

    public static void jsonWriter(Path path, Object object) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            PrintWriter printWriter = new PrintWriter(path.toString());
            printWriter.write(gson.toJson(object));
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printStationCount(Path path) {

        JSONObject jsonObject = (JSONObject) JSONValue.parse(parser(path.toString()));
        JSONObject stations = (JSONObject) jsonObject.get("stations");
        for (Object o : stations.keySet()) {
            List list = (List) stations.get(o.toString());
            System.out.println(o + " - " + list.size() + " станций");
        }
    }

    public static String parser(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(l -> builder.append(l + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
