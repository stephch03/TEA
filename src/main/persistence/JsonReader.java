package persistence;

import model.Employee;
import model.EmployeeDatabase;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Sourced from CPSC 210 JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads database from file and returns it;
    // throws IOException if an error occurs reading data from file
    public EmployeeDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEmployeeDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses database from JSON object and returns it
    private EmployeeDatabase parseEmployeeDatabase(JSONObject jsonObject) {
        EmployeeDatabase database = new EmployeeDatabase();
        addEmployees(database, jsonObject);
        return database;
    }

    // MODIFIES: database
    // EFFECTS: parses employees from JSON object and adds them to workroom
    private void addEmployees(EmployeeDatabase database, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("database");
        for (Object json : jsonArray) {
            JSONObject nextEmployee = (JSONObject) json;
            addEmployee(database, nextEmployee);
        }
    }

    // MODIFIES: database
    // EFFECTS: parses employee from JSON object and adds it to database
    private void addEmployee(EmployeeDatabase database, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Employee e = new Employee(name);
        database.addEmployee(e);
    }
}
