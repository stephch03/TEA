package model;

import java.util.ArrayList;
import java.util.List;

public class Timesheet {

    private List<Integer> hours;
    private int daysRemaining;
    private int totalHours;


    public Timesheet(Employee e) {
        hours = new ArrayList<>();
    }

    public void updateTimesheet(int hoursWorkedToday) {
        hours.add(hoursWorkedToday);
    }

    public void reset(Employee e) {
        daysRemaining = 14;
    }

    public void nextDay() {
        daysRemaining = getDaysRemaining() - 1;
    }

    public void removeEmployee(String name) {

    }

    //getters

    public List<Employee> getEmployees() {
        return null;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public int getHoursWorked(Employee employee) {
        int sumHours = 0;
        for (int i = 0; i < hours.size(); i++) {
            sumHours = sumHours + hours.get(i);
        }
        return sumHours;
    }
}
