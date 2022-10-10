package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {

    EmployeeDatabase databaseTest;
    ArrayList<Integer> adaHoursTest = new ArrayList<>();
    ArrayList<Integer> bobHoursTest = new ArrayList<>();
    ArrayList<Integer> emilyHoursTest = new ArrayList<>();

    @BeforeEach
    public void setup() {
        databaseTest = new EmployeeDatabase();
        Employee a = new Employee("Ada Lovelace", 0);
        adaHoursTest.add(5);
        databaseTest.addEmployee(a.getName(), a.getHoursWorked(adaHoursTest));
        Employee b = new Employee("Bob Builder", 0);
        bobHoursTest.add(0);
        databaseTest.addEmployee(b.getName(), b.getHoursWorked(bobHoursTest));
    }

    @Test
    public void employeeDatabaseConstructorTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        assertEquals(databaseTest.findEmployee("Ada Lovelace").getHoursWorked(adaHoursTest), 5);
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        Employee e = new Employee("Emily Johnson",0);
        assertEquals(e.getHoursWorked(emilyHoursTest), 0);
        emilyHoursTest.add(5);
        databaseTest.addEmployee(e.getName(), e.getHoursWorked(emilyHoursTest));
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
        assertFalse(adaHoursTest.isEmpty());
        assertFalse(bobHoursTest.isEmpty());
        databaseTest.reset();
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        adaHoursTest.clear();
        bobHoursTest.clear();
        assertEquals(databaseTest.findEmployee("Ada Lovelace").getHoursWorked(adaHoursTest),0);
        assertEquals(databaseTest.findEmployee("Bob Builder").getHoursWorked(bobHoursTest),0);
        assertTrue(adaHoursTest.isEmpty());
        assertTrue(bobHoursTest.isEmpty());
    }
}
