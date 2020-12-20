package views;

import entities.BankEmployee;
import entities.Customer;
import entities.ManageFileTXT;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.naming.AuthenticationException;


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
        String employeePin = this.sc.nextLine();
        if (!employeePin.equals(employee.getPIN())) {
            throw new AuthenticationException("Unauthorized - please try again");
        }
    }

    public void main_menu() throws AuthenticationException {
        System.out.println("Do you wish to:");
        System.out.println("1) Create Customer");
        System.out.println("2) Delete Customer");
        System.out.println("3) Create Transaction");
        System.out.println("4) Pull customers list");
        System.out.print("Option: ");
        int option = this.sc.nextInt();
        if (option == 1){
            this.create_customer();
        }
        else if (option == 2){
            this.delete_customer();
        }
        else if (option == 3){
            this.create_transaction();
        }
        else if (option == 4){
            this.print_customers();
        }
    }

    private void create_customer() {
        System.out.print("First name of customer: ");
        String fName = this.sc.nextLine();
        System.out.print("Last name of customer: ");
        String lName = this.sc.nextLine();
        System.out.print("Email of customer: ");
        String email = this.sc.nextLine();
        Customer customer = new Customer(fName, lName, email);
        this.list_of_costumers.add(customer);
    }

    private void delete_customer() {
        System.out.print("First name of customer: ");
        String fName = this.sc.nextLine();
        System.out.print("Last name of customer: ");
        String lName = this.sc.nextLine();
        System.out.print("Please enter the account number: ");
        String accountNumber = this.sc.nextLine();
        System.out.print("Please enter the PIN number: ");
        String pin = this.sc.nextLine();
        for (Customer customer : this.list_of_costumers) {
            if (customer.has_the_same_attributes(fName, lName, pin, accountNumber)) {
                customer.deleteFiles();
                this.list_of_costumers.remove(customer);
            }
        }
    }

    private void create_transaction() throws AuthenticationException {
        CustomerView cview = new CustomerView();
        cview.login();
        cview.transaction();
    }

    private void print_customers() {

        for (Customer x : this.list_of_costumers) {
            System.out.println(x.getfName());
        }
    }

    public void destroy()
    {
        ManageFileTXT customerReader = new ManageFileTXT();
        List<String> list = new java.util.ArrayList<>(Collections.emptyList());
        for (Customer customer: this.list_of_costumers){
            customer.destroy();
            list.add(customer.toString());
        }
        customerReader.write(list);
    }
}