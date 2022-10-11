package model;

import java.util.ArrayList;

public class Employee {
    private String name;
    private ArrayList<Integer> hours;

    //REQUIRES: name must be formatted as FirstName LastName, hoursWorked = 0
    //EFFECTS: creates a new employee
    public Employee(String name) {
        hours = new ArrayList<>();
        this.name = name;
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: changes the name of an employee
    public void setName(String newName) {
        name = newName;
    }

    //REQUIRES: 0 <= hoursWorkedToday
    //MODIFIES: hours, this
    //EFFECTS: adds hours worked today to list of hours worked so far
    public void inputHours(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
    }

    //REQUIRES: 1 <= dayNumber <= size of hours
    //MODIFIES: hours, this
    //EFFECTS: changes the hours worked on the given day number of the work period
    public void updateHours(int dayNumber, int newHours) {
        hours.set((dayNumber - 1), newHours);
    }

    //MODIFIES: this
    //EFFECTS: empties the list of hours
    public void clearHours() {
        hours.clear();
    }

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

