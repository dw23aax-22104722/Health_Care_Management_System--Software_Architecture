package Controller;

import View.HealthcareView;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Set;

public class HealthcareController {

    private HealthcareView view;

    // allowed clinician roles
    private static final Set<String> VALID_ROLES = Set.of(
            "GP",
            "Consultant",
            "Senior_Nurse",
            "Practice_Nurse",
            "Staff_Nurse"
    );

    public HealthcareController(HealthcareView view) {
        this.view = view;
        loadData();
        registerListeners();
    }

    // ================= LOAD CSV =================
    private void loadData() {

        // ================= PATIENTS =================
        DefaultTableModel pModel = (DefaultTableModel) view.patientTable.getModel();
        for (String line : CSVUtil.read("patients.csv")) {
            Patient p = Patient.fromCSV(line);
            pModel.addRow(new Object[]{p.getId(), p.getName(), p.getDob()});
        }

        // ================= CLINICIANS =================
        DefaultTableModel cModel = (DefaultTableModel) view.clinicianTable.getModel();
        for (String line : CSVUtil.read("clinicians.csv")) {
            Clinician c = Clinician.fromCSV(line);
            cModel.addRow(new Object[]{c.getID(), c.getName(), c.getRole()});
        }

        // ================= APPOINTMENTS =================
        DefaultTableModel aModel = (DefaultTableModel) view.appointmentTable.getModel();
        for (String line : CSVUtil.read("appointments.csv")) {
            Appointment a = Appointment.fromCSV(line);
            aModel.addRow(new Object[]{a.getAppointmentId(), a.getPatientId(), a.getClinicianId(), a.getDate()});
        }

        // ================= PRESCRIPTIONS =================
        DefaultTableModel prModel = (DefaultTableModel) view.prescriptionTable.getModel();
        for (String line : CSVUtil.read("prescriptions.csv")) {
            Prescription pr = Prescription.fromCSV(line);
            prModel.addRow(new Object[]{
                    pr.getId(),
                    pr.getPatientId(),
                    pr.getClinicianId(),
                    pr.getMedication(),
                    pr.getStatus()
            });
        }

        // ================= REFERRALS =================
        DefaultTableModel rModel = (DefaultTableModel) view.referralTable.getModel();
        for (String line : CSVUtil.read("referrals.csv")) {
            Referral r = Referral.fromCSV(line);
            rModel.addRow(new Object[]{
                    r.getId(),
                    r.getPatientId(),
                    r.getFromClinician(),
                    r.getToClinician()
            });
        }
    }

