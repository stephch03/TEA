package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee employeeTest;

    @BeforeEach
    public void setup() {
        employeeTest = new Employee("Ada Lovelace");
        employeeTest.inputHours(5);
        employeeTest.inputHours(0);
        employeeTest.inputHours(7);
        EventLog.getInstance().clear();
    }

    @Test
    public void employeeConstructorTest() {
        assertEquals("Ada Lovelace", employeeTest.getName());
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(12, employeeTest.getHoursWorked());
        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertFalse(events.hasNext());
    }


    @Test
    public void changeNameTest() {
        assertEquals("Ada Lovelace", employeeTest.getName());
        employeeTest.changeName("Lily Perkins");
        assertEquals("Lily Perkins", employeeTest.getName());
        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertTrue(events.hasNext());
        assertEquals("Ada Lovelace was changed to Lily Perkins.", events.next().getDescription());
        assertFalse(events.hasNext());
    }

    @Test
    public void inputHoursTest() {
        assertEquals(3, employeeTest.hoursSize());
        employeeTest.inputHours(3);
        assertEquals(4, employeeTest.getHours().size());
        assertEquals(5, employeeTest.getHours().get(0));
        assertEquals(0, employeeTest.getHours().get(1));
        assertEquals(7, employeeTest.getHours().get(2));
        assertEquals(3, employeeTest.getHours().get(3));
        assertEquals(15, employeeTest.getHoursWorked());
        assertEquals(4, employeeTest.getHours().size());

        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertTrue(events.hasNext());
        assertEquals("Ada Lovelace worked 3 hours on Day 4.", events.next().getDescription());
        assertFalse(events.hasNext());
    }

    @Test
    public void inputZeroHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.inputHours(0);
        assertEquals(4, employeeTest.getHours().size());
        assertEquals(5, employeeTest.getHours().get(0));
        assertEquals(0, employeeTest.getHours().get(1));
        assertEquals(7, employeeTest.getHours().get(2));
        assertEquals(0, employeeTest.getHours().get(3));
        assertEquals(12, employeeTest.getHoursWorked());
    }

    @Test
    public void updateFirstDayHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.updateHours(1, 2);
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(2, employeeTest.getHours().get(0));
        assertEquals(9, employeeTest.getHoursWorked());

        Iterator<Event> events = EventLog.getInstance().iterator();
        assertTrue(events.hasNext());
        assertEquals("Event log cleared.", events.next().getDescription());
        assertTrue(events.hasNext());
        assertEquals("Ada Lovelace's Day 1 hours were changed from 5 to 2.", events.next().getDescription());
        assertFalse(events.hasNext());
    }

    @Test
    public void updateMidDayHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.updateHours(2, 6);
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(6, employeeTest.getHours().get(1));
        assertEquals(18, employeeTest.getHoursWorked());
    }

    @Test
    public void updateLastDayHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.updateHours(3, 5);
        assertEquals(3, employeeTest.getHours().size());
        assertEquals(5, employeeTest.getHours().get(2));
        assertEquals(10, employeeTest.getHoursWorked());
    }

    @Test
    public void clearHoursTest() {
        assertEquals(3, employeeTest.getHours().size());
        employeeTest.clearHours();
        assertTrue(employeeTest.getHours().isEmpty());
        assertEquals(0, employeeTest.getHoursWorked());
    }
}