package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import constants.OperationType;
import skeleton.Account;

public class SavingsAccount extends Account{

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public SavingsAccount(TransactionHistory tHistory, Double balance) {
        super(tHistory, balance);
    }

    public SavingsAccount(String filename)
    {
        super(filename);
    }

    public void updateAccount() {

        List<Transaction> update = this.tHistory.search(OperationType.UPDATE);
        if (update.isEmpty()) {
            update();
        }
        else {
            Date date = null;
            for (Transaction x : update) {
                if (date == null) {
                    date = x.getDate();
                }
                else if (x.getDate().compareTo(date) > 0) {
                    date = x.getDate();
                }
            }
            Date thisDate = new Date();
            long diffInMiles = Math.abs(thisDate.getTime() - date.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMiles, TimeUnit.MILLISECONDS);
            if (diff > 30) {
                update();
            }
        }
    }

    private void update() {
        double amount = this.balance * 0.001;
        this.balance += amount;
        this.tHistory.add(OperationType.UPDATE, amount, this.balance);
    }
}
