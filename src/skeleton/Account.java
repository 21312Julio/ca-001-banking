package skeleton;

import entities.ManageFileTXT;
import entities.TransactionHistory;

import java.util.List;

public class Account {

    public TransactionHistory tHistory;
    public Double balance;
    public String accountNumber;
    public String pin;

    public Account(String filename)
    {
        ManageFileTXT account = new ManageFileTXT(filename);
        List<String> output = account.fileReader();
        String description = output.remove(0);
        this.tHistory = new TransactionHistory(output);

        this.accountNumber = output.get(0);
        this.pin = output.get(1);
        this.balance = Double.parseDouble(output.get(2));
    }

    public Account(TransactionHistory tHistory, Double balance) {
        this.tHistory = tHistory;
        this.balance = balance;
    }

    public TransactionHistory gettHistory() {
        return tHistory;
    }

    public Double getBalance() {
        return balance;
    }

    public void Balance(Double balance) {
        this.balance = balance;
    }

    public void createPIN(String fName, String lName) {
        char f1 = fName.charAt(0);
        char f2 = lName.charAt(0);
        String p1 = String.valueOf(Character.toUpperCase(f1) - 64);
        String p2 = String.valueOf(Character.toUpperCase(f2) - 64);
        this.pin = p1+p2;
    }

    public void createAccountNumber(String fName, String lName) {
        char f1 = fName.charAt(0);
        char f2 = lName.charAt(0);
        String length = String.valueOf(fName.length() + lName.length());
        this.accountNumber = f1 + f2 + "-" + length + "-" + this.pin;
    }

    public void deposit(double amount) {
        this.balance -= amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public String toString(){
        return String.format("%s;%s;%s", this.accountNumber, this.pin, this.balance);
    }

}
