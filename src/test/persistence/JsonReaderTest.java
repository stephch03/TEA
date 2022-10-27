package persistence;

import model.Employee;
import model.EmployeeDatabase;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
            assertEquals(2, employees.size());
            checkEmployee("Bob Bobby", employees.get(0).getHours(), employees.get(0));
            checkEmployee("Larry Larson", employees.get(1).getHours(), employees.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
