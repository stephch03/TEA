package persistence;

import model.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Sourced from CPSC 210 JsonSerializationDemo
public class JsonTest {

    protected void checkEmployee(String name, List<Integer> hours, Employee e) {
        assertEquals(name, e.getName());
        assertEquals(hours, e.getHours());
    }
}
