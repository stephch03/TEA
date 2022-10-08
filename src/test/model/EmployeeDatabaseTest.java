package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeDatabaseTest {

    ArrayList<Employee> databaseTest;

    @BeforeEach
    public void setup(){
        databaseTest = new ArrayList<>();
        Employee a = new Employee("Ada Lovelace");
        databaseTest.add(a);
        Employee b = new Employee("Bob the Builder");
        databaseTest.add(b);
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(databaseTest.size(), 2);
        Employee e = new Employee("Emily Johnson");
        databaseTest.add(e);
        assertEquals(databaseTest.get(2), e);
        assertEquals(databaseTest.size(), 3);
    }

    @Test
    public void removeTest() {
        assertEquals(databaseTest.size(), 2);
        Employee e = new Employee("Emily Johnson");
        databaseTest.add(e);
        assertEquals(databaseTest.size(), 3);
        databaseTest.remove(e);
        assertEquals(databaseTest.size(), 2);
    }

    @Test
    public void reset() {
        assertEquals(databaseTest.size(), 2);
        databaseTest.clear();
        assertEquals(databaseTest.size(), 0);
    }
}
