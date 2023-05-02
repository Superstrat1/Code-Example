import java.util.Random;

public class Accounter {

    private static int num = 0;

    public static String accNum(){

        String name = Integer.toString(num);
        num = num + 1;
        return name;
    }

    public static Integer accMoney() {
        int[] moneyGrade = new int[]{1000, 10000, 100000};
        Random random = new Random();
        int grade = random.nextInt(3);
        int money = (random.nextInt(10) + 1) * (random.nextInt(moneyGrade[grade]) + moneyGrade[grade]);
        return money;
    }
}