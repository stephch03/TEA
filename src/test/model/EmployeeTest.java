package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employeeTest;
    ArrayList<Integer> hoursTest;

    @BeforeEach
    public void setup() {
        employeeTest = new Employee("Ada Lovelace");
        hoursTest = new ArrayList<>();
        hoursTest.add(5);
        hoursTest.add(0);
        employeeTest.getHours().add(5);
        employeeTest.getHours().add(5);
    }

    @Test
    public void employeeConstructorTest(){
        assertEquals(employeeTest.getName(), "Ada Lovelace");
        assertEquals(hoursTest.size(), 2);

    }

    @Test
    public void inputHoursTest() {
        hoursTest.add(7);
        employeeTest.inputHours(7);
        assertEquals(hoursTest.size(), 3);
        assertEquals(hoursTest.get(0), 5);
        assertEquals(hoursTest.get(1), 0);
        assertEquals(hoursTest.get(2), 7);
    }

    @Test
    public void updateHoursTest() {
        assertEquals(hoursTest.size(), 2);
        employeeTest.getHours().set(0, 2);
        hoursTest.set(0, 2);
        employeeTest.updateHours(1, 2);
        assertEquals(hoursTest.get(0), 2);
        assertEquals(hoursTest.get(1), 0);
    }

    @Test
    public void updateLastHoursTest() {
        assertEquals(hoursTest.size(), 2);
        employeeTest.getHours().set(1, 2);
        hoursTest.set(1, 2);
        employeeTest.updateHours(2, 2);
        assertEquals(hoursTest.get(0), 5);
        assertEquals(hoursTest.get(1), 2);
    }
}