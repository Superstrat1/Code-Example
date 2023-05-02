import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        Bank bank = new Bank();

        for (int i = 0; i < 300; i++) {
            Account account = new Account();
            bank.addAccount(account);
        }

        System.out.println("сумма до перевода " + bank.getSumAllAccounts());

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                String from = Integer.toString(ThreadLocalRandom.current().nextInt(300));
                String to = Integer.toString(ThreadLocalRandom.current().nextInt(300));
                int sum = ThreadLocalRandom.current().nextInt(58000);
                bank.transfer(from, to, sum);
            }));
        }

        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("сумма после перевода " + bank.getSumAllAccounts());

    }
}
