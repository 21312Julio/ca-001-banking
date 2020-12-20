package views;

import entities.BankEmployee;
import entities.Customer;
import entities.ManageFileTXT;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.naming.AuthenticationException;
import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class EmployeeView {

    private final Scanner sc;
    private List<Customer> list_of_costumers;
    private Customer customer;

    public EmployeeView(){
        Locale.setDefault(Locale.US);
        this.sc = new Scanner(System.in);
        ManageFileTXT customerReader = new ManageFileTXT();
        List<String> list = customerReader.fileReader();
        for (String x : list) {
            this.list_of_costumers.add(new Customer(x));
        }
    }

    public void login () throws AuthenticationException {
        BankEmployee employee = new BankEmployee();
        System.out.print("Please enter your PIN: ");
        String employeePin = sc.nextLine();
        if (employeePin.equals(employee.getPIN())) {
            return True;
        }
        throw new AuthenticationException("Unauthorized - please try again");
    }

    public void main_menu(){
        System.out.println("Do you wish to:");
        System.out.println("1) Create Customer");
        System.out.println("2) Delete Customer");
        System.out.println("3) Create Transaction");
        System.out.println("4) Pull customers list");
        System.out.print("Option: ");
        int option = sc.nextInt();
        if (option == 1){
            this.transaction();
        }
        else if (option == 2){
            this.history();
        }
    }

    private void create_customer() {
        sc.nextLine();
        System.out.print("First name of customer: ");
        String fName = sc.nextLine();
        System.out.print("Last name of customer: ");
        String lName = sc.nextLine();
        System.out.print("Email of customer: ");
        String email = sc.nextLine();
        Customer customer = new Customer(fName, lName, email);
        this.list_of_costumers.add(customer);
    }

    private void delete_customer() {
        System.out.print("First name of customer: ");
        String fName = sc.nextLine();
        System.out.print("Last name of customer: ");
        String lName = sc.nextLine();
        System.out.print("Email of customer: ");
        String email = sc.nextLine();
        Customer customer = new Customer(fName, lName, email);
        this.list_of_costumers.remove(customer);
        customerReader.deleteFiles();
    }

    private void create_transaction() {
        System.out.print("Inform the informations for the chosen customer: ");
        System.out.print("Please enter first name: ");
        String fName = sc.nextLine();
        System.out.print("Please enter last name: ");
        String lName = sc.nextLine();
        System.out.print("Please enter account number: ");
        String accountNumber = sc.nextLine();
        System.out.print("Please enter PIN number: ");
        String pin = sc.nextLine();
        for (Customer customer : this.list_of_costumers) {
            if (customer.has_the_same_attributes(fName, lName, pin, accountNumber)) {
                Customer customerAuth = customer;
                System.out.print("Do you wish to access the customers savings or current account: ");
                String acc = sc.nextLine();
                if (acc.equals("savings")) {
                    System.out.print("Withdraw or Deposit: ");
                    String op = sc.nextLine();
                    if (op.equals("withdraw")) {
                        System.out.print("Amount: ");
                        double amount = sc.nextDouble();
                        customerAuth.savings.withdraw(amount);
                    }
                    if (op.equals("deposit")) {
                        System.out.println("Amount: ");
                        double amount = sc.nextDouble();
                        customerAuth.savings.deposit(amount);
                    }
                } else if (acc.equals("current")) {
                    System.out.print("Withdraw or Deposit: ");
                    String op = sc.nextLine();
                    if (op.equals("withdraw")) {
                        System.out.print("Amount: ");
                        double amount = sc.nextDouble();
                        customerAuth.current.withdraw(amount);
                    }
                    if (op.equals("deposit")) {
                        System.out.println("Amount: ");
                        double amount = sc.nextDouble();
                        customerAuth.current.deposit(amount);
                    }
                } else {
                    throw new OperationException("Please enter valid type of account (savings/current).");
                }
            }
        }
        System.out.println("Some of the informations above are incorrect, sorry.");
    }

    private void print_customers() {

        for (String x : list) {
            System.out.println(x);
        }
    }
}