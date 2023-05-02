import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) {

        HTMLParser.htmlParser("https://skillbox-java.github.io");

        JSONFileClass.jsonFileWorkMethod();

        JSONFileClass.jsonWriter(Paths.get("data/MoscowMetroMap.json"), JSONFileClass.getTotalMetroMapJSON());

        JSONFileClass.printStationCount(Paths.get("data/MoscowMetroMap.json"));
    }

}