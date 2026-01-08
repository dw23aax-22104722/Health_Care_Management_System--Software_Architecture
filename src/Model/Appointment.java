package Model;

public class Appointment {

    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String date;

    public Appointment(String appointmentId, String patientId, String clinicianId, String date) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.date = date;
    }

    public String getAppointmentId()
    {
        return appointmentId;
    }

    public String getPatientId()
    {
        return patientId;
    }

    public String getClinicianId()
    {
        return clinicianId;
    }

    public String getDate()
    {
        return date;
    }

    public static Appointment fromCSV(String line) {
        String[] parts = line.split(",");

        // appointment_id patient_id clinician_id facility_id appointment_date ...
        return new Appointment(
                parts[0],
                parts[1],
                parts[2],
                parts[3]
        );
    }
}
