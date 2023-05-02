import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static String mainURL;

    public static void main(String[] args) {

        mainURL = new Scanner(System.in).nextLine();

        List<String> list = new ForkJoinPool().invoke(new SiteMapMaker(mainURL));

        FileWriter.fileWriter(FormatterAndStringBuilder.fsb(list));

    }


}
