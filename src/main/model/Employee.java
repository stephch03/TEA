package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private double wage;
    private List<Integer> hours = new ArrayList<>();
    private static final double MINIMUM_WAGE = 15.65;
    private int totalHours;


    //REQUIRES: initialWage >= MINIMUM_WAGE
    //MODIFIES: this
    //EFFECTS: creates a new employee with their first and last name and starting wage
    public Employee(String name, int totalHours) {
        this.name = name;
        this.totalHours = totalHours;

    }

    //REQUIRES: hoursWorkedToday >= 0
    //MODIFIES: hours
    //EFFECTS: adds hours worked today to list of hours worked so far
    public void inputHours(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
    }

    //REQUIRES: day <= size of hours
    //MODIFIES: hours
    //EFFECTS: changes the hours worked on the given day number of the work period
    public void updateHours(int day, int newHours) {
        hours.set(day, newHours);
    }

    //REQUIRES: newWage > MINIMUM_WAGE
    //MODIFIES: this
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

    public List<Integer> getHours() {
        return hours;
    }

    public int getHoursWorked() {
        totalHours = 0;
        for (int i = 0; i < hours.size(); i++) {
            totalHours = totalHours + hours.get(i);
        }
        return totalHours;
        // get Name for loopp until the name is equal to the one yo8're looking for and then call get HoursWorked
    }
}
