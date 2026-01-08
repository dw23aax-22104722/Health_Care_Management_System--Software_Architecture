package Controller;

import View.HealthcareView;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Set;

public class HealthcareController {

    private HealthcareView view;

    private static final Set<String> VALID_PRESCRIPTION_STATUS =
            Set.of("Issued", "Collected");

    public HealthcareController(HealthcareView view) {
        this.view = view;
        loadData();
        registerListeners();
    }

    /* ================= LOAD CSV ================= */

    private void loadData() {

        for (String line : CSVUtil.read("patients.csv")) {
            Patient p = Patient.fromCSV(line);
            model(view.patientTable).addRow(new Object[]{
                    p.getId(), p.getName(), p.getDob()
            });
        }

        for (String line : CSVUtil.read("clinicians.csv")) {
            Clinician c = Clinician.fromCSV(line);
            model(view.clinicianTable).addRow(new Object[]{
                    c.getID(), c.getName(), c.getRole()
            });
        }

        for (String line : CSVUtil.read("appointments.csv")) {
            Appointment a = Appointment.fromCSV(line);
            model(view.appointmentTable).addRow(new Object[]{
                    a.getAppointmentId(),
                    a.getPatientId(),
                    a.getClinicianId(),
                    a.getDate()
            });
        }

        for (String line : CSVUtil.read("prescriptions.csv")) {
            Prescription p = Prescription.fromCSV(line);
            model(view.prescriptionTable).addRow(new Object[]{
                    p.getId(),
                    p.getPatientId(),
                    p.getClinicianId(),
                    p.getMedication(),
                    p.getStatus()
            });
        }

        for (String line : CSVUtil.read("referrals.csv")) {
            Referral r = Referral.fromCSV(line);
            model(view.referralTable).addRow(new Object[]{
                    r.getId(),
                    r.getPatientId(),
                    r.getFromClinician(),
                    r.getToClinician()
            });
        }
    }

    /* ================= LISTENERS ================= */

