package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents all the employees in a company
public class EmployeeDatabase implements Writable {
    private List<Employee> database;
    private String date;

    //EFFECTS: creates a database to hold all the employee information
    public EmployeeDatabase(String date) {
        this.date = date;
        database = new ArrayList<>();
    }

    //REQUIRES: date must be formatted in MM-DD-YYYY
    //MODIFIES: this
    //EFFECTS: updates the date
    public void changeDate(String newDate) {
        this.date = newDate;
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: adds a new employee to the database
    public void addEmployee(Employee e) {
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

    //EFFECTS: returns EmployeeName : # hours worked for each employee in database
    public List<String> printTimesheet() {
        List<String> timesheet = new ArrayList<>();
        for (Employee e : database) {
            timesheet.add(e.getName() + " : " + e.getHoursWorked() + " hours");
        }
        return timesheet;
    }

    //EFFECTS: returns a list of all the names of employees in the database
    public List<String> employeeNames() {
        List<String> employeeNames = new ArrayList<>();
        for (Employee employee : database) {
            employeeNames.add(employee.getName());
        }
        return employeeNames;
    }

    //getters

    public String getDate() {
        return date;
    }

    public int getNumberOfEmployees() {
        return database.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", date);
        json.put("database", databaseToJson());
        return json;
    }

    // EFFECTS: returns employees in this database as a JSON array
    private JSONArray databaseToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Employee e : database) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}