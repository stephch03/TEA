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
        databaseTest = new EmployeeDatabase("12-31-2022");
        a = new Employee("Ada Lovelace");
        b = new Employee("Bob Builder");;
        databaseTest.addEmployee(a);
        a.inputHours(5);
        databaseTest.addEmployee(b);
    }


    @Test
    public void employeeDatabaseConstructorTest() {
        assertEquals("12-31-2022", databaseTest.getDate());
        assertEquals(2, databaseTest.getNumberOfEmployees());
        assertEquals(5, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        assertEquals("Ada Lovelace", a.getName());
        assertEquals("Bob Builder", b.getName());
        assertFalse(databaseTest.employeeNames().isEmpty());
        assertTrue(databaseTest.employeeNames().contains("Ada Lovelace"));
        assertTrue(databaseTest.employeeNames().contains("Bob Builder"));
    }

    @Test
    public void changeDateTest() {
        assertEquals("12-31-2022", databaseTest.getDate());
        databaseTest.changeDate("12-01-2022");
        assertEquals("12-01-2022", databaseTest.getDate());
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        Employee e = new Employee("Emily Johnson");
        databaseTest.addEmployee(e);
        assertEquals(e.getHoursWorked(), 0);
        assertEquals(0, e.getHoursWorked());
        assertEquals(3, databaseTest.getNumberOfEmployees());
    }

    @Test
    public void addMultipleEmployeeTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        Employee e = new Employee("Emily Johnson");
        Employee c = new Employee("Carla Potter");
        databaseTest.addEmployee(e);
        databaseTest.addEmployee(c);
        assertEquals(4, databaseTest.getNumberOfEmployees());
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

    @Test
    public void printTimesheetForMultipleEmployeesTest() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        databaseTest.printTimesheet();
        assertEquals(2, databaseTest.printTimesheet().size());
        assertEquals("Ada Lovelace : 5 hours", databaseTest.printTimesheet().get(0));
        assertEquals("Bob Builder : 0 hours", databaseTest.printTimesheet().get(1));
    }

    @Test
    public void printTimesheetWithOneEmployee() {
        assertEquals(2, databaseTest.getNumberOfEmployees());
        databaseTest.removeEmployee(a.getName());
        assertEquals(1, databaseTest.getNumberOfEmployees());
        databaseTest.printTimesheet();
        assertEquals(1, databaseTest.printTimesheet().size());
        assertEquals("Bob Builder : 0 hours", databaseTest.printTimesheet().get(0));
    }

    //TODO
    @Test
    public void toJsonTest() {
    }

    @Test
    public void databaseToJsonTest() {

    }
}
