package model;

import model.Timesheet;

public class Employee {
    private String name;
    private boolean isApproved;
    private double wage;

    //REQUIRES: wage >= 15.65
    //MODIFIES: this
    //EFFECTS: creates a new employee with their name, age, whether they are a manager, and wage
    public Employee(String name, boolean isApproved, double initialWage) {
        this.name = name;
        this.isApproved = isApproved;
        this.wage = initialWage;
    }

    //MODIFIES: this
    //EFFECTS: approve the employee's hours
    public void approve(boolean approved) {
        isApproved = approved;
    }

    public void updateWage(double newWage) {
        wage = newWage;
    }

    // getters

    public String getName() {
        return name;
    }

    public boolean approvalStatus() {
        return isApproved;
    }

    public double getWage() {
        return wage;
    }
}
