package Controller;


import View.HealthcareView;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class HealthcareController {

    private HealthcareView view;

    public HealthcareController(HealthcareView view) {
        this.view = view;
        loadData();
        registerListeners();
    }

    // ================= LOAD CSV =================
    private void loadData() {

        DefaultTableModel pModel = (DefaultTableModel) view.patientTable.getModel();
        for (String line : CSVUtil.read("patients.csv")) {
            Patient p = Patient.fromCSV(line);
            pModel.addRow(new Object[]{p.getId(), p.getName(), p.getDob()});
        }

        DefaultTableModel aModel = (DefaultTableModel) view.appointmentTable.getModel();
        for (String line : CSVUtil.read("appointments.csv")) {
            Appointment a = Appointment.fromCSV(line);
            aModel.addRow(new Object[]{a.getPatientId(), a.getClinicianId(), a.getDate()});
        }

        DefaultTableModel prModel = (DefaultTableModel) view.prescriptionTable.getModel();
        for (String line : CSVUtil.read("prescriptions.csv")) {
            Prescription pr = Prescription.fromCSV(line);
            prModel.addRow(new Object[]{
                    pr.toCSV().split(",")[0],
                    pr.toCSV().split(",")[1],
                    pr.toCSV().split(",")[2],
                    pr.toCSV().split(",")[3]
            });
        }
    }

    // ================= LISTENERS =================
    private void registerListeners() {

        // ADD PATIENT
        view.addPatientBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Patient ID:");
            String name = JOptionPane.showInputDialog("Name:");
            String dob = JOptionPane.showInputDialog("DOB:");
            ((DefaultTableModel) view.patientTable.getModel())
                    .addRow(new Object[]{id, name, dob});
        });

        // EDIT PATIENT
        view.editPatientBtn.addActionListener(e -> {
            int row = view.patientTable.getSelectedRow();
            if (row >= 0) {
                DefaultTableModel m = (DefaultTableModel) view.patientTable.getModel();
                m.setValueAt(JOptionPane.showInputDialog("Name:"), row, 1);
                m.setValueAt(JOptionPane.showInputDialog("DOB:"), row, 2);
            }
        });

        // DELETE PATIENT
        view.deletePatientBtn.addActionListener(e -> {
            int row = view.patientTable.getSelectedRow();
            if (row >= 0) {
                ((DefaultTableModel) view.patientTable.getModel()).removeRow(row);
            }
        });

        // ADD APPOINTMENT
        view.addAppointmentBtn.addActionListener(e -> {
            String pid = JOptionPane.showInputDialog("Patient ID:");
            String cid = JOptionPane.showInputDialog("Clinician ID:");
            String date = JOptionPane.showInputDialog("Date:");
            ((DefaultTableModel) view.appointmentTable.getModel())
                    .addRow(new Object[]{pid, cid, date});
        });

        // EDIT APPOINTMENT
        view.editAppointmentBtn.addActionListener(e -> {
            int row = view.appointmentTable.getSelectedRow();
            if (row >= 0) {
                ((DefaultTableModel) view.appointmentTable.getModel())
                        .setValueAt(JOptionPane.showInputDialog("New Date:"), row, 2);
            }
        });

        // DELETE APPOINTMENT
        view.deleteAppointmentBtn.addActionListener(e -> {
            int row = view.appointmentTable.getSelectedRow();
            if (row >= 0) {
                ((DefaultTableModel) view.appointmentTable.getModel()).removeRow(row);
            }
        });

        // ADD PRESCRIPTION
        view.addPrescriptionBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Prescription ID:");
            String pid = JOptionPane.showInputDialog("Patient ID:");
            String cond = JOptionPane.showInputDialog("Condition:");
            String drug = JOptionPane.showInputDialog("Drug:");
            ((DefaultTableModel) view.prescriptionTable.getModel())
                    .addRow(new Object[]{id, pid, cond, drug});
        });

        // ADD REFERRAL (Singleton)
        view.addReferralBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Referral ID:");
            String pid = JOptionPane.showInputDialog("Patient ID:");
            String from = JOptionPane.showInputDialog("From Clinician:");
            String to = JOptionPane.showInputDialog("To Clinician:");
            String reason = JOptionPane.showInputDialog("Reason:");

            Referral r = new Referral(id, pid, from, to, reason);
            ReferralManager.getInstance().addReferral(r);

            ((DefaultTableModel) view.referralTable.getModel())
                    .addRow(new Object[]{id, pid, from, to, reason});
        });

        // SAVE ON EXIT
        view.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                saveData();
            }
        });
    }

    // ================= SAVE CSV =================
    private void saveData() {

        saveTable("patients.csv", view.patientTable);
        saveTable("appointments.csv", view.appointmentTable);
        saveTable("prescriptions.csv", view.prescriptionTable);
        saveTable("referrals.csv", view.referralTable);
    }

    private void saveTable(String file, JTable table) {
        ArrayList<String> lines = new ArrayList<String>();
        DefaultTableModel m = (DefaultTableModel) table.getModel();

        for (int i = 0; i < m.getRowCount(); i++) {
            String line = "";
            for (int j = 0; j < m.getColumnCount(); j++) {
                line += m.getValueAt(i, j);
                if (j < m.getColumnCount() - 1) line += ",";
            }
            lines.add(line);
        }
        CSVUtil.write(file, lines);
    }
}

