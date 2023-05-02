import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String srcFolder = "/Users/User/Desktop/src";
        String dstFolder = "/Users/User/Desktop/dst";
        int newWidth = 300;
        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();

        ArrayList <ImageResizer> resizerList = new ArrayList<>();

        FilesArrayDivider.divider(files).forEach(arr -> resizerList.add(new ImageResizer(arr, dstFolder,newWidth, start)));

        resizerList.forEach(l -> l.start());
    }
}
