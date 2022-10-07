package model;

import model.Timesheet;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employeeTest;

    @BeforeEach
    public void setup() {
        employeeTest = new Employee("Ada Lovelace", 15.65, null);
    }

    @Test
    public void updateHoursTest() {
        employeeTest.updateHours(5);
        assertEquals(employeeTest.getHours().size(), 1);
        assertTrue(employeeTest.getHours().contains(5));
    }

//    @Test
//    public void changeHoursTest(){
//        employeeTest.hours.add(5);
//    }

    @Test
    public void updateWageTest() {
        employeeTest.updateWage(18.00);
        assertEquals(employeeTest.getWage(), 18.00);

    }
}