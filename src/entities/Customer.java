package entities;

import java.util.Collections;
import java.util.List;

public class Customer {

    private String fName;
    private String lName;
    private String email;
    private String saving_file_name;
    private String current_file_name;
    public SavingsAccount savings;
    public CurrentAccount current;

    public Customer(String parameters) {
        String[] output = parameters.split(";");
        this.fName = output[0];
        this.lName = output[1];
        this.email = output[2];

        this.saving_file_name = output[3];
        this.current_file_name = output[4];

        this.current = new CurrentAccount(this.saving_file_name);
        this.savings = new SavingsAccount(this.current_file_name);

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

    public void deleteFiles() {
        new ManageFileTXT(this.saving_file_name).deleteFile();
        new ManageFileTXT(this.current_file_name).deleteFile();
    }

    public void destroy(){
        ManageFileTXT savingReader = new ManageFileTXT(this.saving_file_name);
        ManageFileTXT currentReader = new ManageFileTXT(this.current_file_name);
        List<String> savinglist = new java.util.ArrayList<>(Collections.emptyList());
        List<String> currentlist = new java.util.ArrayList<>(Collections.emptyList());
        for (Transaction transaction: this.savings.tHistory.getTransactions()){
            savinglist.add(transaction.toString());
        }
        for (Transaction transaction: this.current.tHistory.getTransactions()){
            currentlist.add(transaction.toString());
        }
        savingReader.write(savinglist);
        currentReader.write(currentlist);
    }

    public String toString(){
        return String.format("%s;%s;%s;%s;%s", this.fName, this.lName, this.email, this.saving_file_name, this.current_file_name);
    }

}
