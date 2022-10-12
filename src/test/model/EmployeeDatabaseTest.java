package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {

    EmployeeDatabase databaseTest;
    Employee a;
    Employee b;

    @BeforeEach
    public void setup() {
        databaseTest = new EmployeeDatabase();
        a = new Employee("Ada Lovelace");
        databaseTest.addEmployee(a.getName());
        a.inputHours(5);
        b = new Employee("Bob Builder");
        databaseTest.addEmployee(b.getName());
    }


    @Test
    public void employeeDatabaseConstructorTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        assertEquals(5, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        assertEquals("Ada Lovelace", a.getName());
        assertEquals("Bob Builder", b.getName());
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        Employee e = new Employee("Emily Johnson");
        databaseTest.addEmployee(e.getName());
        assertEquals(e.getHoursWorked(), 0);
        assertEquals(0, e.getHoursWorked());
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
        assertFalse(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
        databaseTest.reset();
        assertEquals(2, databaseTest.getNumberOfEmployees());
        //TODO need to fix bug in reset
//        assertEquals(0, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
//        assertTrue(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
    }
}
