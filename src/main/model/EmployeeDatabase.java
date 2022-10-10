package model;

import java.util.ArrayList;

// Represents all the employees in a company
public class EmployeeDatabase {

    private ArrayList<Employee> database;

    //EFFECTS: creates a database to hold all the employee information
    public EmployeeDatabase() {
        ArrayList<Employee> database = new ArrayList<>();
        this.database = database;
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: adds a new employee to the database
    public void addEmployee(String name, int initialHours) {
        Employee e = new Employee(name, initialHours);
        database.add(e);
    }

    //MODIFIES: this
    //EFFECTS: clears database when the pay period is over
    //TODO change to resetting only the hours
    public void reset() {
        database.clear();
    }

    //REQUIRES: name must be formatted as FirstName LastName
    // //name provided is the name of an existing employee
    //MODIFIES: this
    //EFFECTS: removes an employee from the database
    public void removeEmployee(String name) {
        database.remove(findEmployee(name));
    }

    //getters

    public int getNumberOfEmployees() {
        return database.size();
    }

    public Employee findEmployee(String name) {
        for (Employee employee : database) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }
}