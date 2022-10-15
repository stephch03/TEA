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
        b = new Employee("Bob Builder");
        databaseTest.addEmployee(b.getName());
    }


    @Test
    public void employeeDatabaseConstructorTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        assertEquals(0, a.getHoursWorked());
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
    public void printEmptyTimesheetTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        databaseTest.removeEmployee(a.getName());
        assertEquals(1, databaseTest.getNumberOfEmployees());
        databaseTest.removeEmployee(b.getName());
        assertEquals(0, databaseTest.getNumberOfEmployees());
        databaseTest.printTimesheet();
        assertTrue(databaseTest.printTimesheet().isEmpty());
    }

    //TODO fix bug
    @Test
    public void printTimesheetTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        a.inputHours(5);
        databaseTest.printTimesheet();
        assertEquals(2, databaseTest.printTimesheet().size());
        assertEquals("Ada Lovelace : 5 hours", databaseTest.printTimesheet().get(0));
        assertEquals("Bob Builder : 0 hours", databaseTest.printTimesheet().get(1));
    }

}
