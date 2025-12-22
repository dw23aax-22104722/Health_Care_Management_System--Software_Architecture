package Model;

public class Prescription {

    private String id;
    private String patientId;
    private String clinicianId;
    private String medication;
    private String status;

    public Prescription(String id, String patientId, String clinicianId, String medication, String status) {
        this.id = id;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.medication = medication;
        this.status = status;
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getClinicianId() { return clinicianId; }
    public String getMedication() { return medication; }
    public String getStatus() { return status; }

    public static Prescription fromCSV(String line) {
        String[] parts = line.split(" ");

        // prescription_id patient_id clinician_id appointment_id date medication ...
        String id = parts[0];
        String patientId = parts[1];
        String clinicianId = parts[2];
        String medication = parts[5];
        String status = parts[parts.length - 3];

        return new Prescription(id, patientId, clinicianId, medication, status);
    }
}

