package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employeeTest;

    @BeforeEach
    public void setup() {
        employeeTest = new Employee("Ada Lovelace",0);
        employeeTest.getHours().add(5);
        employeeTest.getHours().add(0);
        employeeTest.getHours().add(7);
    }

    @Test
    public void employeeConstructorTest() {
        assertEquals(employeeTest.getName(), "Ada Lovelace");
        assertEquals(employeeTest.getHours().size(), 3);
        assertEquals(employeeTest.getHoursWorked(employeeTest.getHours()), 12);
    }

    @Test
    public void setNameTest() {
        assertEquals(employeeTest.getName(), "Ada Lovelace");
        employeeTest.setName("Lily Perkins");
        assertEquals(employeeTest.getName(), "Lily Perkins");
    }

    @Test
    public void inputHoursTest() {
        assertEquals(employeeTest.getHours().size(), 3);
        employeeTest.inputHours(3);
        assertEquals(employeeTest.getHours().size(), 4);
        assertEquals(employeeTest.getHours().get(0), 5);
        assertEquals(employeeTest.getHours().get(1), 0);
        assertEquals(employeeTest.getHours().get(2), 7);
        assertEquals(employeeTest.getHours().get(3), 3);
        assertEquals(employeeTest.getHoursWorked(employeeTest.getHours()), 15);
    }

    @Test
    public void inputZeroHoursTest() {
        assertEquals(employeeTest.getHours().size(), 3);
        employeeTest.inputHours(0);
        assertEquals(employeeTest.getHours().size(), 4);
        assertEquals(employeeTest.getHours().get(0), 5);
        assertEquals(employeeTest.getHours().get(1), 0);
        assertEquals(employeeTest.getHours().get(2), 7);
        assertEquals(employeeTest.getHours().get(3), 0);
        assertEquals(employeeTest.getHoursWorked(employeeTest.getHours()), 12);
    }

    @Test
    public void updateFirstDayHoursTest() {
        assertEquals(employeeTest.getHours().size(), 3);
        employeeTest.updateHours(1, 2);
        assertEquals(employeeTest.getHours().get(0), 2);
        assertEquals(employeeTest.getHoursWorked(employeeTest.getHours()), 9);
    }

    @Test
    public void updateMidDayHoursTest() {
        assertEquals(employeeTest.getHours().size(), 3);
        employeeTest.updateHours(2, 6);
        assertEquals(employeeTest.getHours().get(1), 6);
        assertEquals(employeeTest.getHoursWorked(employeeTest.getHours()), 18);
    }

    @Test
    public void updateLastDayHoursTest() {
        assertEquals(employeeTest.getHours().size(), 3);
        employeeTest.updateHours(3, 5);
        assertEquals(employeeTest.getHours().get(2), 5);
        assertEquals(employeeTest.getHoursWorked(employeeTest.getHours()), 10);
    }
}