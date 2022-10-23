package ui;

import model.Employee;
import model.EmployeeDatabase;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Timesheet entry application
// Structure from CPSC 210 TellerApp
public class TimesheetApp {
    private static final String JSON_STORE = "./data/employeeDatabase.json";
    private EmployeeDatabase database;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the timesheet application
    public TimesheetApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTimesheet();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTimesheet() {
        boolean keepGoing = true;
        String command = null;
        init();
        System.out.println("\nHello!");
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
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command in the display menu
    private void processCommand(String command) {
        if (command.equals("d")) {
            changeDate();
        } else if (command.equals("ae")) {
            createEmployee();
        } else if (command.equals("r")) {
            removeEmployee();
        } else if (command.equals("reset")) {
            database.reset();
        } else if (command.equals("s")) {
            saveDatabase();
        } else if (command.equals("l")) {
            loadDatabase();
        } else if (command.equals("p")) {
            printTimesheet();
        } else {
            processEmployeeCommand(command);
        }
    }

    private void processEmployeeCommand(String command) {
        if (command.equals("n")) {
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
        database = new EmployeeDatabase("Undated");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from Main Menu:");
        System.out.println("\td -> change date of Timesheet");
        System.out.println("\tae -> add Employee");
        System.out.println("\tr -> remove Employee");
        System.out.println("\treset -> clear all employee's hours");
        System.out.println("\tl -> load Timesheet");
        System.out.println("\ts -> save Timesheet");
        System.out.println("\tp -> print Timesheet");
        System.out.println("\tq -> quit (remember to save)");
        System.out.println("\nOr, select from Employee Menu:");
        System.out.println("\tn -> change Employee's name");
        System.out.println("\tah -> add Employee's new hours");
        System.out.println("\tu -> update Employee's existing hours");
        System.out.println("\tg -> get Employee's hours worked so far");
    }

    // REQUIRES: format of date must be MM-DD-YYYY
    // MODIFIES: this
    // EFFECTS: changes the date of timesheet
    private void changeDate() {
        System.out.println("Please enter the starting date for this timesheet: MM-DD-YYYY");
        String date = input.next();
        database.changeDate(date);
    }

    // MODIFIES: this
    // EFFECTS: add a new employee to the database
    private void createEmployee() {
        System.out.println("Please enter the name of an employee you'd like to add: FirstName LastName");
        String name = input.next();
        if (database.findEmployee(name) == null) {
            database.addEmployee(new Employee(name));
            System.out.println(name + " has been added to the database");
        } else {
            System.out.println("Employee already exists...");
        }
    }

    // REQUIRES: employee exists
    // MODIFIES: this
    // EFFECTS: removes an employee from the database
    private void removeEmployee() {
        if (nonEmptyDatabase()) {
            printEmployees();
            String name = input.next();
            if (employeeExists(name)) {
                database.removeEmployee(name);
                System.out.println("\n" + name + " has been removed from the database");
            }
        }
    }

    private void saveDatabase() {
        try {
            jsonWriter.open();
            jsonWriter.write(database);
            jsonWriter.close();
            System.out.println("Saved " + database.getDate() + " timesheet to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadDatabase() {
        try {
            database = jsonReader.read();
            System.out.println("Loaded " + database.getDate() + " timesheet from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints timesheet of employee database to the screen
    private void printTimesheet() {
        if (database.getNumberOfEmployees() > 0) {
            System.out.println("Here is your " + database.getDate() + " timesheet:\n");
            System.out.println(database.printTimesheet());
        } else {
            System.out.println("Your timesheet is empty");
        }
    }

    // REQUIRES: employee exists
    // MODIFIES: this
    // EFFECTS: changes the name of an employee
    private void changeName() {
        if (nonEmptyDatabase()) {
            printEmployees();
            String name = input.next();
            if (employeeExists(name)) {
                System.out.print("Enter new name: FirstName LastName\n");
                String newName = input.next();
                database.findEmployee(name).changeName(newName);
                System.out.println(name + " has been changed to " + newName + "\n");
            }
        }
    }

    // REQUIRES: employee exists, 0 <= hour <= 8
    // MODIFIES: this
    // EFFECTS: adds hours worked today to an employee
    private void addHours() {
        if (nonEmptyDatabase()) {
            printEmployees();
            String name = input.next();
            if (employeeExists(name)) {
                System.out.print("Enter new hour: 0 <= Integer <= 8\n");
                int hour = input.nextInt();
                if (hour >= 0 && hour <= 8) {
                    database.findEmployee(name).inputHours(hour);
                    System.out.println(name + " worked " + hour + " hours today\n");
                } else {
                    System.out.println(name + " cannot work " + hour + " hours\n");
                }
            }
        }
    }

    // REQUIRES: employee exists, 0 <= hour <= 8, employee hours must not be empty,
    //           1 <= number <= most recent day employee worked
    // MODIFIES: this
    // EFFECTS: edits employee's hours
    private void updateHours() {
        if (nonEmptyDatabase()) {
            printEmployees();
            String name = input.next();
            if (database.findEmployee(name) == null || database.findEmployee(name).getHours().isEmpty()) {
                System.out.println("The employee may either not exist or they may not have worked any hours yet");
            } else {
                System.out.print("Enter day number: 1 <= Integer <= most recent day employee worked\n");
                int number = input.nextInt();
                System.out.println("Enter new hour: 0 <= Integer <= 8\n");
                int hour = input.nextInt();
                if (hour >= 0 && hour <= 8
                        && number >= 1 && number <= database.findEmployee(name).getHours().size()) {
                    database.findEmployee(name).updateHours(number, hour);
                    System.out.println(name + " worked " + hour + " hours on Day " + number + "\n");
                } else {
                    System.out.println("Please make sure that you are complying with the input rules\n");
                }
            }
        }
    }

    // EFFECTS: prints out how many hours an employee has worked so far
    private void hoursWorked() {
        if (nonEmptyDatabase()) {
            printEmployees();
            String name = input.next();
            if (employeeExists(name)) {
                System.out.println(name + " has worked " + database.findEmployee(name).getHoursWorked() + " hours \n");
            }
        }
    }

    // EFFECTS: prints list of employee names
    private void printEmployees() {
        System.out.println("Please select from the following employees:");
        System.out.println(database.employeeNames());
    }

    // EFFECTS: checks if an employee does not exist
    private boolean employeeExists(String name) {
        if (database.findEmployee(name) == null) {
            System.out.println("Employee does not exist...");
            return false;
        }
        return true;
    }

    // EFFECTS: tells user to add an employee if the database is empty
    private boolean nonEmptyDatabase() {
        if (database.getNumberOfEmployees() == 0) {
            System.out.println("Please add an employee to your database");
            return false;
        }
        return true;
    }

}