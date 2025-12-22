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

    public String toCSV() {
        return id + "," + name + "," + dob;
    }

    public static Patient fromCSV(String line) {
        String[] p = line.split(",");
        return new Patient(p[0], p[1], p[2]);
    }
}
