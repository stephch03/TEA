package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDatabaseTest {

    EmployeeDatabase databaseTest;

    @BeforeEach
    public void setup(){
        databaseTest = new EmployeeDatabase();
//        Employee a = new Employee("Ada Lovelace");
//        databaseTest.add(a);
//        Employee b = new Employee("Bob Builder");
//        databaseTest.add(b);
        Employee a = new Employee("Ada Lovelace");
        databaseTest.addEmployee(a.getName());
        Employee b = new Employee("Bob Builder");
        databaseTest.addEmployee(b.getName());
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        Employee e = new Employee("Emily Johnson");
        databaseTest.addEmployee(e.getName());
        assertEquals(databaseTest.getNumberOfEmployees(), 3);
    }

//    @Test
//    public void removeTest() {
//        assertEquals(databaseTest.getNumberOfEmployees(), 2);
//        Employee e = new Employee("Emily Johnson");
//        databaseTest.addEmployee(e.getName());
//        assertEquals(databaseTest.getNumberOfEmployees(), 3);
//        databaseTest.removeEmployee(e);
//        assertEquals(databaseTest.getNumberOfEmployees(), 2);
//    }

    @Test
    public void resetTest() {
        assertEquals(databaseTest.getNumberOfEmployees(), 2);
        databaseTest.reset();
        assertEquals(databaseTest.getNumberOfEmployees(), 0);
    }
}
