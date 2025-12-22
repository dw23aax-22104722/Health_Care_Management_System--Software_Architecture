package Model;

public class Prescription {
    private String id;
    private String patientId;
    private String condition;
    private String drug;

    public Prescription(String id, String patientId, String condition, String drug) {
        this.id = id;
        this.patientId = patientId;
        this.condition = condition;
        this.drug = drug;
    }

    public String toCSV() {
        return id + "," + patientId + "," + condition + "," + drug;
    }

    public static Prescription fromCSV(String line) {
        String[] p = line.split(",");
        return new Prescription(p[0], p[1], p[2], p[3]);
    }
}
