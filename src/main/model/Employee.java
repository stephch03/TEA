package model;

import java.util.ArrayList;

public class Employee {
    private String name;
    private ArrayList<Integer> hours;

    //REQUIRES: name must be formatted as FirstName LastName
    //EFFECTS: creates a new employee with their name and an empty list of their hours
    public Employee(String name) {
        hours = new ArrayList<>();
        this.name = name;
    }

    //REQUIRES: name must be formatted as FirstName LastName, and be different from the original name
    //MODIFIES: this
    //EFFECTS: sets the name of an employee
    public void changeName(String newName) {
        name = newName;
    }

    //REQUIRES: 0 <= hoursWorkedToday
    //MODIFIES: this
    //EFFECTS: adds hours worked today to list of hours worked so far
    public void inputHours(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
    }

    //REQUIRES: 1 <= dayNumber <= size of hours
    //MODIFIES: this
    //EFFECTS: changes the hours worked on the given day number of the work period
    public void updateHours(int dayNumber, int newHours) {
        hours.set((dayNumber - 1), newHours);
    }

    //REQUIRES: hours is not empty
    //MODIFIES: this
    //EFFECTS: empties the list of hours
    public void clearHours() {
        hours = new ArrayList<Integer>();
    }

    //EFFECTS: returns a sum of the total hours worked so far
    public int getHoursWorked() {
        int hoursWorked = 0;
        for (int i = 0; i < hours.size(); i++) {
            hoursWorked = hoursWorked + hours.get(i);
        }
        return hoursWorked;
    }

    //getters

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }
}