package persistence;

import model.Employee;
import model.EmployeeDatabase;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Sourced from CPSC 210 JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            EmployeeDatabase database = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDatabase.json");
        try {
            EmployeeDatabase database = reader.read();
            assertEquals("Undated", database.getDate());
            assertEquals(0, database.getNumEmployees());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDatabase() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDatabase.json");
        try {
            EmployeeDatabase database = reader.read();
            assertEquals("Undated", database.getDate());
            List<Employee> employees = database.getEmployees();
            List<Integer> bobHours = database.findEmployee("Bob Bobby").getHours();
            List<Integer> larryHours = database.findEmployee("Larry Larson").getHours();
            assertEquals(2, employees.size());
            checkEmployee("Bob Bobby", bobHours, employees.get(0));
            checkEmployee("Larry Larson", larryHours, employees.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
