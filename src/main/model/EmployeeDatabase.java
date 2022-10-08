package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase {

    private ArrayList<Employee> employeeList;

    public EmployeeDatabase(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void reset() {
//        for (Employee e : employeeList)
//            e.hours.clear();
        //TODO use for loop and counter will set empty list using index
    }

    public void remove(String name) {
        //filter
    }

    public int findEmployee(String name) {
        return 0;
        //Filter name and use gethours
    }
}
