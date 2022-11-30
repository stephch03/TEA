package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents all the employees in a company
public class EmployeeDatabase implements Writable {
    public static final String INITIAL_DATE = "Undated";
    private List<Employee> database;
    private String date;

    //EFFECTS: creates a database to hold all the employee information and date of the pay period
    public EmployeeDatabase() {
        date = INITIAL_DATE;
        database = new ArrayList<>();
    }

    //REQUIRES: date must be formatted in MM-DD-YYYY
    //MODIFIES: this
    //EFFECTS: updates the date
    public void changeDate(String newDate) {
        this.date = newDate;
        EventLog.getInstance().logEvent(new Event("The date of the database was changed to " + newDate + "."));
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: adds a new employee to the database
    public void addEmployee(Employee e) {
        database.add(e);
        EventLog.getInstance().logEvent(new Event(e.getName() + " was added to the employee database."));
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: removes an employee from the database
    public void removeEmployee(String name) {
        database.remove(findEmployee(name));
        EventLog.getInstance().logEvent(new Event(name + " was removed from the employee database."));
    }

    //MODIFIES: this
    //EFFECTS: clears database
    public void reset() {
        for (Employee employee : database) {
            employee.clearHours();
        }
        EventLog.getInstance().logEvent(new Event("All employee's hours were reset."));
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

    //EFFECTS: returns EmployeeName: list of hours worked, # hours total for each employee in database
    public List<String> printTimesheet() {
        List<String> timesheet = new ArrayList<>();
        for (Employee e : database) {
            timesheet.add(e.getName() + ": " + e.getHours() + ", " + e.getHoursWorked() + " hours total");
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

    public List<Employee> getEmployees() {
        return database;
    }

    public int getNumEmployees() {
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