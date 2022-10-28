package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

// Represents an employee
public class Employee implements Writable {
    private String name;
    private ArrayList<Integer> hours;

    //REQUIRES: name must be formatted as FirstName LastName
    //EFFECTS: creates a new employee with their name and an empty list of their hours
    public Employee(String name) {
        hours = new ArrayList<>();
        this.name = name;
    }

    //REQUIRES: name must be formatted as FirstName LastName
    //MODIFIES: this
    //EFFECTS: sets the name of an employee
    public void changeName(String newName) {
        name = newName;
    }

    //REQUIRES: 0 <= hoursWorkedToday <= 8
    //MODIFIES: this
    //EFFECTS: adds hours worked today to list of hours worked so far
    public void inputHours(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
    }

    //REQUIRES: 1 <= dayNumber <= size of hours, 0 <= newHours <= 8
    //MODIFIES: this
    //EFFECTS: changes the hours worked on the given day number of the work period
    public void updateHours(int dayNumber, int newHours) {
        hours.set((dayNumber - 1), newHours);
    }

    //MODIFIES: this
    //EFFECTS: empties the list of hours
    public void clearHours() {
        hours = new ArrayList<>();
    }

    //EFFECTS: returns a sum of the total hours worked so far
    public int getHoursWorked() {
        int hoursWorked = 0;
        for (int i = 0; i < hours.size(); i++) {
            hoursWorked += hours.get(i);
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

    public int hoursSize() {
        return hours.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hours", hoursToJson());
        return json;
    }

    // EFFECTS: returns hours in this employee as a JSON array
    private JSONArray hoursToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Integer h : hours) {
            jsonArray.put(h);
        }

        return jsonArray;
    }
}