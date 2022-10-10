package model;

import java.util.ArrayList;

public class EmployeeDatabase {

    private ArrayList<Employee> database = new ArrayList<>();

    public EmployeeDatabase() {
        ArrayList<Employee> database = new ArrayList<>();
        this.database = database;
    }

    public void addEmployee(String name) {
        Employee e = new Employee(name);
        database.add(e);
    }

    public void reset() {
        database.clear();
    }

    public void removeEmployee(Employee e) {
        database.remove(e);
    }

    public int getEmployee(String name) {
//        while (getName.)
        return 0;
        //Filter name and use gethours
    }

    //getters

//    public ArrayList<Employee> getDatabase() {
//        return database;
//    }

    public int getNumberOfEmployees() {
        return database.size();
    }

//    public String getEmployee(){}

    public int getEmployeeHoursWorked(Employee e) {
        database.indexOf(e);
        return 0;
    }

}
