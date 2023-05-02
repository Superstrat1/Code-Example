import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private final String statusMessageConformed = "одобрено";
    private final String statusMessageNotEnoughMoney = "на счете недостаточно средств";
    private final String statusMessageAccountBanned = "счет заблокирован. операция прервана";

    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */

    public void addAccount(Account account) {
        accounts.put(account.getAccNumber(), account);
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

        String statusMessage = checkStatus(fromAccountNum, toAccountNum, amount);
        if (statusMessage.equals(statusMessageConformed)) {
            System.out.println("перевод от " + fromAccountNum + " на " + toAccountNum + " суммой " + amount);
            confirmedTransfer(fromAccountNum, toAccountNum, amount);
            securityCheck(fromAccountNum, toAccountNum, amount);
        } else {
            System.out.println(statusMessage);
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */

    private void confirmedTransfer(String fromAccountNum, String toAccountNum, long amount) {
        int val = accounts.get(fromAccountNum).getAccNumber().compareTo(accounts.get(toAccountNum).getAccNumber());
        if (val < 0) {
            synchronized (accounts.get(fromAccountNum)) {
                synchronized (accounts.get(toAccountNum)) {
                    accounts.get(fromAccountNum).debit(amount);
                    accounts.get(toAccountNum).credit(amount);
                }
            }
        } else {
            synchronized (accounts.get(toAccountNum)) {
                synchronized (accounts.get(fromAccountNum)) {
                    accounts.get(fromAccountNum).debit(amount);
                    accounts.get(toAccountNum).credit(amount);
                }
            }
        }
    }

    private String checkStatus(String fromAccountNum, String toAccountNum, long amount) {
        if (accounts.get(fromAccountNum).isBlocked() || accounts.get(toAccountNum).isBlocked()) {
            return statusMessageAccountBanned;
        } else if (accounts.get(fromAccountNum).getMoney() < amount) {
            return statusMessageNotEnoughMoney;
        } else {
            return statusMessageConformed;
        }
    }

    private void securityCheck(String fromAccountNum, String toAccountNum, long amount) {
        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accounts.get(fromAccountNum).setBlocked(true);
                    accounts.get(toAccountNum).setBlocked(true);
                    System.out.println("Счета заблокированы");
                } else {
                    System.out.println("Счета не заблокированы");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;
        for (Account account : accounts.values()) {
            sum = sum + account.getMoney();
        }
        return sum;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }
}
