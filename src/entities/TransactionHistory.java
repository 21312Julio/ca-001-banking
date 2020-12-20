package entities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import entities.Transaction;
import constants.OperationType;

public class TransactionHistory {

    private final List<Transaction> transactionHistory = new ArrayList<>();

    public TransactionHistory(){

    }

    public TransactionHistory(List<String> output2) {
        for (String transaction : output2) {
            String [] output = transaction.split(";");
            try {
                this.transactionHistory.add(new Transaction(output[0], output[1], output[2], output[3]));
            } catch (ParseException e) {
                System.out.println("Invalid file - please check the Account database and try again");
            }
        }
    }

    public void add(OperationType typeOfOperation, double amount, double balance) {
        Transaction transaction = new Transaction(typeOfOperation, amount, balance);
        this.transactionHistory.add(transaction);
    }

    public List<Transaction> search(OperationType typeOfOperation) {

        return this.transactionHistory.stream()
                .filter(x -> x.getTypeOfOperation() == typeOfOperation)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactions(){
        return this.transactionHistory;
    }

}


