package model;

import java.util.ArrayList;

public class Employee {
    private String name;
    private ArrayList<Integer> hours = new ArrayList<>();
    private int hoursWorked;

    //REQUIRES: name must be formatted as FirstName LastName, 0 <= hoursWorked
    //EFFECTS: creates a new employee
    public Employee(String name, int hoursWorked) {
        this.name = name;
        this.hoursWorked = hoursWorked;
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: changes the name of an employee
    public void changeName(String newName) {
        name = newName;
    }

    //REQUIRES: 0 <= hoursWorkedToday
    //MODIFIES: hours, this
    //EFFECTS: adds hours worked today to list of hours worked so far
    public void inputHours(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
        hoursWorked = getHoursWorked(hours);
    }

    //REQUIRES: 1 <= dayNumber <= size of hours
    //MODIFIES: hours, this
    //EFFECTS: changes the hours worked on the given day number of the work period
    public void updateHours(int dayNumber, int newHours) {
        hours.set((dayNumber - 1), newHours);
        hoursWorked = getHoursWorked(hours);
    }

    //getters

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }

    public int getHoursWorked(ArrayList<Integer> hours) {
        this.hours = hours;
        hoursWorked = 0;
        for (int i = 0; i < hours.size(); i++) {
            hoursWorked = hoursWorked + hours.get(i);
        }
        return hoursWorked;
    }
}
