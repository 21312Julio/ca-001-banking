package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import constants.OperationType;

public class Transaction {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Date date = new Date();
    private OperationType typeOfOperation;
    private Double amount;
    private Double balance;

    public Transaction(OperationType typeOfOperation, Double amount, Double balance) {
        this.typeOfOperation = typeOfOperation;
        this.amount = amount;
        this.balance = balance;
    }

    public Transaction(String typeOfOperation, String amount, String balance, String date) throws ParseException{
        this.typeOfOperation = OperationType.valueOf(typeOfOperation);
        this.amount = Double.parseDouble(amount);
        this.balance = Double.parseDouble(balance);
        this.date = sdf.parse(date);
    }

    public String toString(){
        return String.format("%s;%s;%s;%s", this.typeOfOperation, this.amount, this.balance, this.date);
    }

    public Date getDate() {
        return date;
    }

    public OperationType getTypeOfOperation() {
        return typeOfOperation;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getBalance() {
        return balance;
    }

}