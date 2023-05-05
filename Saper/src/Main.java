
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

    static char filledSquare = (char) 11035;
    static char emptySquare = (char) 11036;
    static char cross = (char) 10006;
    static char[][] field;
    static int[] bombs;
    static Set<Integer> bombMarkCoordinate = new HashSet<>();
    static int width;
    static int length;
    static int difficulties;
    static int bombsOnField;
    static boolean isBlownUp;
    static boolean isWin;
    static int marks;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Задайте ширину поля");
        System.out.println("(Диапазон от 4 до 100)");
        while (true) {
            width = scanner.nextInt();
            if (width < 4 || width > 100) {
                System.out.println("Вы ввели не правильное число");
                System.out.println("(Диапазон от 4 до 100)");
                continue;
            }
            break;
        }
        System.out.println("Задайте длинну поля");
        System.out.println("(Диапазон от 4 до 100)");
        while (true) {
            length= scanner.nextInt();
            if (length < 4 || length > 100) {
                System.out.println("Вы ввели не правильное число");
                System.out.println("(Диапазон от 4 до 100)");
                continue;
            }
            break;
        }

        System.out.println("Укажите сложность");
        System.out.println("Легка - 1");
        System.out.println("Средняя - 2");
        System.out.println("Сложная - 3");
        System.out.println("Очень сложная - 4");
        while (true) {
            difficulties = scanner.nextInt();
            if (difficulties < 1 || difficulties > 4) {
                System.out.println("укажите сложность в диапазоне от 1 до 4:");
                continue;
            }
            break;
        }

        field = fieldCreating(width, length, difficulties);


        while (true) {

            if (isBlownUp) {
                System.out.println("Это мина :(");
                System.out.println("Game over! Х_Х");
                break;
            }

            if ( isWin) {
                System.out.println("Позравляю! Вы нашли все мины.");
                break;
            }
            fieldDisplay();
            int a;
            int b;
            int c;

            System.out.println("Координата по ширине: ");
            System.out.println("(от 1 до " + width + ")");
            while (true) {
                a = scanner.nextInt();
                if (a < 1 || a > width) {
                    System.out.println("Вы ввели неверную координату");
                    System.out.println("Пожалуйста введите верные координаты по ширине");
                    System.out.println("(Диапазон от 1 до " + width + ")");
                    continue;
                }
                a -= 1;
                break;
            }

            System.out.println("Координата по длине: ");
            System.out.println("(от 1 до " + length + ")");
            while (true) {
                b = scanner.nextInt();
                if (b < 1 || b > length) {
                    System.out.println("Вы ввели неверную координату");
                    System.out.println("Пожалуйста введите верные координаты по длине");
                    System.out.println("(Диапазон от 1 до " + length + ")");
                    continue;
                }
                b -= 1;
                break;
            }

            System.out.println("Введите номер действия");
            System.out.println("1 - открыть клетку");
            System.out.println("2 - обозначить флажком за как заминированное");
            System.out.println("3 - убрать флажок");
            while (true) {
                c = scanner.nextInt();
                if (c < 1 || c > 3) {
                    System.out.println("Введите правильный номер действия");
                    System.out.println("1 - открыть клетку");
                    System.out.println("2 - обозначить флажком за как заминированное");
                    System.out.println("3 - убрать флажок");
                    continue;
                }
                break;
            }



            action(a, b, c);
        }



    }

    public static char[][] fieldCreating(int a, int b, int difficulties) {

        bombs = bombRandomGeneration(a, b, difficulties);
        bombsOnField = bombs.length;
        marks = bombs.length;

        char[][] field = new char[a][b];
        for (int i = 0; i < a; i++) {
            for (int y = 0; y < b; y++) {
                field[i][y] = filledSquare;
            }
        }

        return field;
    }

    public static void fieldDisplay() {
        for (int i = 0; i < field.length; i++) {
            for (int y = 0; y < field[i].length; y++) {
                System.out.print(field[i][y]);
            }
            System.out.println();
        }
        System.out.println("Флажков осталось - " + marks);
        System.out.println("======================================================================================================================");
    }

    public static void action(int a, int b, int c) {

        if (c == 1) {
            if (field[a][b] == cross) {
                System.out.println("Вы не можете этого сделать");
            } else {
                int cellCoordinate = coordinate(a, b);
                if (isBomb(cellCoordinate)) {
                    isBlownUp = true;
                } else {
                    whatAround(a, b);
                }
            }
        }
        if (c == 2) {
            if (field[a][b] == filledSquare) {
                field[a][b] = cross;
                marks -= 1;
                bombMarkCoordinate.add(coordinate(a, b));
                bombMarkCheck();
            } else {
                System.out.println("Вы не можете этого сделать");
            }

        }

        if (c == 3) {
            if (field[a][b] == cross) {
                field[a][b] = filledSquare;
                marks += 1;
                bombMarkCoordinate.remove(coordinate(a, b));
            } else {
                System.out.println("Вы не можете этого сделать");
            }

        }
    }

    public static int[] bombRandomGeneration(int a, int b, int difficulties) {
        double difficultiesCoefficient = 0.0;
        if (difficulties == 1) {
            difficultiesCoefficient = 0.1;
        }
        if (difficulties == 2) {
            difficultiesCoefficient = 0.2;
        }
        if (difficulties == 3) {
            difficultiesCoefficient = 0.3;
        }
        if (difficulties == 4) {
            difficultiesCoefficient = 0.5;
        }
        int cellCount = a * b;
        int bombsCount = (int) (cellCount * difficultiesCoefficient);
        int[] bombsCoordinate = new int[bombsCount];
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < cellCount; i++) {
            nums.add(i);
        }

        for (int i = 0; i < bombsCount; ) {
            int n = new Random().nextInt(cellCount);
            if (nums.contains(n)) {
                bombsCoordinate[i] = n;
                nums.remove(n);
                i++;
            }
        }
        return bombsCoordinate;
    }

    public static int coordinate(int a, int b) {
        return (a * length) + b;
    }

    public static boolean isBomb(int c) {
        for (int i : bombs) {
            if (i == c) {
                return true;
            }
        }
        return false;
    }

    public static void whatAround(int a, int b) {
        if (field[a][b] == filledSquare) {
            int bombsAround = bombAround(a, b);
            if (bombsAround == 0) {
                field[a][b] = emptySquare;

                Set<Integer> nextCellCoordinate = nextCellCoordinationSetGeneration(a, b);
                for (int coordinate : nextCellCoordinate) {
                    int firstArrCoordinate = coordinate / length;
                    int secondArrCoordinate = coordinate % length;
                    whatAround(firstArrCoordinate, secondArrCoordinate);
                }
            } else {
                field[a][b] = numberCode(bombsAround);

            }
        }
    }

    public static int bombAround(int a, int b) {
        int bombsCount = 0;
        Set<Integer> nextCellCoordinationSet = nextCellCoordinationSetGeneration(a, b);
        for (int i : bombs) {
            for (int coordinate : nextCellCoordinationSet) {
                if (i == coordinate) {
                    bombsCount++;
                }
            }
        }
        return bombsCount;
    }

    public static Set<Integer> nextCellCoordinationSetGeneration(int a, int b) {
        Set<Integer> set = new HashSet<>();
        if (b - 1 >= 0) {
            set.add(coordinate(a, b - 1));
        }

        if (b + 1 <= field[a].length - 1) {
            set.add(coordinate(a, b + 1));
        }

        if (a - 1 >= 0) {
            if (b - 1 >= 0) {
                set.add(coordinate(a - 1, b - 1));
            }

            if (b + 1 <= field[a].length - 1) {
                set.add(coordinate(a - 1, b + 1));
            }

            set.add(coordinate(a - 1, b));
        }

        if (a + 1 <= field.length - 1) {
            if (b - 1 >= 0) {
                set.add(coordinate(a + 1, b - 1));
            }

            if (b + 1 <= field[a].length - 1) {
                set.add(coordinate(a + 1, b + 1));
            }

            set.add(coordinate(a + 1, b));

        }


        return set;
    }

    public static char numberCode(int number) {
        char code = '\u0000';
        if (number == 1) {
            code = '\u2460';
        }
        if (number == 2) {
            code = '\u2461';
        }
        if (number == 3) {
            code = '\u2462';
        }
        if (number == 4) {
            code = '\u2463';
        }
        if (number == 5) {
            code = '\u2464';
        }
        if (number == 6) {
            code = '\u2465';
        }
        if (number == 7) {
            code = '\u2466';
        }
        if (number == 8) {
            code = '\u2467';
        }
        return code;
    }

    public static void bombMarkCheck() {
        if (marks == 0) {
            int coincidence = 0;
            for (int i : bombs) {
                for (int y : bombMarkCoordinate) {
                    if (i == y) {
                        coincidence++;
                    }
                }
            }
            if (coincidence == bombs.length) {
                isWin = true;
            } else {
                System.out.println("Вы где то ошиблись");
            }
        }
    }
}