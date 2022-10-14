package ui;

import model.Employee;
import model.EmployeeDatabase;

import java.util.Scanner;

public class TimesheetApp {
// start menu: add employee, edit employee, quit
//    - add next employee
//    - edit employee: given name -> find employee
//       - if employee not found, return "Employee not found" select option again
//       - input hours worked
//       - change name
//       - update hours worked
//       - get hours
//    - reset all employees
//    - remove employee
//    quit: print here is your timesheet, should be option for every input
//            - timesheet include each employee formatted as name: total hours

// Timesheet entry application
    private EmployeeDatabase database;
    private Scanner input;

    // EFFECTS: runs the teller application
    public TimesheetApp() {
        runTimesheet();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTimesheet() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            createEmployee();
        } else if (command.equals("d")) {
            removeEmployee();
        } else if (command.equals("m")) {
            modifyEmployee();
        } else if (command.equals("p")) {
            printTimesheet();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        //TODO
        database = new EmployeeDatabase();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add Employee");
        System.out.println("\td -> delete Employee");
        System.out.println("\te -> edit Employee");
        System.out.println("\tp -> print Timesheet");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add a new employee to the database
    private void createEmployee() {
        System.out.print("Enter name of employee to add: FirstName LastName");
        String name = input.next();

        if (database.findEmployee(name) == null) {
            database.addEmployee(name);
        } else {
            System.out.println("Employee already exists...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an employee from the database
    private void removeEmployee() {
        System.out.print("Enter name of employee to add: FirstName LastName");
        String name = input.next();

        if (database.findEmployee(name) == null) {
            System.out.println("Employee does not exist...\n");
        } else {
            database.removeEmployee(name);
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a transfer transaction
    private void doTransfer() {
        System.out.println("\nTransfer from?");
        Account source = selectAccount();
        System.out.println("Transfer to?");
        Account destination = selectAccount();

        System.out.print("Enter amount to transfer: $");
        double amount = input.nextDouble();

        if (amount < 0.0) {
            System.out.println("Cannot transfer negative amount...\n");
        } else if (source.getBalance() < amount) {
            System.out.println("Insufficient balance on source account...\n");
        } else {
            source.withdraw(amount);
            destination.deposit(amount);
        }

        System.out.print("Source ");
        printBalance(source);
        System.out.print("Destination ");
        printBalance(destination);
    }

    // EFFECTS: prompts user to select an employee and returns it
    private Account selectEmployee() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("c") || selection.equals("s"))) {
            System.out.println("c for chequing");
            System.out.println("s for savings");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("c")) {
            return cheq;
        } else {
            return sav;
        }
    }

    // EFFECTS: prints balance of account to the screen
    private void printBalance(Account selected) {
        System.out.printf("Balance: $%.2f\n", selected.getBalance());
    }
}
