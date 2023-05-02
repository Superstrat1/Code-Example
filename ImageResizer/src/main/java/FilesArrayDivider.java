import org.w3c.dom.ls.LSOutput;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesArrayDivider {
    public static ArrayList<File[]> divider(File[] arr) {
        int processorsCount = Runtime.getRuntime().availableProcessors();
        ArrayList<File[]> dividedArray = new ArrayList<>();
        File[] newArr;

        int srcPos = 0;
        int remainder = arr.length % processorsCount;

        for (int i = 0; i < processorsCount; i++) {
            if (remainder > 0) {
                newArr = new File[arr.length / processorsCount + 1];
                remainder -= 1;
            } else {
                newArr = new File[arr.length / processorsCount];
            }

            System.arraycopy(arr, srcPos, newArr, 0, newArr.length);

            dividedArray.add(newArr);

            srcPos = srcPos + newArr.length;
        }
        return dividedArray;
    }
}
