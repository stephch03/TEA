package model;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> printTimesheet() {
        List<String> timesheet = new ArrayList<>();
        for (Employee e : database) {
            timesheet.add(e.getName() + " : " + e.getHoursWorked() + " hours");
        }
        return timesheet;
    }

    //getters

    public int getNumberOfEmployees() {
        return database.size();
    }

    public List<String> employeeNames() {
        List<String> employeeNames = new ArrayList<>();

        for (Employee employee : database) {
            employeeNames.add(employee.getName());
        }
        return employeeNames;
    }
}