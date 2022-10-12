package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {

    EmployeeDatabase databaseTest;

    @BeforeEach
    public void setup() {
        databaseTest = new EmployeeDatabase();
        Employee a = new Employee("Ada Lovelace");
        databaseTest.addEmployee(a.getName());

        Employee b = new Employee("Bob Builder");
        databaseTest.addEmployee(b.getName());
    }


    @Test
    public void employeeDatabaseConstructorTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        Employee a = databaseTest.findEmployee("Ada Lovelace");
        assertEquals(0, a.getHoursWorked());
        Employee b = databaseTest.findEmployee("Bob Builder");
        assertEquals(0, b.getHoursWorked());
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        Employee e = new Employee("Emily Johnson");
        assertEquals(e.getHoursWorked(), 0);
        e.getHours().add(0);
        assertEquals(0, e.getHoursWorked());
        databaseTest.addEmployee(e.getName());
        assertEquals(3, databaseTest.getNumberOfEmployees());
    }

    @Test
    public void removeExistingEmployeeTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        databaseTest.removeEmployee("Ada Lovelace");
        assertEquals(databaseTest.getNumberOfEmployees(), 1);
        assertNull(databaseTest.findEmployee("Ada Lovelace"));
    }

    @Test
    public void removeNonExistentEmployeeTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        databaseTest.removeEmployee("Emily Johnson");
        assertEquals(2, databaseTest.getNumberOfEmployees());
        assertNull(databaseTest.findEmployee("Emily Johnson"));
    }

    @Test
    public void resetTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        Employee a = databaseTest.findEmployee("Ada Lovelace");
        Employee b = databaseTest.findEmployee("Bob Builder");
        a.inputHours(5);
        a.inputHours(7);
        assertEquals(12, a.getHoursWorked());
        assertFalse(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
        databaseTest.reset();
        assertEquals(2, databaseTest.getNumberOfEmployees());
        assertEquals(0, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        assertTrue(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
    }
}
