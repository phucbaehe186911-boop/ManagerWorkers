
package ui;

import controller.Controller;
import entity.SalaryHistory;
import entity.Worker;
import utils.Validator;


public class Main {

    
    public static void main(String[] args) {
        // TODO code application logic here
        Controller control = new Controller();
        do {
            int choice = Validator.getInt("======== Worker Management =========\n"
                    + "1.	Add Worker\n"
                    + "2.	Up salary\n"
                    + "3.	Down salary\n"
                    + "4.	Display Information salary\n"
                    + "5.	Exit\n"
                    + "Enter your choice: ",
                    "Just 1 -> 5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        control.addWorker();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        control.upSalary();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        control.downSalary();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        control.showHistory();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("👋 Exiting program...");
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
