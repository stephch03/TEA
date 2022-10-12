package model;

import java.util.ArrayList;

// Represents all the employees in a company
public class EmployeeDatabase {

    private ArrayList<Employee> database;

    //EFFECTS: creates a database to hold all the employee information
    public EmployeeDatabase() {
        database = new ArrayList<Employee>();
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: adds a new employee to the database
    public void addEmployee(String name) {
        Employee e = new Employee(name);
        database.add(e);
    }

    //MODIFIES: this
    //EFFECTS: clears database
    public void reset() {
        for (Employee employee : database) {
            employee.clearHours();
        }
    }
    //TODO completely reset database or just hours??? check modifies clause

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: removes an employee from the database
    public void removeEmployee(String name) {
        database.remove(findEmployee(name));
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //EFFECTS: returns employee with given name, return null if employee does not exist
    public Employee findEmployee(String name) {
        for (Employee employee : database) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    //getters

    public int getNumberOfEmployees() {
        return database.size();
    }
}