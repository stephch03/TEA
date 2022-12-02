package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {

    EmployeeDatabase databaseTest;
    Employee a;
    Employee b;

    @BeforeEach
    public void setup() {
        databaseTest = new EmployeeDatabase();
        a = new Employee("Ada Lovelace");
        b = new Employee("Bob Builder");;
        databaseTest.addEmployee(a);
        a.inputHours(5);
        databaseTest.addEmployee(b);
        EventLog.getInstance().clear();
    }

    @Test
    public void employeeDatabaseConstructorTest() {
        assertEquals("Undated", databaseTest.getDate());
        assertEquals(2, databaseTest.getNumEmployees());
        assertEquals(5, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        assertEquals("Ada Lovelace", a.getName());
        assertEquals("Bob Builder", b.getName());
        assertFalse(databaseTest.employeeNames().isEmpty());
        assertTrue(databaseTest.employeeNames().contains("Ada Lovelace"));
        assertTrue(databaseTest.employeeNames().contains("Bob Builder"));

        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertFalse(events.hasNext());
    }

    @Test
    public void changeDateTest() {
        assertEquals("Undated", databaseTest.getDate());
        databaseTest.changeDate("12-01-2022");
        assertEquals("12-01-2022", databaseTest.getDate());
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        Employee e = new Employee("Emily Johnson");
        databaseTest.addEmployee(e);
        assertEquals(e.getHoursWorked(), 0);
        assertEquals(0, e.getHoursWorked());
        assertEquals(3, databaseTest.getNumEmployees());

        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertTrue(events.hasNext());
        assertEquals("Emily Johnson was added to the employee database.", events.next().getDescription());
        assertFalse(events.hasNext());
    }

    @Test
    public void addMultipleEmployeeTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        Employee e = new Employee("Emily Johnson");
        Employee c = new Employee("Carla Potter");
        databaseTest.addEmployee(e);
        databaseTest.addEmployee(c);
        assertEquals(4, databaseTest.getNumEmployees());

        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertTrue(events.hasNext());
        assertEquals("Emily Johnson was added to the employee database.", events.next().getDescription());
        assertTrue(events.hasNext());
        assertEquals("Carla Potter was added to the employee database.", events.next().getDescription());
        assertFalse(events.hasNext());
    }

    @Test
    public void removeExistingEmployeeTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        databaseTest.removeEmployee("Ada Lovelace");
        assertEquals(databaseTest.getNumEmployees(), 1);
        assertNull(databaseTest.findEmployee("Ada Lovelace"));

        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertTrue(events.hasNext());
        assertEquals("Ada Lovelace was removed from the employee database.", events.next().getDescription());
        assertFalse(events.hasNext());
    }

    @Test
    public void removeNonExistentEmployeeTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        databaseTest.removeEmployee("Emily Johnson");
        assertEquals(2, databaseTest.getNumEmployees());
        assertNull(databaseTest.findEmployee("Emily Johnson"));
    }

    @Test
    public void printEmptyTimesheetTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        databaseTest.removeEmployee(a.getName());
        assertEquals(1, databaseTest.getNumEmployees());
        databaseTest.removeEmployee(b.getName());
        assertEquals(0, databaseTest.getNumEmployees());
        databaseTest.printTimesheet();
        assertTrue(databaseTest.printTimesheet().isEmpty());
    }

    @Test
    public void printTimesheetForMultipleEmployeesTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        databaseTest.printTimesheet();
        assertEquals(2, databaseTest.printTimesheet().size());
        assertEquals("Ada Lovelace: [5], 5 hours total", databaseTest.printTimesheet().get(0));
        assertEquals("Bob Builder: [], 0 hours total", databaseTest.printTimesheet().get(1));
    }

    @Test
    public void printTimesheetWithOneEmployee() {
        assertEquals(2, databaseTest.getNumEmployees());
        databaseTest.removeEmployee(a.getName());
        assertEquals(1, databaseTest.getNumEmployees());
        databaseTest.printTimesheet();
        assertEquals(1, databaseTest.printTimesheet().size());
        assertEquals("Bob Builder: [], 0 hours total", databaseTest.printTimesheet().get(0));
    }

    @Test
    public void resetHoursTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        assertFalse(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
        assertEquals(5, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        databaseTest.reset();
        assertEquals(2, databaseTest.getNumEmployees());
        assertEquals(0, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        assertTrue(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
    }

    @Test
    public void resetAlreadyEmptyHoursTest() {
        assertEquals(2, databaseTest.getNumEmployees());
        assertFalse(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
        assertEquals(5, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        a.clearHours();
        assertEquals(0, a.getHoursWorked());
        assertTrue(a.getHours().isEmpty());
        databaseTest.reset();
        assertEquals(2, databaseTest.getNumEmployees());
        assertEquals(0, a.getHoursWorked());
        assertEquals(0, b.getHoursWorked());
        assertTrue(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
    }

    //TODO
    @Test
    public void toJsonTest() {
    }

    @Test
    public void databaseToJsonTest() {

    }
}
