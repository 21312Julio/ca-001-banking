package views;

import entities.Customer;
import entities.ManageFileTXT;
import entities.Transaction;
import entities.TransactionHistory;
import constants.AccountType;
import constants.OperationType;

import javax.naming.AuthenticationException;
import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CustomerView{

    private final Scanner sc;
    private List<Customer> list_of_costumers;
    private Customer customer;

    public CustomerView(){
        Locale.setDefault(Locale.US);
        this.sc = new Scanner(System.in);
        ManageFileTXT customerReader = new ManageFileTXT();
        List<String> list = customerReader.fileReader();
        for (String x : list) {
            this.list_of_costumers.add(new Customer(x));
        }
    }

    public Boolean login() throws AuthenticationException {
        System.out.print("Please enter your first name: ");
        String fName = this.sc.nextLine();
        System.out.print("Please enter your last name: ");
        String lName = this.sc.nextLine();
        System.out.print("Please enter your account number: ");
        String accountNumber = this.sc.nextLine();
        System.out.print("Please enter your PIN number: ");
        String pin = this.sc.nextLine();
        for (Customer customer : this.list_of_costumers) {
            if (customer.has_the_same_attributes(fName, lName, pin, accountNumber)) {
                this.customer = customer;
                return true;
            }
        }
        throw new AuthenticationException("Unauthorized - please try again");
    }

    public void main_menu() throws OperationNotSupportedException {
        System.out.println("Do you wish to");
        System.out.println("1)Transaction");
        System.out.println("2)Retrieve History");
        System.out.print("Option: ");
        int option = sc.nextInt();
        if (option == 1){
            this.transaction();
        }
        else if (option == 2){
            this.history();
        }
    }

    public void transaction() {
        System.out.print("Do you wish to access your (1) savings or (0) current account: ");
        int account_type = sc.nextInt();
        System.out.print("Do you wish to (1) withdraw or (0) deposit: ");
        int operation_type = sc.nextInt();
        System.out.print("Amount: ");
        double amount = this.sc.nextDouble();
        if (AccountType.CURRENT.ordinal() == account_type){
            if (OperationType.WITHDRAW.ordinal() == operation_type){
                this.customer.savings.withdraw(amount);
            }
            else if (OperationType.DEPOSIT.ordinal() == operation_type) {
                this.customer.savings.deposit(amount);
            }
        }
        else if (AccountType.SAVING.ordinal() == account_type) {
            if (OperationType.WITHDRAW.ordinal() == operation_type){
                this.customer.current.withdraw(amount);
            }
            else if (OperationType.DEPOSIT.ordinal() == operation_type) {
                this.customer.current.deposit(amount);
            }
        }
    }

    private void history() throws OperationNotSupportedException{
        TransactionHistory content;
        System.out.print("Do you wish to access your (1) savings or (0) current account: ");
        int account_type = sc.nextInt();
        if (AccountType.SAVING.ordinal() == account_type) {
            content = this.customer.savings.tHistory;
        }
        else  if (AccountType.CURRENT.ordinal() == account_type){
            content = this.customer.current.tHistory;
        } else {
            throw new OperationNotSupportedException("Please enter a valid type of account (savings/current).");
        }
        System.out.println("Date ; Operation Type ; Amount ; Total after");
        for (Transaction transaction: content.getTransactions()){
            String converted_transaction = transaction.toString();
            System.out.println(converted_transaction);
        }
    }
}