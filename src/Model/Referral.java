package Model;

public class Referral {

    private String id;
    private String patientId;
    private String fromClinician;
    private String toClinician;


    public Referral(String id, String patientId, String fromClinician, String toClinician) {
        this.id = id;
        this.patientId = patientId;
        this.fromClinician = fromClinician;
        this.toClinician = toClinician;

    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getFromClinician() { return fromClinician; }
    public String getToClinician() { return toClinician; }


    public static Referral fromCSV(String line) {
        String[] parts = line.split(" ");

        return new Referral(
                parts[0],
                parts[1],
                parts[2],
                parts[3]
        );
    }
}
