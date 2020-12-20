package entities;

import views.CustomerView;
import views.EmployeeView;

class MainBankProgram {

    public static void main() {
        System.out.println("Welcome to the console based banking application!");
        System.out.print("Are you a 1) customer OR 2) employee: ");
        int n = sc.nextInt();
        if (n == 1) {
            try{
                CustomerView cview = new CustomerView();
                cview.login();
                cview.main_menu();
            }
            catch (Exception) {
                System.out.println("Failed to authenticate - please try again later");
            }
        }
        else if (n == 2) {
            try{
                EmployeeView eview = new CustomerView();
                eview.login();
                eview.main_menu();
            }
            catch (Exception) {
                System.out.println("Failed to authenticate - please try again later");
            }
        }
        else {
            System.out.println("Option invalid - try again");
        }
    }
}
