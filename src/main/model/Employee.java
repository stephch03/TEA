package model;

import java.util.ArrayList;

public class Employee {
    private String name;
    private ArrayList<Integer> hours = new ArrayList<>();
    private int totalHours;

    //REQUIRES: name must be formatted as FirstName LastName
    //EFFECTS: creates a new employee
    public Employee(String name, int hoursWorked) {
        this.name = name;
        this.totalHours = hoursWorked;
    }

    //REQUIRES: hoursWorkedToday >= 0
    //MODIFIES: hours
    //EFFECTS: adds hours worked today to list of hours worked so far
    public void inputHours(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
        totalHours = getHoursWorked(hours);
    }

    //REQUIRES: 1 <= day <= size of hours
    //MODIFIES: hours, this
    //EFFECTS: changes the hours worked on the given day number of the work period
    public void updateHours(int day, int newHours) {
        hours.set((day - 1), newHours);
        totalHours = getHoursWorked(hours);
    }

    //getters

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }

    public int getHoursWorked(ArrayList<Integer> hours) {
        totalHours = 0;
        for (int i = 0; i < hours.size(); i++) {
            totalHours = totalHours + hours.get(i);
        }
        return totalHours;
        // test getHoursWorked on hours
        // get Name for loop until the name is equal to the one yo8=u're looking for and then call get HoursWorked
    }
}
