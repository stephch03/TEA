package ui;

import model.Employee;
import model.EmployeeDatabase;

import java.util.Scanner;

// Timesheet entry application
// Structure from CPSC 210 TellerApp
public class TimesheetApp {
    private EmployeeDatabase database;
    private Scanner input;

    // EFFECTS: runs the timesheet application
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
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        printTimesheet();
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command in the display menu
    private void processCommand(String command) {
        if (command.equals("ae")) {
            createEmployee();
        } else if (command.equals("d")) {
            removeEmployee();
        } else if (command.equals("n")) {
            changeName();
        } else if (command.equals("ah")) {
            addHours();
        } else if (command.equals("u")) {
            updateHours();
        } else if (command.equals("g")) {
            hoursWorked();
        } else {
            System.out.println("Selection was not recognized, please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes database
    private void init() {
        database = new EmployeeDatabase();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from Main Menu:");
        System.out.println("\tae -> add Employee");
        System.out.println("\td -> delete Employee");
        System.out.println("\tq -> quit and print Timesheet");
        System.out.println("\nOr, select from Employee Menu:");
        System.out.println("\tn -> change Employee's name");
        System.out.println("\tah -> add Employee's new hours");
        System.out.println("\tu -> update Employee's existing hours");
        System.out.println("\tg -> get Employee's hours worked so far");
    }

    // MODIFIES: this
    // EFFECTS: add a new employee to the database
    private void createEmployee() {
        System.out.println("Please enter the name of an employee you'd like to add: FirstName LastName");
        String name = input.next();
        if (database.findEmployee(name) == null) {
            database.addEmployee(name);
            System.out.println(name + " has been added to the database");
        } else {
            System.out.println("Employee already exists...");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an employee from the database
    private void removeEmployee() {
        if (database.getNumberOfEmployees() > 0) {
            printEmployees();
            String name = input.next();
            if (database.findEmployee(name) == null) {
                System.out.println("\nEmployee does not exist...");
            } else {
                database.removeEmployee(name);
                System.out.println("\n" + name + " has been removed from the database");
            }
        } else {
            System.out.println("Please add an employee to your database");
        }
    }

    // EFFECTS: prints timesheet of employee database to the screen
    private void printTimesheet() {
        if (database.getNumberOfEmployees() > 0) {
            System.out.println("Here is your timesheet:\n");
            System.out.println(database.printTimesheet());
        } else {
            System.out.println("Your timesheet is empty");
        }
    }


    // MODIFIES: this
    // EFFECTS: changes the name of an employee
    private void changeName() {
        if (database.getNumberOfEmployees() > 0) {
            printEmployees();
            String name = input.next();
            if (database.findEmployee(name) == null) {
                System.out.println("Employee does not exist...\n");
            } else {
                System.out.print("Enter new name: FirstName LastName\n");
                Employee e = database.findEmployee(name);
                String newName = input.next();
                e.changeName(newName);
                System.out.println(name + " has been changed to " + newName + "\n");
            }
        } else {
            System.out.println("Please add an employee to your database");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds hours worked today to an employee
    private void addHours() {
        if (database.getNumberOfEmployees() > 0) {
            printEmployees();
            String name = input.next();
            if (database.findEmployee(name) == null) {
                System.out.println("Employee does not exist...\n");
            } else {
                Employee e = database.findEmployee(name);
                System.out.print("Enter new hour: Integer\n");
                int hour = input.nextInt();
                if (hour >= 0 && hour <= 8) {
                    e.inputHours(hour);
                    System.out.println(name + " worked " + hour + " hours today \n");
                } else {
                    System.out.println(name + "cannot work " + hour + "hours \n");
                }
            }
        } else {
            System.out.println("Please add an employee to your database");
        }
    }

    //MODIFIES: this
    //EFFECTS: edits employee's hours
    private void updateHours() {
        if (database.getNumberOfEmployees() > 0) {
            printEmployees();
            String name = input.next();
            if (database.findEmployee(name) == null) {
                System.out.println("Employee does not exist...\n");
            } else {
                Employee e = database.findEmployee(name);
                System.out.print("Enter day number: Integer\n");
                int number = input.nextInt();
                System.out.println("Enter new hour: Integer\n");
                int hour = input.nextInt();
                if (hour >= 0 && hour <= 8 && number >= 1 && number <= e.getHours().size()) {
                    e.updateHours(number, hour);
                    System.out.println(name + " worked " + hour + " hours today\n");
                } else {
                    System.out.println(name + " cannot work " + hour + "hours\n");
                }
            }
        } else {
            System.out.println("Please add an employee to your database");
        }
    }

    // EFFECTS: prints out how many hours an employee has worked so far
    private void hoursWorked() {
        if (database.getNumberOfEmployees() > 0) {
            printEmployees();
            String name = input.next();
            if (database.findEmployee(name) == null) {
                System.out.println("Employee does not exist...\n");
            } else {
                Employee e = database.findEmployee(name);
                System.out.println(name + " has worked " + e.getHoursWorked() + " hours so far");
            }
        } else {
            System.out.println("Please add an employee to your database");
        }
    }

    // EFFECTS: prints list of employee names
    private void printEmployees() {
        System.out.println("Please select from the following employees:");
        System.out.println(database.employeeNames());
    }

//    private boolean nonExistentEmployee(String name) {
//        if (database.findEmployee(name) == null) {
//            System.out.println("Employee does not exist...");
//            return true;
//        }
//        return false;
//    }

}