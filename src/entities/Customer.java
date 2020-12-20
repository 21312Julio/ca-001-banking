package entities;

import entities.CurrentAccount;
import entities.SavingsAccount;

public class Customer {

    private String fName;
    private String lName;
    private String email;
    public SavingsAccount savings;
    public CurrentAccount current;

    public Customer(String parameters) {
        String[] output = parameters.split(";");
        this.fName = output[0];
        this.lName = output[1];
        this.email = output[2];

        this.current = new CurrentAccount(output[3]);
        this.savings = new SavingsAccount(output[3]);

    }

    public Customer(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public Boolean has_the_same_attributes(String fName, String lName, String pin, String accountNumber){
        if (this.fName != fName) {
            return false;
        }
        else if (this.lName != lName) {
            return false;
        }
        else if (pin != this.current.pin) {
            return false;
        }
        else if (accountNumber != this.current.accountNumber) {
            return false;
        }
        return true;

    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SavingsAccount getSavings() {
        return savings;
    }

    public void setSavings(SavingsAccount savings) {
        this.savings = savings;
    }

    public CurrentAccount getCurrent() {
        return current;
    }

    public void setCurrent(CurrentAccount current) {
        this.current = current;
    }



}
