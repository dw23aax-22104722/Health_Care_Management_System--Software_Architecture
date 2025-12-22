package Model;

public class Appointment {

    private String patientId;
    private String clinicianId;
    private String date;

    public Appointment(String patientId, String clinicianId, String date) {
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.date = date;
    }

    // REQUIRED getters
    public String getPatientId() {
        return patientId;
    }

    public String getClinicianId() {
        return clinicianId;
    }

    public String getDate() {
        return date;
    }

    // CSV support
    public static Appointment fromCSV(String line) {
        String[] p = line.split(",");
        return new Appointment(p[0], p[1], p[2]);
    }

    public String toCSV() {
        return patientId + "," + clinicianId + "," + date;
    }
}

