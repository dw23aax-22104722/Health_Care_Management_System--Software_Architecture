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
        String[] c = line.split(" ");
        return new Clinician(c[0], c[1] + " " + c[2] + " " + c[3], c[4]);
    }
}
