package ui;

import model.Employee;
import model.EmployeeDatabase;

import java.util.List;
import java.util.Scanner;

// Timesheet entry application
// Structure from CPSC 210 TellerApp
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
//        } else if (command.equals("m")) {
//            modifyEmployee();
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
//        System.out.println("\te -> Employee information");

        System.out.println("\nOr, select from Employee Menu:");

        System.out.println("\tn -> change Employee's name");
        System.out.println("\tah -> add Employee's new hours");
        System.out.println("\tu -> update Employee's existing hours");
        System.out.println("\tg -> get Employee's hours worked so far");
    }

    // MODIFIES: this
    // EFFECTS: add a new employee to the database
    private void createEmployee() {
        printEmployees();
        String name = input.next();

        if (database.findEmployee(name) == null) {
            database.addEmployee(name);
            System.out.println(name + " has been added to the database\n");
        } else {
            System.out.println("Employee already exists...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an employee from the database
    private void removeEmployee() {
        printEmployees();
        String name = input.next();

        if (database.findEmployee(name) == null) {
            System.out.println("Employee does not exist...\n");
        } else {
            database.removeEmployee(name);
            System.out.println(name + " has been removed from the database\n");
        }
    }

    // EFFECTS: prints timesheet of employee database to the screen
    private void printTimesheet() {
        System.out.println("Here is your timesheet:\n");
        System.out.println(database.printTimesheet());
    }

    // MODIFIES: this
    // EFFECTS: changes the name of an employee
    private void changeName() {
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
    }

    // MODIFIES: this
    // EFFECTS: adds hours worked today to an employee
    private void addHours() {
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
    }

    //MODIFIES: this
    //EFFECTS: edits employee's hours
    private void updateHours() {
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
                System.out.println(name + "cannot work " + hour + "hours\n");
            }
        }
    }

    private void hoursWorked() {
        printEmployees();
        String name = input.next();
        if (database.findEmployee(name) == null) {
            System.out.println("Employee does not exist...\n");
        } else {
            Employee e = database.findEmployee(name);
            System.out.println(name + " has worked " + e.getHoursWorked() + " hours so far\n");
        }
    }

    // EFFECTS: prints list of employee names
    private void printEmployees() {
        System.out.println("Please select from the following employees:\n");
        System.out.println(database.employeeNames());
    }
//TODO loop through a list of existing employees to return name - if contains, return employee, otherwise


    private void nonExistentEmployee(String name) {
        if (database.findEmployee(name) == null) {
            System.out.println("Employee does not exist...\n");
        }
    }

    //    // MODIFIES: this
//    // EFFECTS: conducts a transfer transaction
//    private void modifyEmployee() {
//        boolean keepGoing = true;
//        String command = null;
//
//        while (keepGoing) {
//            displayEmployeeMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("exit")) {
//                keepGoing = false;
//            } else {
//                processEmployeeCommand(command);
//            }
//        }
//        String command;
//        processCommand(command);
//    }
//
//    // EFFECTS: displays Employee menu of options to user
//    //TODO how to return to display menu
//    private void displayEmployeeMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tn -> change Employee's name");
//        System.out.println("\ta -> add Employee's new hours");
//        System.out.println("\tu -> update Employee's existing hours");
//        System.out.println("\tg -> get Employee's hours worked so far");
//        System.out.println("\te -> exit Employee menu");
//    }
//    // MODIFIES: this
//    // EFFECTS: processes user command from the employee menu
//    private void processEmployeeCommand(String command) {
//        if (command.equals("a")) {
//            changeName();
//        } else if (command.equals("g")) {
//            hoursWorked();
//        } else if (command.equals("n")) {
//            addHours();
//        } else if (command.equals("u")) {
//            updateHours();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }

}
