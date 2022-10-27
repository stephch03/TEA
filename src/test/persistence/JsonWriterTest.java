package persistence;

import model.Employee;
import model.EmployeeDatabase;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Sourced from CPSC 210 JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            EmployeeDatabase database = new EmployeeDatabase();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterDatabase() {
        try {
            EmployeeDatabase database = new EmployeeDatabase();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDatabase.json");
            writer.open();
            writer.write(database);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDatabase.json");
            database = reader.read();
            assertEquals("Undated", database.getDate());
            assertEquals(0, database.getNumEmployees());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDatabase() {
        try {
            EmployeeDatabase database = new EmployeeDatabase();
            database.addEmployee(new Employee("Bob Bobby"));
            database.addEmployee(new Employee("Larry Larson"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDatabase.json");
            writer.open();
            writer.write(database);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDatabase.json");
            database = reader.read();
            assertEquals("Undated", database.getDate());
            List<Employee> employees = database.getEmployees();
            assertEquals(2, employees.size());
            checkEmployee("Bob Bobby", employees.get(0).getHours(), employees.get(0));
            checkEmployee("Larry Larson", employees.get(1).getHours(), employees.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
