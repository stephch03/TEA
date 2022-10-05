package model;
import model.Timesheet;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimesheetTest {

    Timesheet timesheetTest;
    int daysRemaining;
    Employee e = new Employee("Ada Lovelace", 15.65);

    @BeforeEach
    public void setup(){
        timesheetTest = new Timesheet(e);

    }

    @Test
    public void removeEmployeeTest() {
    }

    @Test
    public void updateTimesheetTest() {

    }

    @Test
    public void reset() {
        daysRemaining = 14;
    }

    // delete or rename this class!
}
