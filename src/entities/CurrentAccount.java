package entities;

import skeleton.Account;
import entities.TransactionHistory;

public class CurrentAccount extends Account{

    public CurrentAccount(TransactionHistory tHistory, Double balance) {
        super(tHistory, balance);
    }

    public CurrentAccount(String filename)
    {
        super(filename);
    }

}
