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

    public String toCSV() {
        return patientId + "," + clinicianId + "," + date;
    }

    public static Appointment fromCSV(String line) {
        String[] a = line.split(",");
        return new Appointment(a[0], a[1], a[2]);
    }
}
