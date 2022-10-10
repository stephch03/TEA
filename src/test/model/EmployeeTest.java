package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employeeTest;
    ArrayList<Integer> hoursTest;

    @BeforeEach
    public void setup() {
        employeeTest = new Employee("Ada Lovelace",0);
        hoursTest = new ArrayList<>();
        hoursTest.add(5);
        hoursTest.add(0);
        hoursTest.add(7);
        employeeTest.getHours().add(5);
        employeeTest.getHours().add(0);
        employeeTest.getHours().add(7);
    }

    @Test
    public void employeeConstructorTest() {
        assertEquals(employeeTest.getName(), "Ada Lovelace");
        assertEquals(hoursTest.size(), 3);
        assertEquals(employeeTest.getHoursWorked(hoursTest), 12);
    }

    @Test
    public void setNameTest() {
        assertEquals(employeeTest.getName(), "Ada Lovelace");
        employeeTest.setName("Lily Perkins");
        assertEquals(employeeTest.getName(), "Lily Perkins");
    }

    @Test
    public void inputHoursTest() {
        assertEquals(hoursTest.size(), 3);
        hoursTest.add(3);
        employeeTest.inputHours(3);
        assertEquals(hoursTest.size(), 4);
        assertEquals(hoursTest.get(0), 5);
        assertEquals(hoursTest.get(1), 0);
        assertEquals(hoursTest.get(2), 7);
        assertEquals(hoursTest.get(3), 3);
        assertEquals(employeeTest.getHoursWorked(hoursTest), 15);
    }

    @Test
    public void inputZeroHoursTest() {
        assertEquals(hoursTest.size(), 3);
        hoursTest.add(0);
        employeeTest.inputHours(0);
        assertEquals(hoursTest.size(), 4);
        assertEquals(hoursTest.get(0), 5);
        assertEquals(hoursTest.get(1), 0);
        assertEquals(hoursTest.get(2), 7);
        assertEquals(hoursTest.get(3), 0);
        assertEquals(employeeTest.getHoursWorked(hoursTest), 12);
    }

    @Test
    public void updateFirstDayHoursTest() {
        assertEquals(hoursTest.size(), 3);
        employeeTest.getHours().set(0, 2);
        hoursTest.set(0, 2);
        employeeTest.updateHours(1, 2);
        assertEquals(hoursTest.get(0), 2);
        assertEquals(employeeTest.getHoursWorked(hoursTest), 9);
    }

    @Test
    public void updateMidDayHoursTest() {
        assertEquals(hoursTest.size(), 3);
        employeeTest.getHours().set(1, 6);
        hoursTest.set(1, 6);
        employeeTest.updateHours(2, 6);
        assertEquals(hoursTest.get(1), 6);
        assertEquals(employeeTest.getHoursWorked(hoursTest), 18);
    }

    @Test
    public void updateLastDayHoursTest() {
        assertEquals(hoursTest.size(), 3);
        employeeTest.getHours().set(2, 5);
        hoursTest.set(2, 5);
        employeeTest.updateHours(3, 5);
        assertEquals(hoursTest.get(2), 5);
        assertEquals(employeeTest.getHoursWorked(hoursTest), 10);
    }
}