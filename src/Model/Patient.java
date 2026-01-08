package Model;

import java.util.*;

public class Patient {

    private String id;
    private String name;
    private String dob;

    public Patient(String id, String name, String dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDob() { return dob; }

    public static Patient fromCSV(String line) {
        String[] parts = line.split(",");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid patient CSV line: " + line);
        }

        return new Patient(parts[0], parts[1], parts[2]);
    }

}
