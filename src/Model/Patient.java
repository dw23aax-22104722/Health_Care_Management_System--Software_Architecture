package Model;

import java.util.*;

public class Patient {


    private String patientId;
    private String patientName;
    private String patientEmail;
    private int patientAge;
    private List<String> conditions;


    public Patient(String patientId, String patientName, String patientEmail, int patientAge) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.patientAge = patientAge;
        this.conditions = new ArrayList<>();
    }


    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public List<String> getConditions() {
        return conditions;
    }


    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }


    public void addCondition(String condition) {
        if (condition != null && !condition.isEmpty()) {
            conditions.add(condition);
        }
    }

    public void removeCondition(String condition) {
        conditions.remove(condition);
    }


    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientEmail='" + patientEmail + '\'' +
                ", patientAge=" + patientAge +
                ", conditions=" + conditions +
                '}';
    }
}
