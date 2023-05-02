import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class HTMLParser {

    private static LinkedHashMap<String, LinkedList<String>> mapStation;
    private static LinkedList<LinkedList> listConnectors;
    private static LinkedList<LinkedHashMap<String, String>> listLines;


    public static void htmlParser(String path) {
        Document doc = null;
        try {
            doc = Jsoup.connect(path).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements linesElements = doc.select("[data-depend]");
        Elements stationElements = doc.select("[data-depend-set]");

        mapStation = new LinkedHashMap<>();
        listConnectors = new LinkedList<>();
        listLines = new LinkedList<>();

        linesElements.forEach(e -> {
            String lineName = e.text();
            LinkedHashMap<String, String> tempLineMap = new LinkedHashMap<>();
            tempLineMap.put("number", e.select("span").attr("data-line"));
            tempLineMap.put("name", lineName);
            listLines.add(tempLineMap);
        });

        stationElements.forEach(se -> {
            mapStation.put(se.select("div").attr("data-line"), new LinkedList<>());
            Elements pTagsElements = se.select("p");
            pTagsElements.forEach(p -> {
                mapStation.get(se.select("div").attr("data-line")).add(p.select("[class=name]").text());
                if (!p.select("span").attr("title").isEmpty()) {

                    LinkedList<LinkedHashMap<String, String>> tempListConnectors = new LinkedList<>();
                    LinkedHashMap<String, String> tempMapConnector = new LinkedHashMap<>();
                    tempMapConnector.put("line", se.select("div").attr("data-line"));
                    tempMapConnector.put("station", p.select("[class=name]").text());
                    tempListConnectors.add(tempMapConnector);

                    Elements connectors = p.select("[title]");
                    for (Element e : connectors) {
                        String conLine = e.attr("class");

                        tempMapConnector = new LinkedHashMap<>();
                        tempMapConnector.put("line", conLine.substring(conLine.indexOf("ln-") + 3));
                        tempMapConnector.put("station", connectorSubstringParser(e.attr("title")));
                        tempListConnectors.add(tempMapConnector);

                    }
                    listConnectors.add(tempListConnectors);
                }
            });
        });
    }

    public static LinkedHashMap<String, LinkedList<String>> getMapStation() {
        return mapStation;
    }

    public static LinkedList<LinkedList> getListConnectors() {
        return listConnectors;
    }

    public static LinkedList<LinkedHashMap<String, String>> getListLines() {
        return listLines;
    }

    public static String connectorSubstringParser(String line) {
        int start = line.indexOf('«');
        int end = line.indexOf('»');
        String connector = line.substring(start + 1, end);
        return connector;
    }
}