    private void registerListeners() {

        /* ---------- PATIENT ---------- */

        view.addPatientBtn.addActionListener(e -> {
            String id = input("Patient ID:");
            if (idExists(view.patientTable, id, -1)) return;

            model(view.patientTable).addRow(new Object[]{
                    id, input("Name:"), input("DOB:")
            });
        });

        view.editPatientBtn.addActionListener(e -> {
            int r = selected(view.patientTable);
            if (r < 0) return;

            String id = input("Patient ID:");
            if (idExists(view.patientTable, id, r)) return;

            set(view.patientTable, r, new Object[]{
                    id, input("Name:"), input("DOB:")
            });
        });

        view.deletePatientBtn.addActionListener(e -> delete(view.patientTable));

        /* ---------- CLINICIAN ---------- */

        view.addClinicianBtn.addActionListener(e -> {
            String id = input("Clinician ID:");
            if (idExists(view.clinicianTable, id, -1)) return;

            model(view.clinicianTable).addRow(new Object[]{
                    id, input("Name:"), input("Role:")
            });
        });

        view.editClinicianBtn.addActionListener(e -> {
            int r = selected(view.clinicianTable);
            if (r < 0) return;

            String id = input("Clinician ID:");
            if (idExists(view.clinicianTable, id, r)) return;

            set(view.clinicianTable, r, new Object[]{
                    id, input("Name:"), input("Role:")
            });
        });

        view.deleteClinicianBtn.addActionListener(e -> delete(view.clinicianTable));

        /* ---------- APPOINTMENT (GP ONLY) ---------- */

        view.addAppointmentBtn.addActionListener(e -> {
            String id = input("Appointment ID:");
            if (idExists(view.appointmentTable, id, -1)) return;

            String pid = input("Patient ID:");
            String cid = input("Clinician ID:");

            if (!rowExists(view.patientTable, pid) ||
                    !rowExists(view.clinicianTable, cid)) {
                error("Invalid Patient or Clinician ID");
                return;
            }

            if (!"GP".equals(getClinicianRole(cid))) {
                error("Only GPs can manage appointments");
                return;
            }

            model(view.appointmentTable).addRow(new Object[]{
                    id, pid, cid, input("Date:")
            });
        });

        view.editAppointmentBtn.addActionListener(e -> {
            int r = selected(view.appointmentTable);
            if (r < 0) return;

            String id = input("Appointment ID:");
            if (idExists(view.appointmentTable, id, r)) return;

            String pid = input("Patient ID:");
            String cid = input("Clinician ID:");

            if (!rowExists(view.patientTable, pid) ||
                    !rowExists(view.clinicianTable, cid)) {
                error("Invalid Patient or Clinician ID");
                return;
            }

            if (!"GP".equals(getClinicianRole(cid))) {
                error("Only GPs can manage appointments");
                return;
            }

            set(view.appointmentTable, r, new Object[]{
                    id, pid, cid, input("Date:")
            });
        });

        view.deleteAppointmentBtn.addActionListener(e -> delete(view.appointmentTable));

        /* ---------- PRESCRIPTION ---------- */

        view.addPrescriptionBtn.addActionListener(e -> {
            String id = input("Prescription ID:");
            if (idExists(view.prescriptionTable, id, -1)) return;

            String pid = input("Patient ID:");
            String cid = input("Clinician ID:");
            String status = input("Status (Issued/Collected):");

            if (!rowExists(view.patientTable, pid) ||
                    !rowExists(view.clinicianTable, cid)) {
                error("Invalid Patient or Clinician ID");
                return;
            }

            if (!VALID_PRESCRIPTION_STATUS.contains(status)) {
                error("Invalid prescription status");
                return;
            }

            model(view.prescriptionTable).addRow(new Object[]{
                    id, pid, cid, input("Medication:"), status
            });
        });

        view.editPrescriptionBtn.addActionListener(e -> {
            int r = selected(view.prescriptionTable);
            if (r < 0) return;

            String id = input("Prescription ID:");
            if (idExists(view.prescriptionTable, id, r)) return;

            String pid = input("Patient ID:");
            String cid = input("Clinician ID:");
            String status = input("Status (Issued/Collected):");

            if (!rowExists(view.patientTable, pid) ||
                    !rowExists(view.clinicianTable, cid)) {
                error("Invalid Patient or Clinician ID");
                return;
            }

            if (!VALID_PRESCRIPTION_STATUS.contains(status)) {
                error("Invalid prescription status");
                return;
            }

            set(view.prescriptionTable, r, new Object[]{
                    id, pid, cid, input("Medication:"), status
            });
        });

        view.deletePrescriptionBtn.addActionListener(e -> delete(view.prescriptionTable));

        /* ---------- REFERRAL ---------- */

        view.addReferralBtn.addActionListener(e -> {
            String id = input("Referral ID:");
            if (idExists(view.referralTable, id, -1)) return;

            String pid = input("Patient ID:");
            String from = input("From Clinician ID:");
            String to = input("To Clinician ID:");

            if (from.equals(to)) {
                error("Referring clinician and receiving clinician must be different");
                return;
            }

            if (!rowExists(view.patientTable, pid) ||
                    !rowExists(view.clinicianTable, from) ||
                    !rowExists(view.clinicianTable, to)) {
                error("Invalid Patient or Clinician ID");
                return;
            }

            model(view.referralTable).addRow(new Object[]{id, pid, from, to});
        });

        view.editReferralBtn.addActionListener(e -> {
            int r = selected(view.referralTable);
            if (r < 0) return;

            String id = input("Referral ID:");
            if (idExists(view.referralTable, id, r)) return;

            String pid = input("Patient ID:");
            String from = input("From Clinician ID:");
            String to = input("To Clinician ID:");

            if (from.equals(to)) {
                error("Referring clinician and receiving clinician must be different");
                return;
            }

            if (!rowExists(view.patientTable, pid) ||
                    !rowExists(view.clinicianTable, from) ||
                    !rowExists(view.clinicianTable, to)) {
                error("Invalid Patient or Clinician ID");
                return;
            }

            set(view.referralTable, r, new Object[]{id, pid, from, to});
        });

        view.deleteReferralBtn.addActionListener(e -> delete(view.referralTable));

        view.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                saveData();
            }
        });
    }

    /* ================= SAVE ================= */

    private void saveData() {
        save("patients.csv", view.patientTable);
        save("clinicians.csv", view.clinicianTable);
        save("appointments.csv", view.appointmentTable);
        save("prescriptions.csv", view.prescriptionTable);
        save("referrals.csv", view.referralTable);
    }

    /* ================= HELPERS ================= */

    private DefaultTableModel model(JTable t) {
        return (DefaultTableModel) t.getModel();
    }

    private int selected(JTable t) {
        if (t.getSelectedRow() < 0) error("Select a row first");
        return t.getSelectedRow();
    }

    private void delete(JTable t) {
        int r = selected(t);
        if (r >= 0) model(t).removeRow(r);
    }

    private boolean idExists(JTable t, String id, int ignoreRow) {
        for (int i = 0; i < model(t).getRowCount(); i++) {
            if (i != ignoreRow &&
                    model(t).getValueAt(i, 0).equals(id)) {
                error("ID already exists");
                return true;
            }
        }
        return false;
    }

    private boolean rowExists(JTable t, String id) {
        for (int i = 0; i < model(t).getRowCount(); i++)
            if (model(t).getValueAt(i, 0).equals(id))
                return true;
        return false;
    }

    private void set(JTable t, int r, Object[] vals) {
        for (int i = 0; i < vals.length; i++)
            model(t).setValueAt(vals[i], r, i);
    }

    private String getClinicianRole(String id) {
        for (int i = 0; i < model(view.clinicianTable).getRowCount(); i++)
            if (model(view.clinicianTable).getValueAt(i, 0).equals(id))
                return (String) model(view.clinicianTable).getValueAt(i, 2);
        return "";
    }

    private String input(String m) {
        return JOptionPane.showInputDialog(m);
    }

    private void error(String m) {
        JOptionPane.showMessageDialog(null, m);
    }

    private void save(String fileName, JTable table) {
        DefaultTableModel m = model(table);
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 0; i < m.getRowCount(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < m.getColumnCount(); j++) {
                sb.append(m.getValueAt(i, j));
                if (j < m.getColumnCount() - 1) sb.append(",");
            }
            lines.add(sb.toString());
        }

        CSVUtil.write(fileName, lines);
    }
}






