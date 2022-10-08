package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private ArrayList<Integer> hours = new ArrayList<>();
    private static final double MINIMUM_WAGE = 15.65;
    private int totalHours;


    //REQUIRES: initialWage >= MINIMUM_WAGE
    //MODIFIES: this
    //EFFECTS: creates a new employee with their first and last name and starting wage
    public Employee(String name) {
        this.name = name;
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
        hours.set((day - 1), newHours);
    }


    // getters

    public String getName() {
        return name;
    }


    public ArrayList<Integer> getHours() {
        return hours;
    }

    public int getHoursWorked() {
        totalHours = 0;
        for (int i = 0; i < hours.size(); i++) {
            totalHours = totalHours + hours.get(i);
        }
        return totalHours;
        // get Name for loop until the name is equal to the one yo8=u're looking for and then call get HoursWorked
    }
}
