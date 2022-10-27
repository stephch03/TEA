package persistence;

import model.Employee;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    protected void checkEmployee(String name, List<Integer> hours, Employee e) {
        assertEquals(name, e.getName());
        assertEquals(hours, e.getHours());
    }
}
