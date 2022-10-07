package model;

import model.Timesheet;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private double wage;
    private ArrayList<Integer> hours;
    private static final double MINIMUM_WAGE = 15.65;

    //REQUIRES: initialWage >= MINIMUM_WAGE
    //MODIFIES: this
    //EFFECTS: creates a new employee with their first and last name, wage, and list of hours worked in pay period
    public Employee(String name, double initialWage, List<Integer> hours) {
        this.name = name;
        this.wage = initialWage;
        this.hours = new ArrayList<Integer>(hours);
    }

//    //REQUIRES: isApproved = false
//    //MODIFIES: this
//    //EFFECTS: approve the employee's hours
//    public void approve(boolean approved) {
//        isApproved = approved;
//    }

    //REQUIRES: hoursWorkedToday >= 0
    //MODIFIES: Employee hours
    //EFFECTS: adds hours worked today to list of hours worked so far
    public void updateHours(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
    }

    //REQUIRES: day <= size of hours
    //MODIFIES: Employee hours
    //EFFECTS: changes the hours worked on the given day number of the work period
    public void changeHours(int day, int newHours) {
        hours.set(day - 1, newHours);
    }

    //REQUIRES: newWage > MINIMUM_WAGE
    //MODIFIES: Employee wage
    //EFFECTS: changes the employee's current wage
    public void updateWage(double newWage) {
        wage = newWage;
    }

    // getters

    public String getName() {
        return name;
    }

    public double getWage() {
        return wage;
    }

    public ArrayList getHours() {
        return hours;
    }
}
