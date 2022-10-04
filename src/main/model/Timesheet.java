package model;

import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class Timesheet {

    private List<Employee> database;
    private boolean isApproved;

    public Timesheet() {
        database = new ArrayList<>();
        isApproved = false;
    }

    public void approve(String name) {
        isApproved = true;
    }
}
