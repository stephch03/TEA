package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employeeTest;

    @BeforeEach
    public void setup() {
        employeeTest = new Employee("Ada Lovelace");
        employeeTest.inputHours(5);
        employeeTest.inputHours(0);
        employeeTest.inputHours(7);
    }


    @Test
    public void employeeConstructorTest() {
        assertEquals("Ada Lovelace", employeeTest.getName());
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(12, employeeTest.getHoursWorked());
    }

    @Test
    public void setNameTest() {
        assertEquals("Ada Lovelace", employeeTest.getName());
        employeeTest.setName("Lily Perkins");
        assertEquals("Lily Perkins", employeeTest.getName());
    }

    @Test
    public void inputHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.inputHours(3);
        assertEquals(4, employeeTest.getHours().size());
        assertEquals(5, employeeTest.getHours().get(0));
        assertEquals(0, employeeTest.getHours().get(1));
        assertEquals(7, employeeTest.getHours().get(2));
        assertEquals(3, employeeTest.getHours().get(3));
        assertEquals(15, employeeTest.getHoursWorked());
    }

    @Test
    public void inputZeroHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.inputHours(0);
        assertEquals(4, employeeTest.getHours().size());
        assertEquals(5, employeeTest.getHours().get(0));
        assertEquals(0, employeeTest.getHours().get(1));
        assertEquals(7, employeeTest.getHours().get(2));
        assertEquals(0, employeeTest.getHours().get(3));
        assertEquals(12, employeeTest.getHoursWorked());
    }

    @Test
    public void updateFirstDayHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.updateHours(1, 2);
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(2, employeeTest.getHours().get(0));
        assertEquals(9, employeeTest.getHoursWorked());
    }

    @Test
    public void updateMidDayHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.updateHours(2, 6);
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(6, employeeTest.getHours().get(1));
        assertEquals(18, employeeTest.getHoursWorked());
    }

    @Test
    public void updateLastDayHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.updateHours(3, 5);
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(5, employeeTest.getHours().get(2));
        assertEquals(10, employeeTest.getHoursWorked());
    }
}