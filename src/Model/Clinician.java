package Model;

public class Clinician {
    private String id;
    private String name;
    private String role;

    public Clinician(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getID()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getRole()
    {
        return role;
    }

    public String toCSV() {
        return id + "," + name + "," + role;
    }

    public static Clinician fromCSV(String line) {
        String[] parts = line.split(",");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid clinician CSV line: " + line);
        }

        String id = parts[0];
        String name = parts[1];
        String role = parts[2];

        return new Clinician(id, name, role);
    }

}
