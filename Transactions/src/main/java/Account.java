import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private long money;
    private String accNumber;
    private volatile boolean isBlocked; // volatile переменная

    public Account() {
        this.money = Accounter.accMoney();
        this.accNumber = Accounter.accNum();
        this.isBlocked = false;
    }

    public long getMoney() {
        return money;
    }

    public void debit(Long amount) {
        money = money - amount;
    }

    public void credit (Long amount) {
        money = money + amount;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

}
