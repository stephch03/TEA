package model;

import model.Timesheet;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employeeTest;

    @BeforeEach
    public void setup(){
        employeeTest = new Employee("Ada Lovelace", 15.65);
    }

    @Test
    public void removeEmployeeTest(){
    }

    @Test
    public void updateWageTest() {
        employeeTest.updateWage(18.00);
        assertEquals(employeeTest.getWage(), 18.00);
    }

    // delete or rename this class!
}