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
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        Employee e = new Employee("Emily Johnson");
        databaseTest.addEmployee(e.getName());
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
        databaseTest.reset();
        assertEquals(databaseTest.getNumberOfEmployees(), 0);
    }
}
