package persistence;

import org.json.JSONObject;

// Sourced from CPSC 210 JsonSerializationDemo
public interface Writable {
    JSONObject toJson();
}
