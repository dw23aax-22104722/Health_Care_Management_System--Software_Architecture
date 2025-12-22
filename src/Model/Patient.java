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
        String[] parts = line.split(" ");

        // patient_id first_name last_name dob ...
        String id = parts[0];
        String name = parts[1] + " " + parts[2];
        String dob = parts[3];

        return new Patient(id, name, dob);
    }
}
