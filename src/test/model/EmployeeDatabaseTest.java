package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {

    EmployeeDatabase databaseTest;

    @BeforeEach
    public void setup() {
        databaseTest = new EmployeeDatabase();
        Employee a = new Employee("Ada Lovelace", 0);
        databaseTest.addEmployee(a.getName(), a.getHoursWorked(a.getHours()));
        Employee b = new Employee("Bob Builder", 0);
        databaseTest.addEmployee(b.getName(), b.getHoursWorked(b.getHours()));
    }


    @Test
    public void employeeDatabaseConstructorTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        Employee a = databaseTest.findEmployee("Ada Lovelace");
        assertEquals(a.getHoursWorked(a.getHours()), 0);
        Employee b = databaseTest.findEmployee("Bob Builder");
        assertEquals(b.getHoursWorked(b.getHours()), 0);
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        Employee e = new Employee("Emily Johnson",0);
        assertEquals(e.getHoursWorked(e.getHours()), 0);
        e.getHours().add(5);
        databaseTest.addEmployee(e.getName(), e.getHoursWorked(e.getHours()));
        assertEquals(databaseTest.getNumberOfEmployees(), 3);
    }

    @Test
    public void removeExistingEmployeeTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        databaseTest.removeEmployee("Ada Lovelace");
        assertEquals(databaseTest.getNumberOfEmployees(), 1);
    }

    @Test
    public void removeNonExistentEmployeeTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        databaseTest.removeEmployee("Emily Johnson");
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
    }

    @Test
    public void resetTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        Employee a = databaseTest.findEmployee("Ada Lovelace");
        Employee b = databaseTest.findEmployee("Bob Builder");
        a.inputHours(5);
        assertEquals(a.getHoursWorked(a.getHours()), 5);
        assertFalse(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
        databaseTest.reset();
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        assertEquals(a.getHoursWorked(a.getHours()),0);
        assertEquals(a.getHoursWorked(a.getHours()),0);
        assertTrue(a.getHours().isEmpty());
        assertTrue(b.getHours().isEmpty());
    }
}
