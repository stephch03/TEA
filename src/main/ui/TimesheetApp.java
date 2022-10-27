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
        handleLoad();
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
        handleSave();
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes database
    private void init() {
        database = new EmployeeDatabase();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command to load
    private void handleLoad() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("\nWould you like to load a previous database or start a new database? Load or new");
            String response = input.next();
            response = response.toLowerCase();
            if (response.equals("load")) {
                loadDatabase();
                keepGoing = false;
            } else if (response.equals("new")) {
                keepGoing = false;
            } else {
                System.out.println("Sorry your request could not be recognized");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command to save
    private void handleSave() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("\nWould you like to save your current database? Yes or no");
            String response = input.next();
            response = response.toLowerCase();
            if (response.equals("yes")) {
                saveDatabase();
                keepGoing = false;
            } else if (response.equals("no")) {
                keepGoing = false;
            } else {
                System.out.println("Sorry your request could not be recognized");
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from Main Menu:");
        System.out.println("\td -> change date of Timesheet");
        System.out.println("\treset -> clear all employee's hours in the database");
        System.out.println("\tp -> print Timesheet");
        System.out.println("\tq -> quit (save?)");
        System.out.println("\nOr, select from Employee Menu:");
        System.out.println("\tae -> add Employee");
        System.out.println("\tr -> remove Employee");
        System.out.println("\tn -> change Employee's name");
        System.out.println("\tah -> add Employee's new hours");
        System.out.println("\tu -> update Employee's existing hours");
        System.out.println("\tg -> get Employee's hours worked so far");
    }

    // MODIFIES: this
    // EFFECTS: processes user command in the display menu
    private void processCommand(String command) {
        if (command.equals("d")) {
            changeDate();
        } else if (command.equals("reset")) {
            resetEmployeeHours();
        } else if (command.equals("p")) {
            printTimesheet();
        } else if (command.equals("ae")) {
            createEmployee();
        } else if (command.equals("r")) {
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

    // EFFECTS: saves database to file
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

    // MODIFIES: this
    // EFFECTS: loads database from file
    private void loadDatabase() {
        try {
            database = jsonReader.read();
            System.out.println("Loaded " + database.getDate() + " timesheet from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // REQUIRES: format of date must be MM-DD-YYYY
    // MODIFIES: this
    // EFFECTS: changes the date of timesheet
    private void changeDate() {
        System.out.println("Please enter the period of this timesheet: MM-DD-YYYY to MM-DD-YYYY");
        String date = input.next();
        database.changeDate(date);
    }

    // REQUIRES: database must not be empty
    // MODIFIES: this
    // EFFECTS: resets the hours of all employees in the database
    private void resetEmployeeHours() {
        if (nonEmptyDatabase()) {
            database.reset();
            System.out.println("All employee's hours have been reset");
        }
    }

    // EFFECTS: prints timesheet of employee database to the screen
    private void printTimesheet() {
        if (database.getNumEmployees() > 0) {
            System.out.println("Here is your " + database.getDate() + " timesheet:\n");
            System.out.println(database.printTimesheet());
        } else {
            System.out.println("Your timesheet is empty");
        }
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
                if (database.findEmployee(name).getHours().isEmpty()) {
                    System.out.print("Enter first hour: 0 <= Integer <= 8\n");
                } else {
                    printEmployeeHours(name);
                    System.out.print("Enter next hour: 0 <= Integer <= 8\n");
                }
                int hour = input.nextInt();
                if (hour >= 0 && hour <= 8) {
                    database.findEmployee(name).inputHours(hour);
                    printEmployeeHours(name);
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
                printEmployeeHours(name);
                System.out.print("Enter day number you'd like to change\n");
                int number = input.nextInt();
                System.out.println("Enter new hour: 0 <= Integer <= 8\n");
                int hour = input.nextInt();
                if (hour >= 0 && hour <= 8
                        && number >= 1 && number <= database.findEmployee(name).getHours().size()) {
                    database.findEmployee(name).updateHours(number, hour);
                    printEmployeeHours(name);
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
        if (database.getNumEmployees() == 0) {
            System.out.println("Your database is empty, please add an employee");
            return false;
        }
        return true;
    }

    // EFFECTS: returns a list of the selected employee's hours so far
    private void printEmployeeHours(String name) {
        System.out.println("\nHere are " + name + "'s hours so far");
        for (int i = 0; i < database.findEmployee(name).hoursSize(); i++) {
            System.out.println("Day " + (i + 1) + ": " + database.findEmployee(name).getHours().get(i) + " hours");
        }
    }

}