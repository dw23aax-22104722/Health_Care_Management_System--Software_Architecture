package View;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HealthcareView extends JFrame {

    public JTable patientTable, appointmentTable, prescriptionTable, referralTable;

    public JButton addPatientBtn, editPatientBtn, deletePatientBtn;
    public JButton addAppointmentBtn, editAppointmentBtn, deleteAppointmentBtn;
    public JButton addPrescriptionBtn, addReferralBtn;

    public HealthcareView() {
        setTitle("Healthcare Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        patientTable = new JTable(new DefaultTableModel(
                new Object[]{"ID","Name","DOB"}, 0));

        appointmentTable = new JTable(new DefaultTableModel(
                new Object[]{"Patient ID","Clinician ID","Date"}, 0));

        prescriptionTable = new JTable(new DefaultTableModel(
                new Object[]{"ID","Patient ID","Condition","Drug"}, 0));

        referralTable = new JTable(new DefaultTableModel(
                new Object[]{"ID","Patient ID","From","To","Reason"}, 0));

        addPatientBtn = new JButton("Add");
        editPatientBtn = new JButton("Edit");
        deletePatientBtn = new JButton("Delete");

        addAppointmentBtn = new JButton("Add");
        editAppointmentBtn = new JButton("Edit");
        deleteAppointmentBtn = new JButton("Delete");

        addPrescriptionBtn = new JButton("Add Prescription");
        addReferralBtn = new JButton("Add Referral");

        tabs.add("Patients", createPanel(patientTable, addPatientBtn, editPatientBtn, deletePatientBtn));
        tabs.add("Appointments", createPanel(appointmentTable, addAppointmentBtn, editAppointmentBtn, deleteAppointmentBtn));
        tabs.add("Prescriptions", createPanel(prescriptionTable, addPrescriptionBtn));
        tabs.add("Referrals", createPanel(referralTable, addReferralBtn));

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

