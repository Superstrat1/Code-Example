import com.opencsv.CSVReader;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {

        Movements movements = new Movements
                ("C:\\Users\\User\\Desktop\\javagit\\java_basics\\FilesAndNetwork\\homework_3\\data\\movementList.csv");
        StringJoiner joiner = new StringJoiner("\n");
        System.out.println("Сумма дохода: " + movements.getIncomeSum());
        System.out.println("Сумма расхода: " + movements.getExpenseSum() + "\n");
        System.out.println("Сумма расходов по организациям:");
        for (String s : movements.getMap().keySet()) {
            joiner.add(s + " - " + movements.getMap().get(s) + " руб.");
        }
        System.out.println(joiner);

    }
}
