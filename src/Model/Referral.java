package Model;

public class Referral {
    private String id;
    private String patientId;
    private String fromClinician;
    private String toClinician;
    private String reason;

    public Referral(String id, String patientId, String fromClinician,
                    String toClinician, String reason) {
        this.id = id;
        this.patientId = patientId;
        this.fromClinician = fromClinician;
        this.toClinician = toClinician;
        this.reason = reason;
    }

    public String toCSV() {
        return id + "," + patientId + "," + fromClinician + "," + toClinician + "," + reason;
    }
}
