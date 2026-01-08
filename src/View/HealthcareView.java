package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HealthcareView extends JFrame {

    public JTable patientTable, appointmentTable, prescriptionTable, referralTable, clinicianTable;

    public JButton addPatientBtn, editPatientBtn, deletePatientBtn;
    public JButton addAppointmentBtn, editAppointmentBtn, deleteAppointmentBtn;
    public JButton addPrescriptionBtn, editPrescriptionBtn, deletePrescriptionBtn;
    public JButton addReferralBtn, editReferralBtn, deleteReferralBtn;
    public JButton addClinicianBtn, editClinicianBtn, deleteClinicianBtn;

    public HealthcareView() {
        setTitle("Healthcare Management System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // Patient table
        patientTable = new JTable(new DefaultTableModel(
                new Object[]{"ID","Name","DOB"}, 0));

        // Appointment table
        appointmentTable = new JTable(new DefaultTableModel(
                new Object[]{"Appointment ID","Patient ID","Clinician ID","Date"}, 0));

        // Prescription table
        prescriptionTable = new JTable(new DefaultTableModel(
                new Object[]{"ID","Patient ID", "Clinician ID", "Medication name","Status"}, 0));

        // Referral table
        referralTable = new JTable(new DefaultTableModel(
                new Object[]{"ID","Patient ID","From","To"}, 0));

        // Clinician
        clinicianTable = new JTable(new DefaultTableModel(
                new Object[]{"Clinician ID","Name","Type"}, 0));

        // The buttons are grouped
        addPatientBtn = new JButton("Add");
        editPatientBtn = new JButton("Edit");
        deletePatientBtn = new JButton("Delete");

        addAppointmentBtn = new JButton("Add");
        editAppointmentBtn = new JButton("Edit");
        deleteAppointmentBtn = new JButton("Delete");

        addPrescriptionBtn = new JButton("Add");
        editPrescriptionBtn = new JButton("Edit");
        deletePrescriptionBtn = new JButton("Delete");

        addReferralBtn = new JButton("Add");
        editReferralBtn = new JButton("Edit");
        deleteReferralBtn = new JButton("Delete");

        addClinicianBtn = new JButton("Add");
        editClinicianBtn = new JButton("Edit");
        deleteClinicianBtn = new JButton("Delete");


        // ADD TABS
        tabs.add("Patients", createPanel(patientTable, addPatientBtn, editPatientBtn, deletePatientBtn));
        tabs.add("Appointments", createPanel(appointmentTable, addAppointmentBtn, editAppointmentBtn, deleteAppointmentBtn));
        tabs.add("Prescriptions", createPanel(prescriptionTable, addPrescriptionBtn, editPrescriptionBtn, deletePrescriptionBtn));
        tabs.add("Referrals", createPanel(referralTable, addReferralBtn, editReferralBtn, deleteReferralBtn));
        tabs.add("Clinicians", createPanel(clinicianTable, addClinicianBtn, editClinicianBtn, deleteClinicianBtn));

        add(tabs);
    }

    private JPanel createPanel(JTable table, JButton... buttons) {
        JPanel p = new JPanel(new BorderLayout());
        JPanel b = new JPanel();
        for (JButton btn : buttons) b.add(btn);
        p.add(new JScrollPane(table), BorderLayout.CENTER);
        p.add(b, BorderLayout.SOUTH);
        return p;
    }
}


