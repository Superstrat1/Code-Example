import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.RecursiveTask;

public class SiteMapMaker extends RecursiveTask<Vector<String>> {

    private String url;
    private static Vector<String> allLinks = new Vector<>();
    private String mainURL = Main.mainURL;
    private String reg = "[/][\\w\\W]+[/]";
    private HashSet<String> pageLinks = new HashSet<>();

    public SiteMapMaker(String url) {
        this.url = url;
    }

    @Override
    protected Vector<String> compute() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<SiteMapMaker> taskList = new ArrayList<>();

        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Elements elements = doc.select("a");

        for (Element e : elements) {
            String HTMLElement = e.attr("href");
            String newURL;

            if (HTMLElement.matches(reg)) {
                newURL = mainURL + HTMLElement;
            } else if (urlTrueFormatCheck(HTMLElement)) {
                    newURL = HTMLElement;
                } else {
                continue;
            }

            if (allLinks.contains(newURL)) {
                continue;
            }
            allLinks.add(newURL);
            pageLinks.add(newURL);
        }

        for (String s : pageLinks) {
            SiteMapMaker task = new SiteMapMaker(s);
            task.fork();
            taskList.add(task);
        }

        for (SiteMapMaker siteMapMaker : taskList) {
            try {
                siteMapMaker.join();
            } catch (NullPointerException exception) {
                exception.printStackTrace();
            }
        }
        return allLinks;
    }

    private boolean urlTrueFormatCheck (String line) {
        if (line.contains(mainURL)
                && !line.contains(".pdf")
                && !line.contains("#")
                && !line.contains(".png")
                && !line.contains(".jpg")
                && line.indexOf(mainURL) == 0
                && !line.contains("?")) {
            return true;
        } else {
            return false;
        }

    }
}
