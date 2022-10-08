package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase {

    private ArrayList<Employee> database = new ArrayList<Employee>();

    public EmployeeDatabase(ArrayList<Employee> database) {
        this.database = database;
    }

    public void addNewEmployee(String name) {
        Employee e = new Employee(name);
        database.add(e);
    }

    public void reset() {
        database.clear();
    }

    public void remove(String name) {
        database.remove(name);
    }

    public int getEmployee(String name) {
//        for (Employee e : database)
        return 0;
        //Filter name and use gethours
    }

    public ArrayList<Employee> getDatabase() {
        return database;
    }

}
