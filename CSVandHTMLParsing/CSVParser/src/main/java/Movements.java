import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {

    private final String pathMovementsCsv;
    private double expenseSum;
    private double incomeSum;
    List<String[]> csvColumns;
    HashMap<String, Double> separateExpenses = new HashMap<>();

    private double exSu;

    public Movements(String pathMovementsCsv) {
        this.pathMovementsCsv = pathMovementsCsv;
        csvParsing();
        for (String[] s : csvColumns) {
            separateExpensesCalculating(s);
        }

    }

    public void csvParsing () {
        try {
            try (CSVReader reader = new CSVReader(new FileReader(pathMovementsCsv))) {
                csvColumns = reader.readAll();
                csvColumns.remove(0);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void separateExpensesCalculating (String[] arr) {
        String expensePath = expensePathParsing(arr[5]);
        String a = arr[6].replaceAll("[,]", ".");
        String b = arr[7].replaceAll("[,]", ".");
        double income = Double.parseDouble(a);
        double expense = Double.parseDouble(b);
        expenseSum = expenseSum + expense;
        incomeSum = incomeSum + income;
        if (expense > 0) {
            if (separateExpenses.containsKey(expensePath)) {
                separateExpenses.put(expensePath, separateExpenses.get(expensePath) + expense);
            } else {
                separateExpenses.put(expensePath, expense);
            }
        }
    }

    public String expensePathParsing (String line) {
        String[] path;
        String expensePath = "";
        String regex = "[0-9]{6}[+]{6}[0-9]{4}";
        String regex2 = "[0-9]{2}[.][0-9]{2}[.][0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Pattern pattern1 = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(line);
        Matcher matcher1 = pattern1.matcher(line);
        while (matcher.find() && matcher1.find()) {
            int start = matcher.end();
            int end = matcher1.start();
            path = line.substring(start, end).trim().split("[/]");
            expensePath = path[path.length - 1].replaceAll("[0-9]", "");
        }
        return expensePath;
    }

    public double getExpenseSum() {
        return expenseSum;
    }

    public double getIncomeSum() {
        return incomeSum;
    }

    public List<String[]> getList () {
        return csvColumns;
    }

    public Map<String, Double> getMap () {
        return separateExpenses;
    }
}