    // ================= LISTENERS =================
    private void registerListeners() {

        // ===== ADD PATIENT =====
        view.addPatientBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Patient ID:");
            if (existsInTable((DefaultTableModel) view.patientTable.getModel(), id, 0)) {
                JOptionPane.showMessageDialog(null, "Patient ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name = JOptionPane.showInputDialog("Name:");
            String dob = JOptionPane.showInputDialog("DOB:");
            ((DefaultTableModel) view.patientTable.getModel()).addRow(new Object[]{id, name, dob});
        });

        // ===== EDIT PATIENT =====
        view.editPatientBtn.addActionListener(e -> {
            int row = view.patientTable.getSelectedRow();
            if (row >= 0) {
                DefaultTableModel m = (DefaultTableModel) view.patientTable.getModel();
                m.setValueAt(JOptionPane.showInputDialog("Name:"), row, 1);
                m.setValueAt(JOptionPane.showInputDialog("DOB:"), row, 2);
            }
        });

        // ===== DELETE PATIENT =====
        view.deletePatientBtn.addActionListener(e -> {
            int row = view.patientTable.getSelectedRow();
            if (row >= 0) ((DefaultTableModel) view.patientTable.getModel()).removeRow(row);
        });

        // ===== ADD CLINICIAN =====
        view.addClinicianBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Clinician ID:");
            if (existsInTable((DefaultTableModel) view.clinicianTable.getModel(), id, 0)) {
                JOptionPane.showMessageDialog(null, "Clinician ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = JOptionPane.showInputDialog("Name:");
            String role = JOptionPane.showInputDialog(
                    "Role (GP, Consultant, Senior_Nurse, Practice_Nurse, Staff_Nurse):"
            );

            if (!VALID_ROLES.contains(role)) {
                JOptionPane.showMessageDialog(null, "Invalid clinician role!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ((DefaultTableModel) view.clinicianTable.getModel()).addRow(new Object[]{id, name, role});
        });

        // ===== EDIT CLINICIAN =====
        view.editClinicianBtn.addActionListener(e -> {
            int row = view.clinicianTable.getSelectedRow();
            if (row >= 0) {
                String name = JOptionPane.showInputDialog("Name:");
                String role = JOptionPane.showInputDialog(
                        "Role (GP, Consultant, Senior_Nurse, Practice_Nurse, Staff_Nurse):"
                );

                if (!VALID_ROLES.contains(role)) {
                    JOptionPane.showMessageDialog(null, "Invalid clinician role!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DefaultTableModel m = (DefaultTableModel) view.clinicianTable.getModel();
                m.setValueAt(name, row, 1);
                m.setValueAt(role, row, 2);
            }
        });

        // ===== DELETE CLINICIAN =====
        view.deleteClinicianBtn.addActionListener(e -> {
            int row = view.clinicianTable.getSelectedRow();
            if (row >= 0) ((DefaultTableModel) view.clinicianTable.getModel()).removeRow(row);
        });

        // ===== ADD APPOINTMENT =====
        view.addAppointmentBtn.addActionListener(e -> {
            DefaultTableModel aModel = (DefaultTableModel) view.appointmentTable.getModel();
            String id = JOptionPane.showInputDialog("Appointment ID:");
            if (existsInTable(aModel, id, 0)) {
                JOptionPane.showMessageDialog(null, "Appointment ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String pid = JOptionPane.showInputDialog("Patient ID:");
            if (!existsInTable((DefaultTableModel) view.patientTable.getModel(), pid, 0)) {
                JOptionPane.showMessageDialog(null, "Patient ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cid = JOptionPane.showInputDialog("Clinician ID:");
            if (!existsInTable((DefaultTableModel) view.clinicianTable.getModel(), cid, 0)) {
                JOptionPane.showMessageDialog(null, "Clinician ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!getClinicianType(cid).equalsIgnoreCase("GP")) {
                JOptionPane.showMessageDialog(null, "Only GPs can handle appointments!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String date = JOptionPane.showInputDialog("Date:");
            aModel.addRow(new Object[]{id, pid, cid, date});
        });

        // ===== ADD REFERRAL =====
        view.addReferralBtn.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Referral ID:");
            if (existsInTable((DefaultTableModel) view.referralTable.getModel(), id, 0)) {
                JOptionPane.showMessageDialog(null, "Referral ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String pid = JOptionPane.showInputDialog("Patient ID:");
            if (!existsInTable((DefaultTableModel) view.patientTable.getModel(), pid, 0)) {
                JOptionPane.showMessageDialog(null, "Patient ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String from = JOptionPane.showInputDialog("From Clinician:");
            String to = JOptionPane.showInputDialog("To Clinician:");

            if (from.equals(to)) {
                JOptionPane.showMessageDialog(null, "From and To clinicians must be different!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!existsInTable((DefaultTableModel) view.clinicianTable.getModel(), from, 0) ||
                    !existsInTable((DefaultTableModel) view.clinicianTable.getModel(), to, 0)) {
                JOptionPane.showMessageDialog(null, "Clinician ID does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Referral r = new Referral(id, pid, from, to);
            ReferralManager.getInstance().addReferral(r);
            ((DefaultTableModel) view.referralTable.getModel()).addRow(new Object[]{id, pid, from, to});
        });

        // ===== EDIT REFERRAL =====
        view.editReferralBtn.addActionListener(e -> {
            int row = view.referralTable.getSelectedRow();
            if (row >= 0) {
                String pid = JOptionPane.showInputDialog("Patient ID:");
                String from = JOptionPane.showInputDialog("From Clinician:");
                String to = JOptionPane.showInputDialog("To Clinician:");

                if (from.equals(to)) {
                    JOptionPane.showMessageDialog(null, "From and To clinicians must be different!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!existsInTable((DefaultTableModel) view.patientTable.getModel(), pid, 0) ||
                        !existsInTable((DefaultTableModel) view.clinicianTable.getModel(), from, 0) ||
                        !existsInTable((DefaultTableModel) view.clinicianTable.getModel(), to, 0)) {
                    JOptionPane.showMessageDialog(null, "Invalid ID provided!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DefaultTableModel m = (DefaultTableModel) view.referralTable.getModel();
                m.setValueAt(pid, row, 1);
                m.setValueAt(from, row, 2);
                m.setValueAt(to, row, 3);
            }
        });

        // ===== DELETE REFERRAL =====
        view.deleteReferralBtn.addActionListener(e -> {
            int row = view.referralTable.getSelectedRow();
            if (row >= 0) ((DefaultTableModel) view.referralTable.getModel()).removeRow(row);
        });

        // ===== SAVE ON EXIT =====
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
        saveTable("clinicians.csv", view.clinicianTable);
    }

    private void saveTable(String file, JTable table) {
        ArrayList<String> lines = new ArrayList<>();
        DefaultTableModel m = (DefaultTableModel) table.getModel();
        for (int i = 0; i < m.getRowCount(); i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < m.getColumnCount(); j++) {
                line.append(m.getValueAt(i, j));
                if (j < m.getColumnCount() - 1) line.append(",");
            }
            lines.add(line.toString());
        }
        CSVUtil.write(file, lines);
    }

    // ================= HELPERS =================
    private boolean existsInTable(DefaultTableModel model, String id, int col) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, col).equals(id)) return true;
        }
        return false;
    }

    private String getClinicianType(String clinicianId) {
        DefaultTableModel cModel = (DefaultTableModel) view.clinicianTable.getModel();
        for (int i = 0; i < cModel.getRowCount(); i++) {
            if (cModel.getValueAt(i, 0).equals(clinicianId)) {
                return (String) cModel.getValueAt(i, 2);
            }
        }
        return "";
    }
}


